package com.example.gihan.mashawyery.model;

import java.io.Serializable;

/**
 * Created by Gihan on 2/25/2018.
 */

public class trip implements Serializable {

    private String from, to, driver, user, day, date, time, distance, price, waitTime, cost, kindOFTrip,kindCar;

    public trip(String from, String to, String driver, String user, String day, String date, String time,
                String distance, String price, String waitTime, String cost, String kindOFTrip,String kindCar) {
        this.from = from;
        this.to = to;
        this.driver = driver;
        this.user = user;
        this.day = day;
        this.date = date;
        this.time = time;
        this.distance = distance;
        this.price = price;
        this.waitTime = waitTime;
        this.cost = cost;
        this.kindOFTrip = kindOFTrip;
        this.kindCar=kindCar;
    }

    public trip() {
    }

    public String getKindCar() {
        return kindCar;
    }

    public void setKindCar(String kindCar) {
        this.kindCar = kindCar;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getKindOFTrip() {
        return kindOFTrip;
    }

    public void setKindOFTrip(String kindOFTrip) {
        this.kindOFTrip = kindOFTrip;
    }
}
