package com.demo.entity;

public class Fund {
    String code;
    String name;
    String netincome;
    String assincome;
    String netassrate;
    String netgrowrate;
    String tonetgrora;
    String time;

    public Fund(String code, String name, String netincome, String assincome, String netassrate, String netgrowrate, String tonetgrora, String time) {
        this.code = code;
        this.name = name;
        this.netincome = netincome;
        this.assincome = assincome;
        this.netassrate = netassrate;
        this.netgrowrate = netgrowrate;
        this.tonetgrora = tonetgrora;
        this.time = time;
    }

    public Fund() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetincome() {
        return netincome;
    }

    public void setNetincome(String netincome) {
        this.netincome = netincome;
    }

    public String getAssincome() {
        return assincome;
    }

    public void setAssincome(String assincome) {
        this.assincome = assincome;
    }

    public String getNetassrate() {
        return netassrate;
    }

    public void setNetassrate(String netassrate) {
        this.netassrate = netassrate;
    }

    public String getNetgrowrate() {
        return netgrowrate;
    }

    public void setNetgrowrate(String netgrowrate) {
        this.netgrowrate = netgrowrate;
    }

    public String getTonetgrora() {
        return tonetgrora;
    }

    public void setTonetgrora(String tonetgrora) {
        this.tonetgrora = tonetgrora;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
