package com.think.tools.dbtoword.config;

import org.aeonbits.owner.Config;

public interface SystemConfig extends Config {
    @Key("db.url")
    String dbUrl();
    @Key("db.user")
    String dbUser();
    @Key("db.pass")
    String dbPass();
}
