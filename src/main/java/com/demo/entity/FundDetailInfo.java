package com.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class FundDetailInfo {
    private FundFullInfo fundFullInfo;
    private List<FundGrade> fundGrades;
    private List<FundManager> fundManagers;

    @Data
    public static class FundFullInfo {
        private String dayOfGrowth;                   //日增长率
        private boolean fixedDisplayStatus;           //无
        private String fundCode;                      //基金代码
        private String fundCompanyName;               //基金公司名称
        private String fundName;                       //基金名称
        private String fundNameAbbr;                    //基金简称
        private String fundNameAbbr4;                   //基金简称
        private String fundType;                       //基金类型
        private String instId;                         //无
        private boolean intelligentFixedDisplayStatus;  //无
        private String investPhilosophy;                //投资理念
        private String investStrategy;                  //基金策略
        private String lastHalfYear;                    //上半年收益率
        private String lastMonth;                       //上个月收益率
        private String lastQuarter;                     //近一个季度收益率
        private String lastWeek;                       //近一周收益率
        private String lastYear;                      //上一年度收益率
        private String manageRate;                      //买入费率
        private String netValue;                         //单位净值
        private String netValueDate;                      //最新净值日期
        private String productId;                         //产品编号
        private String redeemStatus;                      //赎回开关
        private String riskLevel;                         //风险级别
        private String saleEnable;                       //可否购买
        private String saleStatus;                         //销售状态
        private Long timeId;                              //无
        private String trusteeRate;                       //托管费
        private String typeDesc;                           //无
    }

    @Data
    public static class FundManager {
        private String background;           //背景介绍
        private String birthday;             //出生日期
        private String educationLevel;        //文化程度
        private String gender;                //性别 1-男  2-女
        private String gmtCreate;            //任职日期
        private String gmtModified;          //变动时间
        private boolean incumbent;            //无
        private String managerId;            //编号
        private String name;                 //姓名
    }

    @Data
    public static class FundGrade {
        private String grade;           //级别
        private String gradeDate;       //评级时间
        private String gradeInst;       //评级机构
        private Long timeId;            //无
    }

}
