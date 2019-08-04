package com.example.smartcard.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Log {
    
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String action;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public Log() {
    }

    public Log(String username, String action) {
        this.username = username;
        this.action = action;
    }
    
    
    public Log(Long id, String username, String action, Date createdDate) {
        this.id = id;
        this.username = username;
        this.action = action;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
}
