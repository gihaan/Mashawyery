package com.example.gihan.mashawyery.model;

/**
 * Created by Gihan on 2/20/2018.
 */

public class User {

    private String name, email, password, phone, creditCard, country, date, ccv, image, themp_up, verivied;

    public User(String name, String email, String password, String phone, String creditCard, String country, String date, String ccv, String image, String themp_up, String verivied) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.creditCard = creditCard;
        this.country = country;
        this.date = date;
        this.ccv = ccv;
        this.image = image;
        this.verivied = verivied;
        this.themp_up = themp_up;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVerivied() {
        return verivied;
    }

    public void setVerivied(String verivied) {
        this.verivied = verivied;
    }

    public String getThemp_up() {
        return themp_up;
    }

    public void setThemp_up(String themp_up) {
        this.themp_up = themp_up;
    }


}
