package com.cs.trading.Models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Log {
    Integer id;
    EventType eventType;
    Integer buySideOrderOwnerId;
    Integer sellSideOrderOwnerId;
    Integer quantity;
    Double price;
    OrderType buyOrderType;
    OrderType sellOrderType;
    String tickerSymbol;
    Date timestamp;

    public Log(Integer id, EventType eventType, Integer quantity, Double price, String tickerSymbol, Date timestamp) {
        this.id = id;
        this.eventType = eventType;
        this.quantity = quantity;
        this.price = price;
        this.tickerSymbol = tickerSymbol;
        this.timestamp = timestamp;
    }

    public Log() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Integer getBuySideOrderOwnerId() {
        return buySideOrderOwnerId;
    }

    public void setBuySideOrderOwnerId(Integer buySideOrderOwnerId) {
        this.buySideOrderOwnerId = buySideOrderOwnerId;
    }

    public Integer getSellSideOrderOwnerId() {
        return sellSideOrderOwnerId;
    }

    public void setSellSideOrderOwnerId(Integer sellSideOrderOwnerId) {
        this.sellSideOrderOwnerId = sellSideOrderOwnerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderType getBuyOrderType() {
        return buyOrderType;
    }

    public void setBuyOrderType(OrderType buyOrderType) {
        this.buyOrderType = buyOrderType;
    }

    public OrderType getSellOrderType() {
        return sellOrderType;
    }

    public void setSellOrderType(OrderType sellOrderType) {
        this.sellOrderType = sellOrderType;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public Date getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
