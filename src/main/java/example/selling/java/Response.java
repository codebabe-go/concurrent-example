package example.selling.java;

/**
 * author: code.babe
 * date: 2016-09-14 20:14
 */
public class Response {

    public interface ReturnCode {
        int SUCCESS = 200;
        int FAILED = 400;
    }

    public Response(int code) {
        this.code = code;
    }

    private int code;
    private Object data;

    public Response wrapper(Object data) {
        this.data = data;
        return this;
    }

    public static Response SUCCESS() {
        return new Response(ReturnCode.SUCCESS);
    }

    // 如果失败了, 就不需要返回数据, 默认为空
    public static Response FAILED() {
        return new Response(ReturnCode.FAILED);
    }

    public int getCode() {
        return code;
    }
}
