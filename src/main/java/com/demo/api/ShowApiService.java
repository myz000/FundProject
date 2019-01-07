package com.demo.api;

import com.demo.entity.NetValue;
import com.demo.entity.News;
import com.demo.entity.apiBody.ApiBody;
import com.show.api.ShowApiRequest;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ShowApiService {
    private String my_appId = "63423";
    private String my_appSecret = "c686c8708f3048abbcc326b2d792c4fb";

    public ApiBody getNews(int page, int maxResult) {
        String channelId = "5572a108b3cdc86cf39001d0";
        String res = new ShowApiRequest("http://route.showapi.com/109-35", my_appId, my_appSecret)
                .addTextPara("channelId", channelId)
                .addTextPara("page", String.valueOf(page))
                .addTextPara("needContent", "0")
                .addTextPara("needHtml", "1")
                .addTextPara("needAllList", "0")
                .addTextPara("maxResult", String.valueOf(maxResult))
                .post();
        JSONObject body = JSONObject.fromObject(res);
        Map<String, Class> classMap = new HashMap<String, Class>();
        classMap.put("contentlist", News.NewsItem.class);
        classMap.put("imageurls", News.Img.class);
        classMap.put("feedback_tag", News.FeedBackTag.class);
        ApiBody apiBody = (ApiBody) JSONObject.toBean(body, ApiBody.class, classMap);
        return apiBody;
    }

    public NetValue getNetValue(String fundCode, String date) {
        String res = new ShowApiRequest("http://route.showapi.com/902-4", my_appId, my_appSecret)
                .addTextPara("fundCode", fundCode)
                .addTextPara("date", date)
                .post();
        JSONObject body = JSONObject.fromObject(res);
        JSONObject showapi_res_body = body.getJSONObject("showapi_res_body");
        Map<String, Class> classMap = new HashMap<String, Class>();
        classMap.put("data", NetValue.netData.class);
        return (NetValue) JSONObject.toBean(showapi_res_body, NetValue.class, classMap);
    }
}
