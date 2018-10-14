package com.demo.entity;

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

    public showTrend(String fundcode, String fundname, int investdays, float shouyirate, double shourinianhua, double xirr, double profit, double investcost,String platform) {
        this.fundcode = fundcode;
        this.fundname = fundname;
        this.investdays = investdays;
        this.shouyirate = shouyirate;
        this.shourinianhua = shourinianhua;
        this.xirr = xirr;
        this.profit = profit;
        this.investcost = investcost;
        this.platform=platform;
    }

    public showTrend() {
    }

    public String getFundcode() {
        return fundcode;
    }

    public void setFundcode(String fundcode) {
        this.fundcode = fundcode;
    }

    public String getFundname() {
        return fundname;
    }

    public void setFundname(String fundname) {
        this.fundname = fundname;
    }

    public int getInvestdays() {
        return investdays;
    }

    public void setInvestdays(int investdays) {
        this.investdays = investdays;
    }

    public float getShouyirate() {
        return shouyirate;
    }

    public void setShouyirate(float shouyirate) {
        this.shouyirate = shouyirate;
    }

    public double getShourinianhua() {
        return shourinianhua;
    }

    public void setShourinianhua(double shourinianhua) {
        this.shourinianhua = shourinianhua;
    }

    public double getXirr() {
        return xirr;
    }

    public void setXirr(double xirr) {
        this.xirr = xirr;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getInvestcost() {
        return investcost;
    }

    public void setInvestcost(double investcost) {
        this.investcost = investcost;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }



}
