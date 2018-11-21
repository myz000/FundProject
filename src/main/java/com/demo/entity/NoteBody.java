package com.demo.entity;

import lombok.Data;

@Data
public class NoteBody {
    private String reason;
    private Result result;
    private String error_code;

    @Data
    public static class Result {
        private int count;
        private int fee;
        private String sid;
    }
}
