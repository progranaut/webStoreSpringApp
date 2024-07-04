package com.example.spring_project.bot.service;

import com.example.spring_project.bot.MessageBot;
import com.example.spring_project.webstore.entity.Order;
import com.example.spring_project.webstore.entity.OrderProductRelation;
import com.example.spring_project.webstore.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageBotService {

    @Value("${admin.id}")
    private String adminId;

    private final MessageBot messageBot;

    public void sendOrderMessage(User user, Order order, List<OrderProductRelation> orderProductRelations) {

        StringBuilder relations = new StringBuilder();
        orderProductRelations.stream().forEach(relation -> {
            relations.append(relation.getProduct().getName());
            relations.append(" - ");
            relations.append(relation.getRelationPrice());
            relations.append(" руб. - ");
            relations.append(relation.getRelationQuantity());
            relations.append(" шт.\n");
        });

        String text = """
                Новый заказ номер: %d
                Пользователь: %s
                Телефон: %s
                Адрес: %s
                Товары:
                %s
                """;

        String message = String.format(text, order.getOrderNumber(), user.getName(), user.getPhoneNumber(), user.getAddress(), relations);

        messageBot.sendMessage(Long.valueOf(adminId), message);
    }

}
