package com.bot.data;


import java.util.HashMap;
import java.util.Map;

public class OrderDetailsDAOImpl {

    public static Map<String,OrderStatus> orders = new HashMap<>();

    static {
        orders.put("123456",OrderStatus.IN_PROGRESS);
        orders.put("234567",OrderStatus.DELIVERED);
        orders.put("789456",OrderStatus.PENDING);
    }

}
