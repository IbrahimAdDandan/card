package com.example.smartcard.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class SmartCard {

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
    private String status = "VALID";
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date expireDate;
    @Column(nullable = true)
    private double ammount;
    private double restAmmount;
    @OneToMany(mappedBy = "card")
    private List<FillOperation> fillOperations;
    @ManyToOne
    private User user;
    @OneToMany( mappedBy = "card" )
    private List<FraudOperation> fraudOperations;

    public SmartCard() {
    }

    public SmartCard(String full_name, String address, Long nationalId, int phone, Date expireDate, double ammount, double restAmmount) {
        this.full_name = full_name;
        this.address = address;
        this.nationalId = nationalId;
        this.phone = phone;
        this.expireDate = expireDate;
        this.ammount = ammount;
        this.restAmmount = restAmmount;
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
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    
    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public double getRestAmmount() {
        return restAmmount;
    }

    public void setRestAmmount(double restAmmount) {
        this.restAmmount = restAmmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<FillOperation> getFillOperations() {
        return fillOperations;
    }

    public void setFillOperations(List<FillOperation> fillOperations) {
        this.fillOperations = fillOperations;
    }

    public List<FraudOperation> getFraudOperations() {
        return fraudOperations;
    }

    public void setFraudOperations(List<FraudOperation> fraudOperations) {
        this.fraudOperations = fraudOperations;
    }
    
}
