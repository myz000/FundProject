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
    private String investid;
    private double payments;
    private double unitval;                   //单位净值
    private double unitshare;                 //本次份额
    private double totalshare;                //累计份额

    public Trend() {

    }
}
