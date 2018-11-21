package com.demo.service;

import com.demo.entity.News;

public interface ShowApiService {
    public News getNews(int page, int maxResult);
}
