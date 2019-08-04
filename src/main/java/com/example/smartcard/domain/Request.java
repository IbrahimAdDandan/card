package com.example.smartcard.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Request {
    
    @Id
    @GeneratedValue
    private Long id;
    private String full_name;
    private String address;
    private Long nationalId;
    private int phone;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    private boolean approval;
    private boolean manipulated = false;
    private String type;

    public Request(Long id, String full_name, String address, Long nationalId, int phone, Date createdDate, boolean approval, String type) {
        this.id = id;
        this.full_name = full_name;
        this.address = address;
        this.nationalId = nationalId;
        this.phone = phone;
        this.createdDate = createdDate;
        this.approval = approval;
        this.type = type;
    }

    public Request() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getNationalId() {
        return nationalId;
    }

    public void setNationalId(Long nationalId) {
        this.nationalId = nationalId;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public boolean isManipulated() {
        return manipulated;
    }

    public void setManipulated(boolean manipulated) {
        this.manipulated = manipulated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
