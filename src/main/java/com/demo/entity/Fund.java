package com.demo.entity;

import lombok.Data;

@Data
public class Fund {
    String code;
    String name;
    String netincome;
    String assincome;
    String netassrate;
    String netgrowratel;
    String tonetgrora;
    String time;

    public Fund(String code, String name, String netincome, String assincome, String netassrate, String netgrowratel, String tonetgrora, String time) {
        this.code = code;
        this.name = name;
        this.netincome = netincome;
        this.assincome = assincome;
        this.netassrate = netassrate;
        this.netgrowratel = netgrowratel;
        this.tonetgrora = tonetgrora;
        this.time = time;
    }

    public Fund() {

    }
}
