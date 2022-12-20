package cn.gx.mbg.service;

import cn.gx.mbg.entity.Schema;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 数据库接口
 * 2022/12/09 9:50 am
 *
 * @author thegx
 */
public interface SchemaService {
  /**
   * 查询当前连接下的数据库
   *
   * @param pageIndex 当前页
   * @param pageSize 查询条数
   * @param request
   * @return
   */
  List<Schema> listSchema(Integer pageIndex, Integer pageSize, HttpServletRequest request);
}
