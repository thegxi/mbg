package cn.gx.mbg.enums;

import lombok.Getter;

/**
 * 数据库类型
 * 2022/12/08 9:55 am
 *
 * @author thegx
 */
@Getter
public enum DbType {
  MYSQL("mysql", "mysql数据库"),
  ORACLE("oracle", "oracle数据库"),
  SQL_SERVER("sqlserver", "sqlServer数据库"),
  OTHER("other", "其它数据库");
  private String db;
  private String desc;

  public static DbType getDbType(String type) {
    DbType[] dbTypes = DbType.values();
    for (int i = 0; i < dbTypes.length; i++) {
      DbType dbType = dbTypes[i];
      if (dbType.db.equalsIgnoreCase(type)) {
        return dbType;
      }
    }
    return OTHER;
  }

  DbType(String db, String desc) {
    this.db = db;
    this.desc = desc;
  }
}
