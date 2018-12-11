package com.demo.entity.apiBody;

import com.demo.entity.News;
import lombok.Data;

@Data
public class ApiBody {
    private News showapi_res_body;
    private int showapi_res_code;
    private String showapi_res_error;
    private String showapi_res_id;
}
