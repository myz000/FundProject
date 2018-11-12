package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class ApiBody {
    private String showapi_res_body;
    private int showapi_res_code;
    private String showapi_res_error;
    private String showapi_res_id;
}
