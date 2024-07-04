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
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        Long chatId = update.getMessage().getChatId();
        String message = update.getMessage().getText();
        if (message.equals("/start")) {
            String userName = update.getMessage().getChat().getUserName();
            startCommand(chatId, userName);
        } else {
            undefinedCommand(chatId);
        }
    }

    @Override
    public String getBotUsername() {
        return "java_spring_store_bot";
    }

    private void startCommand(Long chatId, String userName) {
        String text = String.format("Привет, %s!", userName);
        sendMessage(chatId, text);
    }

    private void undefinedCommand(Long chatId) {
        String text = "Что-то новенькое? Я такую команду еще не изучил. =/";
        sendMessage(chatId, text);
    }

    public void sendMessage(Long chatId, String text) {
        String chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Ошибка отправки сообщения");
            System.out.println(e);
        }
    }
}
