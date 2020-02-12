package com.sliit.research.blockchainbasedapplication.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery_route")
public class DeliveryRoute {

    @Id
    @GeneratedValue
    private Long id;

    private String remarks;

    public DeliveryRoute() {
    }

    public DeliveryRoute(String remarks) {
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
