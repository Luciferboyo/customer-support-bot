package com.bot.states;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface State {

    SendMessage getResponse(Update update);

}
