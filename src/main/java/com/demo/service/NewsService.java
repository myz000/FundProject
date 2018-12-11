package com.demo.service;

import com.demo.entity.News;

public interface NewsService {
    public News getNews(int page, int maxResult);
}
