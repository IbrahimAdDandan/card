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
public class FraudOperation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Station station;
    @ManyToOne
    private SmartCard card;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    private String username;
    private String type;

    public FraudOperation() {
    }

    public FraudOperation(Station station, SmartCard card, String username, String type) {
        this.station = station;
        this.card = card;
        this.username = username;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
