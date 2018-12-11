package com.demo.entity.apiBody;

import com.demo.entity.Fund;
import lombok.Data;

import java.util.List;

@Data
public class FundListApiBody {
    private String resultcode;
    private String reason;
    private List<Fund> result;
}
