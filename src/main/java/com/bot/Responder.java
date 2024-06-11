package com.bot;

import com.bot.states.impl.GeneralState;
import com.bot.states.impl.OrderState;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Responder extends TelegramLongPollingBot {

    Logger LOGGER = Logger.getLogger(Responder.class.getName());

    @Override
    public String getBotToken() {
        return Bot.BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return Bot.BOT_USERNAME;
    }

    //1-Order status
    //2-More information
    //3-Contact human

    @Override
    public void onUpdateReceived(Update update) {


        try {

            SendMessage response = null;

            String chatId = getChatId(update);

            if (!MongoDB.userExists(chatId)){
                MongoDB.insertNewUserId(chatId);
                response = new GeneralState().getResponse(update);
                sendApiMethod(response);
                return;
            }

            String currentState = MongoDB.getFieldValue(MongoDB.STATE,chatId);

            if (State.GENERAL.toString().equals(currentState)){
                response = new GeneralState().getResponse(update);
                sendApiMethod(response);
                return;
            }

            if (State.ORDER_STATUS.toString().equals(currentState)){
                response = new OrderState().getResponse(update);
                sendApiMethod(response);
                return;
            }

            LOGGER.log(Level.SEVERE,"No response could be determined!",update);

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getChatId(Update update){

        if (update.hasMessage()){
            return String.valueOf(update.getMessage().getChatId());
        }

        if (update.hasCallbackQuery()){
            return String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        }

        throw new IllegalStateException("Chat room id is empty");
    }
}
