package com.demo.api;

import com.demo.entity.FundDetailInfo;
import com.demo.entity.apiBody.FundListApiBody;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FundApiService {
    @Autowired
    JuHeApiService juHeApiService;

    public FundListApiBody getFundList() {
        String APPKEY = "f8114d69d2bf65726b0c2dcf153e948b";
        String result;
        String url = "http://web.juhe.cn:8080/fund/findata/main";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", APPKEY);//APPKEY值
        try {
            result = juHeApiService.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("result", Map.class);
            FundListApiBody fundListApiBody = (FundListApiBody) JSONObject.toBean(object, FundListApiBody.class);
            return fundListApiBody;
        } catch (Exception e) {
            e.printStackTrace();
            FundListApiBody fundListApiBody = new FundListApiBody();
            fundListApiBody.setReason(e.toString());
            fundListApiBody.setResultcode("404");
            return fundListApiBody;
        }
    }


    public FundDetailInfo getFundDetails(String fundcode) {
        String host = "http://fund.market.alicloudapi.com";
        String path = "/getFundDetail";
        String method = "GET";
        String appcode = "3ab1a90422be48ce8da1777879315f80";

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("fundcode", fundcode);

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);

            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put("fundFullInfo", FundDetailInfo.FundFullInfo.class);
            classMap.put("fundGrades", FundDetailInfo.FundGrade.class);
            classMap.put("fundManagers", FundDetailInfo.FundManager.class);
            JSONObject m = JSONObject.fromObject(EntityUtils.toString(response.getEntity()));
            FundDetailInfo fundDetailInfor = (FundDetailInfo) JSONObject.toBean(m, FundDetailInfo.class, classMap);
            return fundDetailInfor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
