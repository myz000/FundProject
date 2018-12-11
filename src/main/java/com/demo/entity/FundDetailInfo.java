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
        private String dayOfGrowth;
        private boolean fixedDisplayStatus;
        private String fundCode;
        private String fundCompanyName;
        private String fundName;
        private String fundNameAbbr;
        private String fundNameAbbr4;
        private String fundType;
        private String instId;
        private boolean intelligentFixedDisplayStatus;
        private String investPhilosophy;
        private String investStrategy;
        private String lastHalfYear;
        private String lastMonth;
        private String lastQuarter;
        private String lastWeek;
        private String lastYear;
        private String manageRate;
        private String netValue;
        private String netValueDate;
        private String productId;
        private String redeemStatus;
        private String riskLevel;
        private String saleEnable;
        private String saleStatus;
        private Long timeId;
        private String trusteeRate;
        private String typeDesc;
    }

    @Data
    public static class FundManager {
        private String background;
        private String birthday;
        private String educationLevel;
        private String gender;
        private String gmtCreate;
        private String gmtModified;
        private boolean incumbent;
        private String managerId;
        private String name;
    }

    @Data
    public static class FundGrade {
        private String grade;
        private String gradeDate;
        private String gradeInst;
        private Long timeId;
    }

}
