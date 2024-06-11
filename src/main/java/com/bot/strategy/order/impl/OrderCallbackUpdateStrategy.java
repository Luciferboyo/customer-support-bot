package com.bot.strategy.order.impl;

import com.bot.data.OrderDetailsDAOImpl;
import com.bot.strategy.order.OrderStrategy;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class OrderCallbackUpdateStrategy implements OrderStrategy {

    @Override
    public SendMessage getResponse(Update update) {

        String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Order number is expected,so please input the order number");

        //空指针异常
        if (update.hasMessage() || update.getMessage().hasText()){
            if (OrderDetailsDAOImpl.orders.containsKey(update.getMessage().getText())){
                sendMessage.setText("The status of the order is:"+update.getMessage().getText());
            }
        }
        return sendMessage;
    }
}
