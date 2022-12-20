package cn.gx.mbg.controller;

import cn.gx.mbg.common.api.RespTemplate;
import cn.gx.mbg.service.SchemaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 数据库控制器
 * 2022/12/08 5:29 pm
 *
 * @author thegx
 */
@RestController
@RequestMapping("/schema")
public class SchemaController {
  @Resource
  private SchemaService schemaService;

  @GetMapping("/list")
  public RespTemplate listSchema(@RequestParam(value = "pageIndex", defaultValue = "0")Integer pageIndex,
                                 @RequestParam(value = "pageSize", defaultValue = "15")Integer pageSize,
                                 HttpServletRequest request) {
    System.out.println(request.getSession().getId());
    return RespTemplate.success(schemaService.listSchema(pageIndex, pageSize, request));
  }
}
