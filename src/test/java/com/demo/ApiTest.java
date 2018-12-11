package com.demo;

import com.demo.api.FundApiService;
import com.demo.api.JuHeApiService;
import com.demo.api.ShowApiService;
import com.demo.entity.Fund;
import com.demo.entity.FundDetailInfo;
import com.demo.entity.News;
import com.demo.entity.apiBody.ApiBody;
import com.demo.entity.apiBody.FundListApiBody;
import com.demo.entity.apiBody.NoteBody;
import lombok.Data;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class ApiTest extends BaseTest {
    @Autowired
    ShowApiService showApiService;
    @Autowired
    JuHeApiService juHeApiService;
    @Autowired
    FundApiService fundApiService;

    @Test
    public void NewsApiTest() {
        ApiBody apiBody = showApiService.getNews(1, 20);
        News news = apiBody.getShowapi_res_body();
        News.PageBean pageBean = news.getPagebean();
        System.out.println(pageBean.getContentlist().get(0).getTitle());
    }

    @Test
    public void testNoteApi() {
        int code = (int) ((Math.random() * 9 + 1) * 1000);
        System.out.println("code:" + code);
        NoteBody noteBody = juHeApiService.getNoteBody(code, "15800989215");
    }

    @Test
    public void testGetFundDetails() {
        FundDetailInfo fundDetailInfo = fundApiService.getFundDetails("");

    }

    @Test
    public void testFundList() {
        FundListApiBody fundListApiBody = fundApiService.getFundList();
        Object object = fundListApiBody.getResult().get(0);
        JSONObject array = JSONObject.fromObject(object);
        List list = new ArrayList();
        for (int i = 1; i <= array.size(); i++) {
            JSONObject ff = (JSONObject) array.get("" + i + "");
            Fund fund = (Fund) JSONObject.toBean(ff, Fund.class);
            list.add(fund);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    @Data
    public class CC {
        List<Fund> a;
    }
}
