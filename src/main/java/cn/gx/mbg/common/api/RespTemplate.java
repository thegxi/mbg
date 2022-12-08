package cn.gx.mbg.common.api;

import lombok.Data;

/**
 * 统一返回结果
 * Created by gx on 2022/12/06
 */
@Data
public class RespTemplate<T> {
  private Long code;
  private String msg;
  private T data;

  private RespTemplate(Long code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  private RespTemplate(ResultEnum resultEnum, T data) {
    this.code = resultEnum.getCode();
    this.msg = resultEnum.getMsg();
    this.data = data;
  }

  public static RespTemplate success() {
    return new RespTemplate(ResultEnum.SUCESS,null);
  }
  public static RespTemplate success(String msg) {
    return new RespTemplate(ResultEnum.SUCESS.getCode(), msg, null);
  }
  public static <T> RespTemplate success(T data) {
     return new RespTemplate(ResultEnum.SUCESS, data);
  }

  public static <T> RespTemplate customEnum(ResultEnum resultEnum) {
    return new RespTemplate(resultEnum, null);
  }

  public static <T> RespTemplate success(Long code, String msg, T data) {
    return new RespTemplate(code, msg, data);
  }

  public static RespTemplate fail(String msg) {
    return new RespTemplate(ResultEnum.FAIL.getCode(), msg, null);
  }

  public static <T> RespTemplate fail(String msg, T data) {
    return new RespTemplate(ResultEnum.FAIL.getCode(), msg, data);
  }

  public static <T> RespTemplate fail(Long code, String msg, T data) {
    return new RespTemplate(code, msg, data);
  }
}
