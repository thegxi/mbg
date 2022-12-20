package cn.gx.mbg.service.impl;

import cn.gx.mbg.entity.Schema;
import cn.gx.mbg.entity.Table;
import cn.gx.mbg.service.SchemaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 2022/12/09 10:10 am
 *
 * @author thegx
 */
@Slf4j
@Service
public class SchemaServiceImpl implements SchemaService {
  @Override
  public List<Schema> listSchema(Integer pageIndex, Integer pageSize, HttpServletRequest request) {
    Connection curCon = (Connection) request.getSession().getAttribute("curCon");
    List<Schema> schemaList = new ArrayList<>();
    String sql = String.format("select schema_name from information_schema.schemata");
    try {
      PreparedStatement preparedStatement = curCon.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        String schema_name = resultSet.getString("schema_name");
        Schema schema = new Schema();
        if (StringUtils.isNotBlank(schema_name)) {
          List<Table> tableList = new ArrayList<>();
          String tableSql = String.format("select table_name,table_comment from information_schema.tables where TABLE_SCHEMA = '%s'", schema_name);
          PreparedStatement tablePrep = curCon.prepareStatement(tableSql);
          ResultSet tabResultSet = tablePrep.executeQuery();
          while(tabResultSet.next()) {
            Table table = Table.builder().name(tabResultSet.getString("table_name")).comment(tabResultSet.getString("table_comment")).build();
            tableList.add(table);
          }
          schema.setTableList(tableList);
          System.out.println(tabResultSet);
        }
        schema.setName(schema_name);
        schemaList.add(schema);
      }
      System.out.println(resultSet);
    } catch (SQLException e) {
      log.error("Connection failed, detail {}", e.getMessage());
      throw new RuntimeException(e);
    }
    return schemaList;
  }
}
