package com.lxseason.mybatis.config;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private String jdbcDriver;
    private String jdbcUrl;
    private String jdbcUserName;
    private String jdbcPassword;
    private Map<String ,MappedStatment> mappedStatementMap = new HashMap<String,MappedStatment>();

    public Map<String, MappedStatment> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatment> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUserName() {
        return jdbcUserName;
    }

    public void setJdbcUserName(String jdbcUserName) {
        this.jdbcUserName = jdbcUserName;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }
}
