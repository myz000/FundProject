package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_trend")
public class Trend {
    @Id
    private long id;  //自增
    private String fundcode;  //基金代码
    private String currentdate;   //当前日期
    private double property;   //资产
    private double zhangdiefu;  //涨跌幅
    private double chicangyingkui; //持仓盈亏
    private int investdays;  //定投天数
    private float shouyirate; //收益率
    private double shourinianhua;//首日年化
    private double xirr; //xirr年化
    private double profit;//本轮收益
    private double investcost; // 投入成本
    private long userid; //用户Id
    private int state;  //状态              0：已停止      1：定投中

    public Trend(long id, String fundcode, String currentdate, double property, double zhangdiefu, double chicangyingkui, int investdays, float shouyirate, double shourinianhua, double xirr, double profit, double investcost, long userid, int state) {
        this.id=id;
        this.fundcode = fundcode;
        this.currentdate = currentdate;
        this.property = property;
        this.zhangdiefu = zhangdiefu;
        this.chicangyingkui = chicangyingkui;
        this.investdays = investdays;
        this.shouyirate = shouyirate;
        this.shourinianhua = shourinianhua;
        this.xirr = xirr;
        this.profit = profit;
        this.investcost = investcost;
        this.userid = userid;
        this.state = state;
    }

    public Trend() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getFundcode() {
        return fundcode;
    }

    public void setFundcode(String fundcode) {
        this.fundcode = fundcode;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public double getProperty() {
        return property;
    }

    public void setProperty(double property) {
        this.property = property;
    }

    public double getZhangdiefu() {
        return zhangdiefu;
    }

    public void setZhangdiefu(double zhangdiefu) {
        this.zhangdiefu = zhangdiefu;
    }

    public double getChicangyingkui() {
        return chicangyingkui;
    }

    public void setChicangyingkui(double chicangyingkui) {
        this.chicangyingkui = chicangyingkui;
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

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
