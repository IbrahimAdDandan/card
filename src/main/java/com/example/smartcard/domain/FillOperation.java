package com.example.smartcard.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class FillOperation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Station station;
    @ManyToOne
    private SmartCard card;
    private double ammount;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public FillOperation() {
    }

    public FillOperation(Station station, SmartCard card, double ammount) {
        this.station = station;
        this.card = card;
        this.ammount = ammount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public SmartCard getCard() {
        return card;
    }

    public void setCard(SmartCard card) {
        this.card = card;
    }
    
    
}
