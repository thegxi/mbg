package cn.gx.mbg.utils;

import cn.gx.mbg.enums.DbType;
import cn.gx.mbg.exception.MbgBusinessException;
import org.apache.commons.lang3.StringUtils;

/**
 * 处理Url
 * 2022/12/08 2:31 pm
 *
 * @author thegx
 */
public class DbUrlUtil {
  public static String getHostAndPort(String url) {
    if (StringUtils.isBlank(url)) {
      return null;
    }
    int lastSeparatorIndex = url.lastIndexOf("/");
    if (lastSeparatorIndex == -1) {
      throw new MbgBusinessException("url格式不正确");
    }
    int lastSecondSeparatorIndex = url.substring(0, lastSeparatorIndex).lastIndexOf("/");
    if (lastSecondSeparatorIndex == -1) {
      throw new MbgBusinessException("url格式不正确");
    }
    String subUrl = url.substring(lastSecondSeparatorIndex, lastSeparatorIndex);
    String[] stringArr = subUrl.split(":");
    if (stringArr == null || stringArr.length <= 1) {
      throw new MbgBusinessException("url格式不正确");
    }
    return subUrl;
  }

  public static String getDbType(String url) {
    if (StringUtils.isBlank(url)) {
      return null;
    }
    int firstSeparatorIndex = url.indexOf("/");
    if (firstSeparatorIndex == -1) {
      throw new MbgBusinessException("url格式不正确");
    }
    String subUrl = url.substring(0, firstSeparatorIndex);
    String[] stringArr = subUrl.split(":");
    if (stringArr == null || stringArr.length <= 1) {
      throw new MbgBusinessException("url格式不正确");
    }
    return stringArr[1];
  }

  public static String getDbUrlPrefixByType(DbType dbType) {
    String dbUrl = "";
    switch (dbType) {
      case MYSQL:
        dbUrl = "jdbc:mysql://";
        break;
      case ORACLE:
        dbUrl = "jdbc:oracle:thin:@";
        break;
      case SQL_SERVER:
        dbUrl = "jdbc:sqlserver://";
        break;
      default:
        throw new MbgBusinessException("不支持的数据库类型");
    }
    return dbUrl;
  }
}
