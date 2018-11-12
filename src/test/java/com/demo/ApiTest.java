package com.demo;

import com.demo.api.ApiService;
import com.demo.entity.ApiBody;
import net.sf.json.JSONObject;
import org.junit.Test;


public class ApiTest extends BaseTest {
    private ApiService apiService = new ApiService();

    @Test
    public void NewsApiTest() {
        JSONObject body = apiService.getNews(1, 20);
        ApiBody apiBody = (ApiBody) JSONObject.toBean(body);
        System.out.println(apiBody.getShowapi_res_code());
    }
}
