package com.bot.strategy.general.impl;

import com.bot.data.CallBackData;
import com.bot.strategy.general.GeneralStrategy;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class GeneralTextUpdateStrategy implements GeneralStrategy {

    @Override
    public SendMessage getResponse(Update update) {

        String sendText = "Sorry,your command is incorrect, you can enter \"/start\" to start ordering";

        String chatId = String.valueOf(update.getMessage().getChatId());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (update.getMessage().hasText() && "/start".equalsIgnoreCase(update.getMessage().getText())){

            sendText = "Welcome to HelloWorld,please select the option you are looking for.";

            List<List<InlineKeyboardButton>> keyboards = new ArrayList<>();
            List<InlineKeyboardButton> buttonRows = new ArrayList<>();

            InlineKeyboardButton orderStatusButton = new InlineKeyboardButton();
            orderStatusButton.setText("Status \uD83C\uDF74");
            orderStatusButton.setCallbackData(CallBackData.ORDER_STATUS.toString());

            InlineKeyboardButton informationButton = new InlineKeyboardButton();
            informationButton.setText("Information \u2139");
            informationButton.setCallbackData(CallBackData.MORE_INFORMATION.toString());

            InlineKeyboardButton contactButton = new InlineKeyboardButton();
            contactButton.setText("Support \u260E");
            contactButton.setCallbackData(CallBackData.CONTACT_HUMAN.toString());

            buttonRows.add(orderStatusButton);
            buttonRows.add(informationButton);
            buttonRows.add(contactButton);

            keyboards.add(buttonRows);

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            inlineKeyboardMarkup.setKeyboard(keyboards);

            sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        }

        sendMessage.setText(sendText);

        return sendMessage;
    }
}
