package com.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_trend")
@Data
public class Trend {
    @Id
    private long id;                          //自增ID
    private String fundcode;                  //基金代码
    private String currentdate;               //当前日期
    private double property;                  //资产
    private double zhangdiefu;                //涨跌幅
    private double chicangyingkui;            //持仓盈亏
    private int investdays;                   //定投天数
    private float shouyirate;                 //收益率
    private double shourinianhua;             //首日年化
    private double xirr;                      //xirr年化
    private double profit;                    //本轮收益
    private double investcost;                //投入成本
    private long userid;                      //用户Id
    private int state;                        //状态    0：已停止      1：定投中

    public Trend(long id, String fundcode, String currentdate, double property, double zhangdiefu, double chicangyingkui, int investdays, float shouyirate, double shourinianhua, double xirr, double profit, double investcost, long userid, int state) {
        this.id = id;
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
}
