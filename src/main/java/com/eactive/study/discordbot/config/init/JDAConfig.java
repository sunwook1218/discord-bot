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

        try {
            jda = JDABuilder.createDefault(key)
                    .enableIntents(EnumSet.allOf(GatewayIntent.class))
                    .build()
                    .awaitReady();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        jda.addEventListener(mainAdapter);

        log.info("JDA Config Ready!!!");

        return jda;
    }
}
