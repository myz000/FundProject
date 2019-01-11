package com.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_inform")
@Data
public class Inform {
    @Id
    @GeneratedValue
    private Long id;
    private String title;       //标题
    private String time;        //发表时间
    private String content;     //内容
    private String author;      //作者
    private int state;          //状态   1-正常  0-仅管理员可见

    public Inform() {

    }
}
