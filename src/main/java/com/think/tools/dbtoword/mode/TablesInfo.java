package com.think.tools.dbtoword.mode;

import java.util.List;

public class TablesInfo {
    public String name;
    public String type;
    public String  ascriptionDatabase;
    public String comment;

    public List<TableFieldInfo> fields;

    public String getName() {
        return name;
    }

    public List<TableFieldInfo> getFields() {
        return fields;
    }

    public void setFields(List<TableFieldInfo> fields) {
        this.fields = fields;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAscriptionDatabase() {
        return ascriptionDatabase;
    }

    public void setAscriptionDatabase(String ascriptionDatabase) {
        this.ascriptionDatabase = ascriptionDatabase;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
