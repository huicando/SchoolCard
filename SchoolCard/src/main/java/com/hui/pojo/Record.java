package com.hui.pojo;

import java.io.Serializable;

public class Record implements Serializable {
    private String cardId;
    private String mark;   //备注
    private String way;   //消费方式，如饭堂
    private Float  price;   //金额

    @Override
    public String toString() {
        return "Record{" +
                "cardId='" + cardId + '\'' +
                ", mark='" + mark + '\'' +
                ", way='" + way + '\'' +
                ", price=" + price +
                '}';
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}
