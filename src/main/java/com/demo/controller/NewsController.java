package com.demo.controller;

import com.demo.entity.News;
import com.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewsController {
    @Autowired
    NewsService newsService;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNews() {
        return "News";
    }

    @RequestMapping(value = "/newsList", method = RequestMethod.GET)
    public ResponseEntity getNewsList(int page, int pageSize) {
        News news = newsService.getNews(page, pageSize);
        return ResponseEntity.ok(news);
        //return ResponseEntity.ok(news.getPagebean().getContentlist()) ;
    }
}
