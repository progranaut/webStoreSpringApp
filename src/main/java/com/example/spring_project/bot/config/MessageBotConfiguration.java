package com.example.spring_project.bot.config;

import com.example.spring_project.bot.MessageBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class MessageBotConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi(MessageBot messageBot) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(messageBot);
        return api;
    }

}
