package com.betlivefront.betlive;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;

@JsonDeserialize(using = UserDeserializer.class)
@SuppressWarnings("serial")
public class User {

    private final int userId;
    private final String userName;
    private final BigDecimal balance;


    public User(int userId, String userName, BigDecimal balance) {
        this.userId = userId;
        this.userName = userName;
        this.balance = balance;

    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public BigDecimal getBalance() {
        return balance;
    }


}
