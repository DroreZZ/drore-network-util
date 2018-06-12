package com.drore.cloud.tdp.entity;

import java.util.Date;
import java.util.UUID;

public class DeviceStatus {
    private String id;

    private String ip;

    private String devicetype;

    private String isconnect;

    private Date updatetime;

    private String cron;

    public String getId() {
        id= UUID.randomUUID().toString().replace("-","");
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype == null ? null : devicetype.trim();
    }

    public String getIsconnect() {
        return isconnect;
    }

    public void setIsconnect(String isconnect) {
        this.isconnect = isconnect == null ? null : isconnect.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron == null ? null : cron.trim();
    }
}