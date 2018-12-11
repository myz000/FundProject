package com.demo.service.Impl;

import com.demo.api.FundApiService;
import com.demo.entity.Fund;
import com.demo.entity.FundDetailInfo;
import com.demo.entity.apiBody.FundListApiBody;
import com.demo.service.FundService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FundServiceImpl implements FundService {
    @Autowired
    FundApiService fundApiService;

    @Override
    public List<Fund> getFundList() {
        FundListApiBody fundApiBody = fundApiService.getFundList();
        if (!fundApiBody.getResultcode().equals("200")) {
            return null;
        }
        Object object = fundApiBody.getResult().get(0);
        JSONObject array = JSONObject.fromObject(object);
        List fundList = new ArrayList();
        for (int i = 1; i <= array.size(); i++) {
            JSONObject ff = (JSONObject) array.get("" + i + "");
            Fund fund = (Fund) JSONObject.toBean(ff, Fund.class);
            fundList.add(fund);
        }
        return fundList;
    }

    @Override
    public FundDetailInfo getFundDetail(String fundCode) {
        return fundApiService.getFundDetails(fundCode);
    }
}
