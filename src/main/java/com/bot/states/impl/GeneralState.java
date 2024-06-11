package com.bot.states.impl;

import com.bot.states.State;
import com.bot.strategy.general.GeneralStrategy;
import com.bot.strategy.general.impl.GeneralCallbackUpdateStrategy;
import com.bot.strategy.general.impl.GeneralTextUpdateStrategy;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class GeneralState implements State {

    @Override
    public SendMessage getResponse(Update update) {

        GeneralStrategy generalStrategy = null;

        if (update.hasMessage()){
            generalStrategy = new GeneralTextUpdateStrategy();
        }

        if (update.hasCallbackQuery()){
            generalStrategy = new GeneralCallbackUpdateStrategy();
        }

        if (generalStrategy == null) {
            throw new IllegalStateException("The update type could not be determined");
        }

        return generalStrategy.getResponse(update);
    }
}
