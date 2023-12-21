package cn.zhusaidong.idea.plugins.repositories.admin.domain;

import lombok.Data;

/**
 * @author zhusaidong
 * @since 2023/12/20
 */
@Data
public class Response<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Response<T> success(T data) {
        Response<T> tResponse = new Response<>();
        tResponse.setCode(200);
        tResponse.setData(data);
        tResponse.setMessage("请求成功");
        return tResponse;
    }

    public static <T> Response<T> error(int code, String message) {
        Response<T> tResponse = new Response<>();
        tResponse.setCode(code);
        tResponse.setData(null);
        tResponse.setMessage(message);
        return tResponse;
    }
}
