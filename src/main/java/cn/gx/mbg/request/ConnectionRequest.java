package cn.gx.mbg.request;

import lombok.Data;

/**
 * @author thegx
 */
@Data
public class ConnectionRequest {
  /**
   * 数据库类型
   */
  private String type;
  /**
   * 数据库主机ip
   */
  private String host;
  /**
   * 用户名
   */
  private String username;
  /**
   * 用户密码
   */
  private String pwd;
  /**
   * url
   */
  private String url;
}
