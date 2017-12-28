package com.think.tools.dbtoword.db.impl;

import com.think.tools.dbtoword.db.FindTableInterface;
import com.think.tools.dbtoword.mode.TableFieldInfo;
import com.think.tools.dbtoword.mode.TablesInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MysqlFindTableImpl implements FindTableInterface {

    public List<TablesInfo> findDBTables(Connection conn, String database) {
    String sql ="select table_name,table_comment \n" +
            "from information_schema.tables \n" +
            "where table_schema='"+database+"'";
        List<TablesInfo>  list = new LinkedList<TablesInfo>();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                TablesInfo info = new TablesInfo();
                info.name = rs.getString("table_name");
                info.comment = rs.getString("table_comment");

                list.add(info);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public List<TableFieldInfo> findTableField(Connection conn, String table) {
        String sql  =" show full columns from "+table;
        List<TableFieldInfo>  list = new LinkedList<TableFieldInfo>();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                TableFieldInfo info = new TableFieldInfo();
                info.name = rs.getString("Field");
                info.type = rs.getString("Type");
                info.label = rs.getString("Comment");
                info.isnull = "YES".equals(rs.getString("Null"))?true:false;
                info.key = "PRI".equals(rs.getString("Key"))?true:false;
                list.add(info);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
