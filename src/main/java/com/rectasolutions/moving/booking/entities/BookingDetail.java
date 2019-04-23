package com.rectasolutions.moving.booking.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.rectasolutions.moving.booking.config.serializers.JsonDateDeserializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking_detail")
public class BookingDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "pick_up_id", nullable = false)
    @NotNull
    private Location pickUp;

    @ManyToOne
    @JoinColumn(name = "drop_to_id", nullable = false)
    @NotNull
    private Location dropTo;

    @Column(name = "distance", nullable = false)
    @NotNull
    private double distance;

    @Column(name = "payload", nullable = false)
    @NotNull
    private double payload;

    @Column(name = "volume")
    private double volume;

    @Column(name = "good_descriptions")
    private String goodDescriptions;

    @Column(name = "delivery_time", nullable = false)
    @NotNull
    private String deliveryTime;

    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "pick_up_time")
    private LocalDateTime pickUpTime;

    @ManyToOne
    @JoinColumn(name = "customer_detail_id", nullable = false)
    @NotNull
    private CustomerDetail customerDetail;

    @ManyToOne
    @JoinColumn(name = "vehicle_category_id", nullable = false)
    @NotNull
    private VehicleCategory vehicleCategory;

    @Column(name = "price", nullable = false)
    @NotNull
    private String price;

    public Location getPickUp() {
        return pickUp;
    }

    public void setPickUp(Location pickUp) {
        this.pickUp = pickUp;
    }

    public Location getDropTo() {
        return dropTo;
    }

    public void setDropTo(Location dropTo) {
        this.dropTo = dropTo;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
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

    public String getGoodDescriptions() {
        return goodDescriptions;
    }

    public void setGoodDescriptions(String goodDescriptions) {
        this.goodDescriptions = goodDescriptions;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(CustomerDetail customerDetail) {
        this.customerDetail = customerDetail;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalDateTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    @Override
    public String toString() {
        return "BookingDetail{" +
                "pickUp=" + pickUp +
                ", dropTo=" + dropTo +
                ", distance=" + distance +
                ", payload=" + payload +
                ", volume=" + volume +
                ", goodDescriptions='" + goodDescriptions + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", customerDetail=" + customerDetail +
                ", vehicleCategory=" + vehicleCategory +
                ", price='" + price + '\'' +
                '}';
    }
}
