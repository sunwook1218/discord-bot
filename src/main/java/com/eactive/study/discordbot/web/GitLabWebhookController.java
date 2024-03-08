package com.eactive.study.discordbot.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GitLabWebhookController {

    @RequestMapping("/testCall")
    public ResponseEntity<Map<String, String[]>> testCall(HttpServletRequest request) {
        return ResponseEntity.ok(request.getParameterMap());
    }

}
