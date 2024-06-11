package com.bot.strategy.general.impl;

import com.bot.MongoDB;
import com.bot.State;
import com.bot.data.CallBackData;
import com.bot.strategy.general.GeneralStrategy;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class GeneralCallbackUpdateStrategy implements GeneralStrategy {

    @Override
    public SendMessage getResponse(Update update) {

        String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        String sendText = "Order number is expected,so please input the order number or tap /start to start over";

        String callBackData = update.getCallbackQuery().getData();

        if (CallBackData.ORDER_STATUS.toString().equalsIgnoreCase(callBackData)){
            sendText = "Please input the order number";
            MongoDB.updateField(MongoDB.STATE, State.ORDER_STATUS.toString(),chatId);
        }

        if (CallBackData.MORE_INFORMATION.toString().equalsIgnoreCase(callBackData)){
            sendText="Thank you for your interest in our product.Our company is available 24 hours a day.Feel free to check our website.";

            List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

            List<InlineKeyboardButton> buttonRows = new ArrayList<>();

            InlineKeyboardButton websiteButton = new InlineKeyboardButton();
            websiteButton.setText("Website");
            websiteButton.setUrl("https://www.google.com");
            websiteButton.setCallbackData(CallBackData.ORDER_STATUS.toString());

            buttonRows.add(websiteButton);
            keyboard.add(buttonRows);

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            inlineKeyboardMarkup.setKeyboard(keyboard);

            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }

        if (CallBackData.CONTACT_HUMAN.toString().equalsIgnoreCase(callBackData)){

            sendText = "You can contact our staff through the following website";

            List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
            List<InlineKeyboardButton> buttonRows = new ArrayList<>();
            InlineKeyboardButton customerButton = new InlineKeyboardButton();

            customerButton.setText("Human Support");
            customerButton.setUrl("https://www.google.com");

            buttonRows.add(customerButton);
            keyboard.add(buttonRows);

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            inlineKeyboardMarkup.setKeyboard(keyboard);

            sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        }

        sendMessage.setText(sendText);
        return sendMessage;
    }
}
