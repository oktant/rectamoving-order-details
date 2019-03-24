package com.rectasolutions.moving.booking.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Entity
@Table(name = "location")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "latitude", nullable = false)
    @NotNull
    private double latitude;

    @Column(name = "longtide", nullable = false)
    @NotNull
    private double longtide;

    @Column(name = "placeName", nullable = false)
    @NotNull
    private String placeName;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtide() {
        return longtide;
    }

    public void setLongtide(double longtide) {
        this.longtide = longtide;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longtide=" + longtide +
                ", placeName='" + placeName + '\'' +
                '}';
    }
}
