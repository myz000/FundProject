package com.demo;

import com.demo.api.JuHeApiService;
import com.demo.api.ShowApiService;
import com.demo.entity.ApiBody;
import com.demo.entity.News;
import com.demo.entity.NoteBody;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ApiTest extends BaseTest {
    ShowApiService showApiService = new ShowApiService();
    JuHeApiService juHeApiService = new JuHeApiService();

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

}
