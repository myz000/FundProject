package com.demo.service.Impl;

import com.demo.api.ShowApiService;
import com.demo.entity.News;
import com.demo.entity.apiBody.ApiBody;
import com.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsServiceImpl implements NewsService {
    @Autowired
    ShowApiService showApiService;

    @Override
    public News getNews(int page, int maxResult) {
        ApiBody apiBody = showApiService.getNews(page, maxResult);
        News news = new News();
        if (apiBody.getShowapi_res_code() != 0) {
            news.setRet_code("1");
            news.setError_message(apiBody.getShowapi_res_error());
            return news;
        } else {
            return apiBody.getShowapi_res_body();
        }
    }
}
