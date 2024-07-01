package com.example.spring_project.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MessageBot extends TelegramLongPollingBot {

    public MessageBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return "java_spring_store_bot";
    }

    public void sendMessage(String text) {
        String chatIdStr = String.valueOf(2012903675l);
        System.out.println(chatIdStr);
        var sendMessage = new SendMessage(chatIdStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Ошибка отправки сообщения");
            System.out.println(e);
        }
    }
}
