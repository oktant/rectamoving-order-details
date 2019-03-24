package com.rectasolutions.moving.booking.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "customer_detail")
public class CustomerDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "sender", nullable = false)
    @NotNull
    private String sender;

    @Column(name = "receiver", nullable = false)
    @NotNull
    private String receiver;

    @Column(name="delivery_note")
    private String deliveryNote;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    @Override
    public String toString() {
        return "CustomerDetail{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", deliveryNote='" + deliveryNote + '\'' +
                '}';
    }
}
