package com.example.spring_project.alterbot;

import com.example.spring_project.webstore.entity.Order;
import com.example.spring_project.webstore.entity.OrderProductRelation;
import com.example.spring_project.webstore.entity.User;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class AlterMessageBot {

    public void sendMessage(User user, Order order, List<OrderProductRelation> orderProductRelations) {
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

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8082/send"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(String.format("{\"text\":\"%s\", \"key\":\"sasha\"}", message)))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
