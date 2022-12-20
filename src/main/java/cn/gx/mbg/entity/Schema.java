package cn.gx.mbg.entity;

import lombok.Data;

import java.util.List;

/**
 * 数据库实体类
 * 2022/12/09 9:52 am
 *
 * @author thegx
 */
@Data
public class Schema {
  private String name;
  private List<Table> tableList;
}
