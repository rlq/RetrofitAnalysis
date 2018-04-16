package retrofit2;

import static retrofit2.Utils.checkNotNull;

public class HttpException extends RuntimeException {

    private final int code;
    private final String message;
    private final transient Response<?> response;

    public HttpException(Response<?> response) {
        super(getMessage(response));
        this.code = response.code();
        this.message = response.message();
        this.response = response;
    }


    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public Response<?> response() {
        return response;
    }

    private static String getMessage(Response<?> response) {
        checkNotNull(response, "HttpException response == null");
        return "HTTP" + response.code() + " " + response.message();
    }
}
