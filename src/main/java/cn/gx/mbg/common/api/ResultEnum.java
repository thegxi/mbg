package cn.gx.mbg.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态码对应类
 *
 * @author thegx
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
  // 成功
  SUCESS(200L, "OK", ""),
  // 失败
  FAIL(500, "FAIL", ""),
  // 未授权
  NO_AUTH(401, "No authorization", "No authorization, please concat with administrator"),
  // 参数不合法
  ERROR_PARAM(400, "Error param", "Error param");

  private final long code;
  private final String msg;
  private final String description;
}
