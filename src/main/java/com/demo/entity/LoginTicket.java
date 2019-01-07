package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tbl_loginticket")
public class LoginTicket {
    @Id
    private long id;
    private String userid;
    private Date expired;
    private int status;
    private String ticket;

    public void LoginTicket() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserid(String userid) {
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

    public String getUserid() {
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
