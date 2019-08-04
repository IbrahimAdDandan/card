package com.example.smartcard.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Station {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private double langitude;
    private double latitude;
    private double ammount = 1000;
    private int queueCount = 0;
    @OneToMany( mappedBy = "station" )
    private List<FillOperation> fillOperations;
    @OneToMany( mappedBy = "station" )
    private List<FraudOperation> fraudOperations;

    public Station() {
    }

    public Station(Long id, String name, String address, double langitude, double latitude, double ammount, int queueCount) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.langitude = langitude;
        this.latitude = latitude;
        this.ammount = ammount;
        this.queueCount = queueCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLangitude() {
        return langitude;
    }

    public void setLangitude(double langitude) {
        this.langitude = langitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public int getQueueCount() {
        return queueCount;
    }

    public void setQueueCount(int queueCount) {
        this.queueCount = queueCount;
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
