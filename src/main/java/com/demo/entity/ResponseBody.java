package com.demo.entity;

import lombok.Data;

@Data
public class ResponseBody {
    private String msg;
    private Integer status;
    private Object data;
}
