package com.think.tools.dbtoword;

import com.google.gson.Gson;
import com.think.tools.dbtoword.db.FindTableInterface;
import com.think.tools.dbtoword.db.impl.MysqlFindTableImpl;
import com.think.tools.dbtoword.mode.TableFieldInfo;
import com.think.tools.dbtoword.mode.TablesInfo;
import com.think.tools.dbtoword.util.PropertiesUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunMain {

    public static void main(String[] args) {
        //这里没有指定数据库
//        String url = "jdbc:mysql://10.173.44.29:3306/4thstandard";
//        String user = "coder";
//        String pass = "Deloitte12#$";
//        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            Connection conn = DriverManager.getConnection(url, user, pass);
//            FindTableInterface find = new MysqlFindTableImpl();
//            List<TableFieldInfo> list = find.findTableField(conn, "user");
//
//            System.out.println(new Gson().toJson(list));
//            List<TablesInfo> list2 = find.findDBTables(conn, "4thstandard");
//
//            System.out.println(new Gson().toJson(list2));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            RunMain main = new RunMain();
            main.renderingTemplate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void renderingTemplate() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException, IOException, TemplateException {
        PropertiesUtil util=new PropertiesUtil("/SystemConfig.properties");
        Connection conn = DriverManager.getConnection(util.getProperty("db.url"), util.getProperty("db.user"), util.getProperty("db.pass"));
        FindTableInterface find = new MysqlFindTableImpl();
        List<TablesInfo> list2 = find.findDBTables(conn, util.getProperty("db.database"));
        System.out.println(new Gson().toJson(list2));


        Configuration freeMarkerCfg = new Configuration();
        freeMarkerCfg.setDirectoryForTemplateLoading(new File("temp"));
        freeMarkerCfg.setObjectWrapper(new DefaultObjectWrapper());
        Template  template = freeMarkerCfg.getTemplate("temp.xml");
        Map<String, Object> parameters = new HashMap<String, Object>();
        for(TablesInfo info :list2){
            List<TableFieldInfo> list = find.findTableField(conn, info.name);
            info.fields  = list;

        }

        parameters.put("tables", list2);
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("C:\\Users\\Public\\Desktop\\table.doc"), "UTF-8");
        template.process(parameters, writer);
        writer.flush();
    }

}

