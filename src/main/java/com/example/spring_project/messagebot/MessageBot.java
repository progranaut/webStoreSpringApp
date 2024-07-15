//package com.example.spring_project.messagebot;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//@Component
//public class MessageBot {
//
//    @Value("${bot.uri}")
//    String uri;
//
//    @Value("${bot.content-type}")
//    String contentType;
//
//    public void sendMessage(String message) {
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(uri))
//                .header("Content-Type", contentType)
//                .POST(HttpRequest.BodyPublishers.ofString(String.format("{\"text\":\"%s\", \"key\":\"sasha\"}", message)))
//                .build();
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.statusCode());
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//    }
//
//}
