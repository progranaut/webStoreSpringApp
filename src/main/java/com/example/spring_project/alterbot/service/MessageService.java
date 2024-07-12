package com.example.spring_project.alterbot.service;

import com.example.spring_project.alterbot.AlterMessageBot;
import com.example.spring_project.alterbot.dto.CallOrderDto;
import com.example.spring_project.webstore.entity.Order;
import com.example.spring_project.webstore.entity.OrderProductRelation;
import com.example.spring_project.webstore.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final AlterMessageBot alterMessageBot;

    public void sendOrderInfo(User user, Order order, List<OrderProductRelation> orderProductRelations) {

        StringBuilder relations = new StringBuilder();
        orderProductRelations.stream().forEach(relation -> {
            relations.append(relation.getProduct().getName());
            relations.append(" - ");
            relations.append(relation.getRelationPrice());
            relations.append(" руб. - ");
            relations.append(relation.getRelationQuantity());
            relations.append(" шт.\\n");
        });

        String text = "Новый заказ номер: %d\\nПользователь: %s\\nТелефон: %s\\nАдрес: %s\\nТовары:\\n%s\\n";

        String message = String.format(text, order.getOrderNumber(), user.getName(), user.getPhoneNumber(), user.getAddress(), relations);

        alterMessageBot.sendMessage(message);

    }

    public void callOrder(CallOrderDto callOrderDto) {

        StringBuilder message = new StringBuilder();
        message.append("Заказ звонка! \\n");
        message.append("Номер телефона: ").append(callOrderDto.getPhone()).append("\\n");
        message.append("Имя: ").append(callOrderDto.getName()).append("\\n");
        message.append("Описание: \\n").append(callOrderDto.getMessage()).append("\\n");

        alterMessageBot.sendMessage(message.toString());

    }
}
