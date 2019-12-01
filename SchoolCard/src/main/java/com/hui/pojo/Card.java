package com.hui.pojo;

import java.io.Serializable;

public class Card implements Serializable {
    private  String cardId;
    private  String password;
    private  String openid;

    @Override
    public String toString() {
        return "Card{" +
                "cardId='" + cardId + '\'' +
                ", password='" + password + '\'' +
                ", openid='" + openid + '\'' +
                ", money=" + money +
                '}';
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    private  float money;


    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }


}
