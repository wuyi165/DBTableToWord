package com.think.tools.dbtoword.db;

import com.think.tools.dbtoword.mode.TableFieldInfo;
import com.think.tools.dbtoword.mode.TablesInfo;

import java.sql.Connection;
import java.util.List;

public interface FindTableInterface {

    public List<TablesInfo>  findDBTables(Connection conn, String database);
    public List<TableFieldInfo>  findTableField(Connection conn, String table);
}
