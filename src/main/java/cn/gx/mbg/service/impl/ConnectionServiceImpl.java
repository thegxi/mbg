package cn.gx.mbg.service.impl;

import cn.gx.mbg.common.api.RespTemplate;
import cn.gx.mbg.constants.DbConstant;
import cn.gx.mbg.enums.DbType;
import cn.gx.mbg.exception.MbgBusinessException;
import cn.gx.mbg.request.ConnectionRequest;
import cn.gx.mbg.service.ConnectionService;
import cn.gx.mbg.utils.DbUrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 2022/12/07 10:50 pm
 *
 * @author thegx
 */
@Slf4j
@Service
public class ConnectionServiceImpl implements ConnectionService {
  @Override
  public RespTemplate testCon(ConnectionRequest connectionRequest, HttpServletRequest request) {
    String dbUrl = "";
    String driver = "";
    String dbType = connectionRequest.getType();
    String host = connectionRequest.getHost();
    String username = connectionRequest.getUsername();
    String pwd = connectionRequest.getPwd();

    DbType matchType = DbType.getDbType(dbType);
    switch (matchType) {
      case MYSQL:
        dbUrl = "jdbc:mysql://" + host;
        driver = DbConstant.MYSQL8_DRIVER;
        break;
      case ORACLE:
        dbUrl = "jdbc:oracle:thin:@" + host;
        driver = DbConstant.ORACLE_DRIVER;
        break;
      case SQL_SERVER:
        dbUrl = "jdbc:sqlserver://" + host;
        driver = DbConstant.SQLSERVER_DRIVER;
        break;
      default:
        throw new MbgBusinessException("不支持的数据库类型");
    }
    connection(driver, dbUrl, username, pwd, request);
    return RespTemplate.success("连接成功");
  }

  @Override
  public RespTemplate urlCon(ConnectionRequest connectionRequest, HttpServletRequest request) {
    String driver;
    String dbUrl = connectionRequest.getUrl();
    String dbType = DbUrlUtil.getDbType(dbUrl);
    String username = connectionRequest.getUsername();
    String pwd = connectionRequest.getPwd();

    DbType matchType = DbType.getDbType(dbType);
    if (matchType == DbType.MYSQL) {
    }
    switch (matchType) {
      case MYSQL:
        driver = DbConstant.MYSQL8_DRIVER;
        break;
      case ORACLE:
        driver = DbConstant.ORACLE_DRIVER;
        break;
      case SQL_SERVER:
        driver = DbConstant.SQLSERVER_DRIVER;
        break;
      default:
        return RespTemplate.fail("暂不支持的数据库类型");
    }
    connection(driver, dbUrl, username, pwd, request);
    return RespTemplate.success("连接成功");
  }

  private void connection(String driver, String dbUrl, String username, String pwd, HttpServletRequest request) {
    try {
      Class.forName(driver);
      Connection connection = DriverManager.getConnection(dbUrl, username, pwd);
      // 保存连接到当前session
      HttpSession session = request.getSession();
      session.setAttribute("curCon", connection);
      session.setMaxInactiveInterval(10000);
    } catch (ClassNotFoundException e) {
      log.error(e.getMessage());
      throw new MbgBusinessException("数据库驱动错误,请检查驱动");
    } catch (SQLException e) {
      log.error(e.getMessage());
      throw new MbgBusinessException("连接失败");
    }
  }
}
