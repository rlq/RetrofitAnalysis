package retrofit2.converter.gson;

import retrofit2.Converter;

public class GsonConverterFactory extends Converter.Factory {

    public static GsonConverterFactory create() {
        return new GsonConverterFactory();
    }
}
