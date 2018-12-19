package com.demo.entity;

import lombok.Data;

@Data
public class showTrend {
    private String fundcode;
    private String fundname;
    private int investdays;  //定投天数
    private float shouyirate; //收益率
    private double shourinianhua;//首日年化
    private double xirr; //xirr年化
    private double profit;//
    private double investcost; // 投入成本
    private String platform;
    private String date;     //更新日期
    private String investid;

    public showTrend(String fundcode, String fundname, int investdays, float shouyirate, double shourinianhua, double xirr, double profit, double investcost, String platform, String date, String investid) {
        this.fundcode = fundcode;
        this.fundname = fundname;
        this.investdays = investdays;
        this.shouyirate = shouyirate;
        this.shourinianhua = shourinianhua;
        this.xirr = xirr;
        this.profit = profit;
        this.investcost = investcost;
        this.platform=platform;
        this.date = date;
        this.investid = investid;
    }

    public showTrend() {
    }

}
