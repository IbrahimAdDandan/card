package com.example.smartcard.domain;

public class StationStat {
    
    private Long id;
    private String address;
    private String name;
    private int fillOpsNum;
    private int fraudOpsNum;
    
    @Override
    public StationStat clone() {
        StationStat s = new StationStat(this.id, this.address, this.name, this.fillOpsNum, this.fraudOpsNum);
        return s;
    }

    public StationStat() {
    }

    public StationStat(Long id, String address, String name, int fillOpsNum, int fraudOpsNum) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.fillOpsNum = fillOpsNum;
        this.fraudOpsNum = fraudOpsNum;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFillOpsNum() {
        return fillOpsNum;
    }

    public void setFillOpsNum(int fillOpsNum) {
        this.fillOpsNum = fillOpsNum;
    }

    public int getFraudOpsNum() {
        return fraudOpsNum;
    }

    public void setFraudOpsNum(int fraudOpsNum) {
        this.fraudOpsNum = fraudOpsNum;
    }
    
}
