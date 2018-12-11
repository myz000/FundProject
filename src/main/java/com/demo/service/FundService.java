package com.demo.service;

import com.demo.entity.Fund;
import com.demo.entity.FundDetailInfo;

import java.util.List;


public interface FundService {
    List<Fund> getFundList();

    FundDetailInfo getFundDetail(String fundCode);
}
