package com.bot.strategy.order.impl;

import com.bot.MongoDB;
import com.bot.State;
import com.bot.data.OrderDetailsDAOImpl;
import com.bot.strategy.general.impl.GeneralTextUpdateStrategy;
import com.bot.strategy.order.OrderStrategy;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class OrderTextUpdateStategy implements OrderStrategy {

    @Override
    public SendMessage getResponse(Update update) {

        String chatId = String.valueOf(update.getMessage().getChatId());

        SendMessage sendMessage = new SendMessage();

        sendMessage.setText(chatId);

        sendMessage.setText("Invalid response entered. Please input the order number or tap /start to start over.");

        String textUpdate = update.getMessage().getText().trim();

        if (OrderDetailsDAOImpl.orders.containsKey(textUpdate)){
            sendMessage.setText(OrderDetailsDAOImpl.orders.get(textUpdate).toString());
        }

        if (textUpdate.equalsIgnoreCase("/start")){

            //MongoDB.updateField(MongoDB.STATE, State.GENERAL.toString(),chatId);
            sendMessage = new GeneralTextUpdateStrategy().getResponse(update);
        }

        return sendMessage;
    }
}
