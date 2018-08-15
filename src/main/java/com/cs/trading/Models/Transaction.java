package com.cs.trading.Models;

import java.util.Date;

public class Transaction {
    private int id;
    private int buyOrderId;
    private int sellOrderId;
    private int quantityTraded;
    private double price;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyOrderId() {
        return buyOrderId;
    }

    public void setBuyOrderId(int buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    public int getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(int sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public int getQuantityTraded() {
        return quantityTraded;
    }

    public void setQuantityTraded(int quantityTraded) {
        this.quantityTraded = quantityTraded;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
