package com.demo.entity.apiBody;

import lombok.Data;

@Data
public class NoteBody {
    private String reason;
    private Result result;
    private String error_code;    //0-发送成功

    @Data
    public static class Result {
        private int count;      //发送数量
        private int fee;        //扣除条数
        private String sid;     //短信ID
    }
}
