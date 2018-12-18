package com.demo.entity;

import javax.persistence.*;


@Entity
@Table(name="tbl_invest")
public class Invest {
    @Id
    private String id;                  //ID 自增
    private String fundcode;         //基金代码
    private String fundname;
    private String firstdate;       //定投首日日期
    private String investmode;      //定投方式（日投、周投、月投）
    private double amountofinvest;  //每次定投金额
    private double alreadyincome;  //已实现收益
    private int state;             //状态       -1：未完结   0-已停止   1-定投中
    private long userid;           //用户ID
    private float fee;             //卖出时手续费  （0~1）
    private int receiveddays;      //卖出到账天数
    private int delaydays;         //结算延迟天数
    private double unfinish;       //未完结金额
    private String platform;

    public Invest(String id, String fundcode, String firstdate, String investmode, double amountofinvest, double alreadyincome, int state, long userid, float fee, int receiveddays, int delaydays, double unfinish, String platform) {
        this.id = id;
        this.fundcode = fundcode;
        this.firstdate = firstdate;
        this.investmode = investmode;
        this.amountofinvest = amountofinvest;
        this.alreadyincome = alreadyincome;
        this.state = state;
        this.userid = userid;
        this.fee = fee;
        this.receiveddays = receiveddays;
        this.delaydays = delaydays;
        this.unfinish=unfinish;
        this.platform=platform;
    }

    public Invest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    public String getFirstdate() {
        return firstdate;
    }

    public void setFirstdate(String firstdate) {
        this.firstdate = firstdate;
    }

    public String getInvestmode() {
        return investmode;
    }

    public void setInvestmode(String investmode) {
        this.investmode = investmode;
    }

    public double getAmountofinvest() {
        return amountofinvest;
    }

    public void setAmountofinvest(double amountofinvest) {
        this.amountofinvest = amountofinvest;
    }

    public double getAlreadyincome() {
        return alreadyincome;
    }

    public void setAlreadyincome(double alreadyincome) {
        this.alreadyincome = alreadyincome;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public int getReceiveddays() {
        return receiveddays;
    }

    public void setReceiveddays(int receiveddays) {
        this.receiveddays = receiveddays;
    }

    public int getDelaydays() {
        return delaydays;
    }

    public void setDelaydays(int delaydays) {
        this.delaydays = delaydays;
    }
    public double getUnfinish() {
        return unfinish;
    }

    public void setUnfinish(double unfinish) {
        this.unfinish = unfinish;
    }
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

}
