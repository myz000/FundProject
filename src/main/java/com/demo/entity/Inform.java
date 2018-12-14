package com.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tbl_inform")
@Data
public class Inform {
    @Id
    private Long id;
    private String title;       //标题
    private Date time;       //发表时间
    private String content;     //内容
}
