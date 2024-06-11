package com.bot.strategy.order;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface OrderStrategy {

    SendMessage getResponse(Update update);
}
