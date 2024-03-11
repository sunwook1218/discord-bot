package com.eactive.study.discordbot.config.init;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumSet;

@Slf4j
@Configuration
public class JDAConfig {

    @Value("${key}")
    private String key;

    @Bean
    public JDA jda(MainAdapter mainAdapter) {

        JDA jda = null;

            jda = JDABuilder.createDefault(key)
                    .enableIntents(GatewayIntent.DIRECT_MESSAGES)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .build();

        jda.addEventListener(mainAdapter);

        log.info("JDA Config Ready!!!");

        return jda;
    }
}
