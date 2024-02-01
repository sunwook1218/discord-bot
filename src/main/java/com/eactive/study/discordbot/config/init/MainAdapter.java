package com.eactive.study.discordbot.config.init;

import com.eactive.study.discordbot.service.message.IconMessageService;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MainAdapter extends ListenerAdapter {

    private final IconMessageService messageService;

    public MainAdapter(IconMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) {
            return;
        }

        log.info("MESSAGE Received");

        if (event.getMessage().getContentRaw().startsWith("GGG")) {
            try {
                Thread.sleep(60_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            messageService.sendImage(event.getChannel(), "9.jpg", true);
        }

        super.onMessageReceived(event);

    }

}
