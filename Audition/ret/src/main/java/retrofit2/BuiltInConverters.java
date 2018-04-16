package retrofit2;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Streaming;

/**
 * 1 override Converter.Factory 的三个接口
 * 2 添加5个不同类型的ResponseConverter, 以及override convert
 */

final class BuiltInConverters extends Converter.Factory {


    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == ResponseBody.class) {
            return Utils.isAnnotationPresent(annotations, Streaming.class) ? new StreamingResponseBodyConverter()
                    : new BufferingResponseBodyConverter();
        }

        if (type == Void.class) {
            return new VoidResponseBodyConverter();
        }

        return super.responseBodyConverter(type, annotations, retrofit);
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (RequestBody.class.isAssignableFrom(Utils.getRawType(type))) {
            return new RequestResponseBodyConverter();
        }
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Nullable
    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (String.class.isAssignableFrom(Utils.getRawType(type))) {
            return new ToStringConverter();
        }
        return super.stringConverter(type, annotations, retrofit);
    }



    /** 以下是关于Converter的一些实现类,转化为不同类型 */

    static final class VoidResponseBodyConverter implements Converter<ResponseBody, Void> {
        static final VoidResponseBodyConverter INSTANCE = new VoidResponseBodyConverter();
        @Override
        public Void convert(ResponseBody value) throws IOException {
            value.close();
            return null;
        }
    }

    static final class RequestResponseBodyConverter implements Converter<RequestBody, RequestBody> {

        @Override
        public RequestBody convert(RequestBody value) throws IOException {
            return value;
        }
    }

    static final class StreamingResponseBodyConverter implements Converter<ResponseBody, ResponseBody> {

        @Override
        public ResponseBody convert(ResponseBody value) throws IOException {
            return value;
        }
    }


    static final class BufferingResponseBodyConverter implements Converter<ResponseBody, ResponseBody> {

        @Override
        public ResponseBody convert(ResponseBody value) throws IOException {
            try{
                return Utils.buffer(value); // TODO: 需要研究buffer
            } finally {
                value.close();
            }
        }
    }

    static final class ToStringConverter implements Converter<Object, String> {

        static final ToStringConverter INSTANCE = new ToStringConverter();
        @Override
        public String convert(Object value) throws IOException {
            return value.toString();
        }
    }


}
