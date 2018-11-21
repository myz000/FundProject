package com.demo.service.Impl;

import com.demo.api.ShowApiService;
import com.demo.entity.ApiBody;
import com.demo.entity.News;
import org.springframework.beans.factory.annotation.Autowired;

public class ShowApiServiceImpl implements com.demo.service.ShowApiService {
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
