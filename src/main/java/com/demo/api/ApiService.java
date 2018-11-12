package com.demo.api;

import com.demo.entity.ApiBody;
import com.show.api.ShowApiRequest;
import net.sf.json.JSONObject;

public class ApiService {
    private String my_appId = "63423";
    private String my_appSercet = "c686c8708f3048abbcc326b2d792c4fb";

    public JSONObject getNews(int page, int maxResult) {
        String channelId = "5572a108b3cdc86cf39001d0";
        String res = new ShowApiRequest("http://route.showapi.com/109-35", "my_appId", "my_appSecret")
                .addTextPara("channelId", channelId)
                // .addTextPara("channelName","")
                //  .addTextPara("title","")
                .addTextPara("page", String.valueOf(page))
                .addTextPara("needContent", "0")
                .addTextPara("needHtml", "1")
                .addTextPara("needAllList", "0")
                .addTextPara("maxResult", String.valueOf(maxResult))
                //.addTextPara("id","")
                .post();
        JSONObject ff = JSONObject.fromObject(res);
        //ApiBody apiBody= (ApiBody)JSONObject.toBean(ff);
        return ff;
    }

}
