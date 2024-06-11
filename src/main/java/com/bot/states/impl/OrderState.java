package com.bot.states.impl;

import com.bot.states.State;
import com.bot.strategy.order.OrderStrategy;
import com.bot.strategy.order.impl.OrderCallbackUpdateStrategy;
import com.bot.strategy.order.impl.OrderTextUpdateStategy;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class OrderState implements State {

    @Override
    public SendMessage getResponse(Update update) {

        OrderStrategy orderStrategy = null;

        if (update.hasMessage()){
            orderStrategy = new  OrderTextUpdateStategy();
        }

        if (update.hasCallbackQuery()){
            orderStrategy = new OrderCallbackUpdateStrategy();
        }

        if (orderStrategy == null){
            throw new IllegalStateException("The update type could not be determined");
        }

        return orderStrategy.getResponse(update);
    }
}
