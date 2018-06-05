package com.fly.bean.business;

import java.util.Date;

public class SystemLogMessage {
    private Integer id;

    private String name;

    private String roles;

    private String method;

    private String description;

    private String params;

    private Date startTime;

    private String sumTime;

    private String successful;

    private String exception;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles == null ? null : roles.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getSumTime() {
        return sumTime;
    }

    public void setSumTime(String sumTime) {
        this.sumTime = sumTime == null ? null : sumTime.trim();
    }

    public String getSuccessful() {
        return successful;
    }

    public void setSuccessful(String successful) {
        this.successful = successful == null ? null : successful.trim();
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception == null ? null : exception.trim();
    }
}