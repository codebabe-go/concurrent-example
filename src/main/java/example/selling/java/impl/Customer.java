package example.selling.java.impl;

import example.selling.java.Request;

import java.sql.Timestamp;

/**
 * author: code.babe
 * date: 2016-09-17 13:45
 */
public class Customer extends Request {
    public Customer(Timestamp postTime, int type) {
        super(postTime, type);
    }

}