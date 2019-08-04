package com.example.smartcard.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Complaint {
    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private String type;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date createdDate;
    private String email;
    private String title;
    private String message;
    private boolean manipulated = false;
    private String notice;

    public Complaint() {
    }

    public Complaint(Long id, String address, String type, Date createdDate, String email, String title, String message, String notice) {
        this.id = id;
        this.address = address;
        this.type = type;
        this.createdDate = createdDate;
        this.email = email;
        this.title = title;
        this.message = message;
        this.manipulated = false;
        this.notice = notice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isManipulated() {
        return manipulated;
    }

    public void setManipulated(boolean manipulated) {
        this.manipulated = manipulated;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
    
}
