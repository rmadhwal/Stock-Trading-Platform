package com.cs.trading.Models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {
    private Integer id;
    private Integer buyOrderId;
    private Integer sellOrderId;
    private Integer quantityTraded;
    private Double price;
    private Date timestamp;

    public Transaction() {
    }

    public Transaction(int id, int buyOrderId, int sellOrderId, int quantityTraded, double price, Date timestamp) {
        this.id = id;
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.quantityTraded = quantityTraded;
        this.price = price;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuyOrderId() {
        return buyOrderId;
    }

    public void setBuyOrderId(Integer buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    public Integer getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(Integer sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public Integer getQuantityTraded() {
        return quantityTraded;
    }

    public void setQuantityTraded(Integer quantityTraded) {
        this.quantityTraded = quantityTraded;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
