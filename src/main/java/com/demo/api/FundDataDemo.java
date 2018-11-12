package com.demo.api;

import com.demo.entity.Fund;
import com.show.api.ShowApiRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FundDataDemo {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

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
            result = net(url, params, "GET");
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
    public static void getRequest2() {
        String result = null;
        String url = "http://web.juhe.cn:8080/fund/findata/size";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", APPKEY);//APPKEY值

        try {
            result = net(url, params, "GET");
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
    public static void getRequest3() {
        String result = null;
        String url = "http://web.juhe.cn:8080/fund/findata/config";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", APPKEY);//APPKEY值

        try {
            result = net(url, params, "GET");
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


    public static void reguest4(String code) {
        String result = null;
        String url = "http://apis.haoservice.com/lifeservice/fund/info/";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("code", code);
        params.put("key", "92cd42e06c324e08b686027c8e44924e");//APPKEY值

        try {
            result = net(url, params, "GET");
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
            return null;
        } else {
            JSONObject showapi_res_body = JSONObject.fromObject(object.get("showapi_res_body"));
            JSONArray data = JSONArray.fromObject(showapi_res_body.get("data"));
            JSONObject fundInfor = data.getJSONObject(0);
            // JSONObject details = JSONObject.fromObject(fundInfor.get("details"));
            return fundInfor;
        }
    }





   /* public static void main(String[] args) {
          JSONObject details=getFundDetails("","万家精选");
        //  System.out.println(details);
          JSONArray managerList = JSONArray.fromObject(details.get("managerList"));
          JSONObject manager = managerList.getJSONObject(0);

          System.out.println(manager.get("managerName"));
           System.out.println(manager.get("profession"));
      /*  ArrayList<Fund> FundList=new ArrayList<>();
         FundList= (ArrayList<Fund>)getRequest1();
         int i;
         for(i=0;i<FundList.size();i++){
             Fund fund=FundList.get(i);
            System.out.println(fund.getCode()+"-"+fund.getName()+"-"+fund.getAssincome());
         }*/

    //  reguest4();
    // getRequest2();
    //  }

    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
