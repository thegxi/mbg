package cn.gx.mbg.service;

import cn.gx.mbg.common.api.RespTemplate;
import cn.gx.mbg.request.ConnectionRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 2022/12/07 10:46 pm
 *
 * @author thegx
 */
public interface ConnectionService {
  /**
   * 根据主机名、端口、用户名、用户密码连接
   *
   * @param connectionRequest 连接信息
   * @param request
   * @return
   */
  RespTemplate testCon(ConnectionRequest connectionRequest, HttpServletRequest request);

  /**
   * 根据url连接
   *
   * @param connectionRequest 连接信息
   * @param request
   * @return
   */
  RespTemplate urlCon(ConnectionRequest connectionRequest, HttpServletRequest request);
}
