package top.coenocyte.cloud.common;

import lombok.Data;

/**
 * @author 303
 * @description
 * @create 2025-10-10 19:43
 */
@Data
public class AjaxResult<T> {

    private Integer code;
    private String msg;
    private T data;

    public static <T> AjaxResult<T> success() {
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public static <T> AjaxResult<T> success(Integer code, String msg, T data) {
        AjaxResult<T> result = success();
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> AjaxResult<T> error() {
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(500);
        result.setMsg("error");
        return result;
    }

    public static <T> AjaxResult<T> error(Integer code, String msg) {
        AjaxResult<T> result = error();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
