package cn.gx.mbg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表实体
 * 2022/12/20 10:31 am
 *
 * @author thegx
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Table {
  /**
   * 表名
   */
  private String name;
  /**
   * 表注释
   */
  private String comment;
}
