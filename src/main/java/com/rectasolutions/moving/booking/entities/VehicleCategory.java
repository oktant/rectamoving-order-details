package com.rectasolutions.moving.booking.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vehicle_category")
public class VehicleCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "type", nullable = false)
    @NotNull
    private String type;

    @Column(name = "instant_del_option", nullable = false)
    @NotNull
    private Boolean instantDelOption;

    @Column(name="next_time")
    private double nextTime;

    @Column(name = "call_distance")
    private double callDistance;

    @Column(name = "delivery_distance")
    private double deliveryDistance;

    @Column(name = "payload")
    private double payload;

    @Column(name = "volume")
    private double volume;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private VehicleCategory parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isInstantDelOption() {
        return instantDelOption;
    }

    public void setInstantDelOption(boolean instantDelOption) {
        this.instantDelOption = instantDelOption;
    }

    public double getNextTime() {
        return nextTime;
    }

    public void setNextTime(double nextTime) {
        this.nextTime = nextTime;
    }

    public double getCallDistance() {
        return callDistance;
    }

    public void setCallDistance(double callDistance) {
        this.callDistance = callDistance;
    }

    public double getDeliveryDistance() {
        return deliveryDistance;
    }

    public void setDeliveryDistance(double deliveryDistance) {
        this.deliveryDistance = deliveryDistance;
    }

    public double getPayload() {
        return payload;
    }

    public void setPayload(double payload) {
        this.payload = payload;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public VehicleCategory getParentId() {
        return parentId;
    }

    public void setParentId(VehicleCategory parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "VehicleCategory{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", instantDelOption=" + instantDelOption +
                ", nextTime=" + nextTime +
                ", callDistance=" + callDistance +
                ", deliveryDistance=" + deliveryDistance +
                ", payload=" + payload +
                ", volume=" + volume +
                ", parentId=" + parentId +
                '}';
    }
}
