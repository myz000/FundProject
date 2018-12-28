package com.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class NetValue {
    private String flag;             //接口操作是否成功
    private String ret_code;         //接口调用是否成功 0为成功,其他为失败
    private String msg;              //提示信息
    private String fundCode;         //同输入参数 基金代码
    private String date;             //同输入参数
    private List<netData> data;      //净值列表

    @lombok.Data
    public static class netData {
        private String date;        //日期
        private Double unitVal;     //单位净值
        private Double leiJiVal;    //累计净值
    }
}
