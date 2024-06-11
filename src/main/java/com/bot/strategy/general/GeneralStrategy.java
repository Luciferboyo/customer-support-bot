package com.bot.strategy.general;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface GeneralStrategy {

    SendMessage getResponse(Update update);
}
