package com.demo.service;

import com.demo.entity.News;

public interface NewsService {
    News getNews(int page, int maxResult);
}
