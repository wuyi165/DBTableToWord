package com.think.tools.dbtoword.db;

import com.think.tools.dbtoword.mode.TableFieldInfo;
import com.think.tools.dbtoword.mode.TablesInfo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class QueryTableInfo {


    public static List<TableFieldInfo> queryTableFiledInfo(Connection conn,String tableName){
            List<TableFieldInfo>  list = new LinkedList<TableFieldInfo>();
            String sql = "select * from " + tableName;
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData meta = rs.getMetaData();
                int columeCount = meta.getColumnCount();

                for (int i = 1; i < columeCount + 1; i++) {
                    TableFieldInfo info = new TableFieldInfo();
                    info.name = meta.getColumnName(i);
                    info.type =meta.getColumnTypeName(i);
                    info.label=  meta.getColumnLabel(i);

                    list.add(info);
//                    sbCloumns.append("字段名："+meta.getColumnName(i)+"<br/>");
//                    sbCloumns.append("类型："+meta.getColumnType(i)+"<br/>");
//                    sbCloumns.append("------------------------------<br/>");
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return  list;
    }


//    public static  List<TablesInfo>  queryTables(Connection conn, String database){
//        List<TablesInfo>  list = new LinkedList<TablesInfo>();
//        try {
//            DatabaseMetaData metadata = conn.getMetaData();
//            ResultSet rs = metadata.getTables(null, null, null,new String[] { "TABLE" });
//            while (rs.next()) {
//                TablesInfo info = new TablesInfo();
//                info.name =   rs.getString("TABLE_NAME");
//                info.type =   rs.getString("TABLE_TYPE");
//                info.ascriptionDatabase =   rs.getString("TABLE_CAT");
//                info.mark =   rs.getString("REMARKS");
//                list.add(info);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
}
