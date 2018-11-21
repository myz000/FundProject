package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class News {
    private PageBean pagebean;
    private String ret_code;
    private String error_message;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PageBean {
        private Long allNum;
        private Long allPages;
        private Long currentPage;
        private Long maxResult;
        private List<NewsItem> contentlist;
    }


    @Data
    public static class NewsItem {
        private String link;
        private String id;
        private String channelId;
        private String nid;
        private boolean havePic;
        private String pubDate;
        private String title;
        private String source;
        private List<Img> imageurls;
        private String desc;
        private String channelName;
    }

    @Data
    public static class Img {
        private String url;
        private Integer height;
        private Integer width;
    }
}
