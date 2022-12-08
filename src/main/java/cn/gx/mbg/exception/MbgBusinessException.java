package cn.gx.mbg.exception;

import lombok.NoArgsConstructor;

/**
 * 生成器业务异常
 * 2022/12/08 2:41 pm
 *
 * @author thegx
 */
@NoArgsConstructor
public class MbgBusinessException extends RuntimeException {
  private String msg;
  private String description;

  public MbgBusinessException(String msg) {
    super(msg, new Throwable());
  }
}
