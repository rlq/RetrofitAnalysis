package retrofit2;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Creates call adapters for that uses the same thread for both I/O and application-level callbacks.
 * For synchronous calls this is the application thread making the request;
 * For asynchronous calls this is a thread provided by OkHttp's dispatcher.
 *
 * 同一个线程的 IO操作以及 应用层返回的适配器. 同步调用UI线程可请求, 异步调用是被okHttp分发
 */

final class DefaultCallAdapterFactory extends CallAdapter.Factory {

    static final CallAdapter.Factory INSTANCE = new DefaultCallAdapterFactory();


    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != Call.class) {
            return null;
        }

        final Type responseType = Utils.getCallResponseType(returnType); // 必须是一个ParameterizedType
        return new CallAdapter<Object, Object>() {
            @Override
            public Type responseType() {
                return responseType;
            }

            @Override
            public Object adapt(Call<Object> call) {
                return call;
            }
        };
    }
}
