package com.eactive.study.discordbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication
@EnableAsync
public class DiscordbotApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Asia/Seoul")));
		SpringApplication.run(DiscordbotApplication.class, args);
	}

}
