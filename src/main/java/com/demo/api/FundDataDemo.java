package com.demo.api;

import com.demo.entity.Fund;
import com.show.api.ShowApiRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FundDataDemo {
    @Autowired
    JuHeApiService juHeApiService;
    //配置您申请的KEY
    public static final String APPKEY = "f8114d69d2bf65726b0c2dcf153e948b";

    //1.主要财务指标
    public List<Fund> getRequest1() {
        String result = null;
        String url = "http://web.juhe.cn:8080/fund/findata/main";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", APPKEY);//APPKEY值
        List<Fund> list = new ArrayList<Fund>();
        try {
            result = juHeApiService.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                System.out.println("读取完毕！");
                //获取"persons"的json对象,并将其转换为一个json数组
                JSONArray RR = JSONArray.fromObject(object.get("result"));
                JSONObject array = RR.getJSONObject(0);
                //通过循环获取数据,并放入list集合中
                for (int i = 1; i <= array.size(); i++) {
                    JSONObject ff = (JSONObject) array.get("" + i + "");
                    String code = (String) ff.get("code");
                    String name = (String) ff.get("name");
                    String netincome = (String) ff.get("netincome");
                    String assincome = (String) ff.get("assincome");
                    String netassrate = (String) ff.get("netassrate");
                    String netgrowrate = (String) ff.get("netgrowrate");
                    String tonetgrora = (String) ff.get("tonetgrora");
                    String time = (String) ff.get("time");
                    Fund fund = new Fund(code, name, netincome, assincome, netassrate, netgrowrate, tonetgrora, time);
                    list.add(fund);
                }
                return list;
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //2.基金规模
    public void getRequest2() {
        String result = null;
        String url = "http://web.juhe.cn:8080/fund/findata/size";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", APPKEY);//APPKEY值

        try {
            result = juHeApiService.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.资产配置
    public void getRequest3() {
        String result = null;
        String url = "http://web.juhe.cn:8080/fund/findata/config";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", APPKEY);//APPKEY值

        try {
            result = juHeApiService.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void reguest4(String code) {
        String result = null;
        String url = "http://apis.haoservice.com/lifeservice/fund/info/";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("code", code);
        params.put("key", "92cd42e06c324e08b686027c8e44924e");//APPKEY值

        try {
            result = juHeApiService.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static JSONObject getFundDetails(String Fundcode, String FundName) {
        String my_appId = "63423";
        String my_appSercet = "c686c8708f3048abbcc326b2d792c4fb";
        String res = new ShowApiRequest("http://route.showapi.com/902-1", my_appId, my_appSercet)
                .addTextPara("fundCode", Fundcode)
                .addTextPara("simpleName", FundName)
                .addTextPara("page", "1")
                .addTextPara("maxResult", "50")
                .addTextPara("needDetails", "1")
                .post();
        JSONObject object = JSONObject.fromObject(res);

        if (object.getInt("showapi_res_code") != 0) {
            throw new RuntimeException("调用失败！");
        } else {
            JSONObject showapi_res_body = JSONObject.fromObject(object.get("showapi_res_body"));
            if (!showapi_res_body.getBoolean("flag")) {
                throw new RuntimeException(showapi_res_body.getString("msg"));
            }
            JSONArray data = JSONArray.fromObject(showapi_res_body.get("data"));
            JSONObject fundInfor = data.getJSONObject(0);
            // JSONObject details = JSONObject.fromObject(fundInfor.get("details"));
            return fundInfor;
        }
    }
}
