package com.eactive.study.discordbot.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class GitLabWebhookController {

    @RequestMapping("/testCall")
    public ResponseEntity<Map<String, String[]>> testCall(HttpServletRequest request, @RequestBody String body) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            log.info("PARAM:" + objectMapper.writeValueAsString(request.getParameterMap()));
            log.info("BODY:" + body);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        System.out.println("TEMP");
        return ResponseEntity.ok(request.getParameterMap());
    }

}
