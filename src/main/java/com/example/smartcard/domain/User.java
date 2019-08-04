package com.example.smartcard.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String full_name;
    @Column( unique = true, nullable = false )
    private String username;
    @Column( nullable = false )
    private String password;
    private String role_name;
    private String email;
    private int phone;
    private boolean isEnabled = true;
    @Column( unique = true, nullable = true )
    private Long idNum;
    @OneToMany
    private List<SmartCard> cards;

    public User(){}

    public User(Long id, String full_name, String username, String password, String role_name, String email, Long idNum) {
        this.id = id;
        this.full_name = full_name;
        this.username = username;
        this.password = password;
        this.role_name = role_name;
        this.email = email;
        this.idNum = idNum;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public List<SmartCard> getCards() {
        return cards;
    }

    public void setCards(List<SmartCard> cards) {
        this.cards = cards;
    }

    public Long getIdNum() {
        return idNum;
    }

    public void setIdNum(Long idNum) {
        this.idNum = idNum;
    }
    
    
}
