package com.eactive.study.discordbot.service.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
public class GptMessageService {

    @Value("${gptApiKey}")
    private String gptApiKey;

    public String getReply(String inquiry) {

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openai.com/v1/chat/completions")) // Replace with your API URL
                    .header("Authorization", "Bearer " + gptApiKey)
                    .header("Content-Type", "application/json; utf-8")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"model\": \"gpt-3.5-turbo\",\n" +
                            "               \"messages\": [{\"role\":\"user\",\"content\": \""+inquiry+"\"}], \n" +
                            "               \"temperature\":1.0, \n" +
                            "               \"max_tokens\":1024,\n" +
                            "               \"top_p\":1, \n" +
                            "               \"frequency_penalty\": 0.0, \n" +
                            "               \"presence_penalty\":0.0\n" +
                            "              }\n"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return e.getMessage();
        }

    }


}
