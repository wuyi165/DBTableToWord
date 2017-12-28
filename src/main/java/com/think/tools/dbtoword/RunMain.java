package com.think.tools.dbtoword;

import com.google.gson.Gson;
import com.think.tools.dbtoword.db.FindTableInterface;
import com.think.tools.dbtoword.db.QueryTableInfo;
import com.think.tools.dbtoword.db.impl.MysqlFindTableImpl;
import com.think.tools.dbtoword.mode.TableFieldInfo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RunMain {

    public static void main(String[] args) {
        //这里没有指定数据库
        String url = "jdbc:mysql://10.173.44.29:3306/4thstandard";
        String user = "coder";
        String pass = "Deloitte12#$";
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, user, pass);
            //DatabaseMetaData metadata = conn.getMetaData();

            FindTableInterface find = new MysqlFindTableImpl();
            List<TableFieldInfo>  list =   find.findTableField(conn,"user");
            System.out.println(new Gson().toJson(list));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

