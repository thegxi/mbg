package cn.gx.mbg.controller;

import cn.gx.mbg.common.api.RespTemplate;
import cn.gx.mbg.common.api.ResultEnum;
import cn.gx.mbg.request.ConnectionRequest;
import cn.gx.mbg.service.ConnectionService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 连接控制器
 * 2022/12/07
 * @author thegx
 */
@RestController
@RequestMapping("/con")
public class ConnectionController {
  @Resource
  private ConnectionService connectionService;

  @PostMapping("/testCon")
  public RespTemplate normalCon(@RequestBody ConnectionRequest connectionRequest,
                                HttpServletRequest request) {
    if (ObjectUtils.isEmpty(connectionRequest) ||
        StringUtils.isAnyEmpty(
                connectionRequest.getType(),
                connectionRequest.getHost(),
                connectionRequest.getUsername(),
                connectionRequest.getPwd())) {
      return RespTemplate.customEnum(ResultEnum.ERROR_PARAM);
    }
    System.out.println(request.getSession().getId());
    return connectionService.testCon(connectionRequest, request);
  }

  @PostMapping("/urlCon")
  public RespTemplate urlCon(@RequestBody ConnectionRequest connectionRequest,
                             HttpServletRequest request) {
    if (ObjectUtils.isEmpty(connectionRequest) ||
            StringUtils.isAnyEmpty(
                    connectionRequest.getUrl(),
                    connectionRequest.getUsername(),
                    connectionRequest.getPwd())) {
      System.out.println(request.getSession().getId());
      return RespTemplate.customEnum(ResultEnum.ERROR_PARAM);
    }
    System.out.println(request.getSession().getId());
    return connectionService.urlCon(connectionRequest, request);
  }
}
