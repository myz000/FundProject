package com.demo.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="tbl_loginticket")
public class LoginTicket {
       @Id
       private long id;
       private long userid;
       private Date expired;
       private int status;
       private String ticket;

    public void LoginTicket(){}

    public void setId(long id) {
        this.id = id;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public long getId() {
        return id;
    }

    public long getUserid() {
        return userid;
    }

    public Date getExpired() {
        return expired;
    }

    public int getStatus() {
        return status;
    }

    public String getTicket() {
        return ticket;
    }
}
