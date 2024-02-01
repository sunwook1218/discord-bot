package com.eactive.study.discordbot.config.init;

import com.eactive.study.discordbot.service.message.MessageService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MainAdapter extends ListenerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final MessageService messageService;

    public MainAdapter(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getMessage().getContentRaw().startsWith("DEBUG")) {
            debugMode(event);
            return;
        }

        try {

            messageService.service(event);

        } catch (Exception e) {

            event.getChannel().sendMessage(e.getMessage()).queue(message -> {
                message.editMessageFormat("Error : %s", e.getMessage()).queue();
            });

        }

        super.onMessageReceived(event);
    }

    private void debugMode(MessageReceivedEvent event) {

        try {

            messageService.service(event);

        } catch (Exception e) {

            event.getChannel().sendMessage(e.getMessage()).queue(message -> {
                message.editMessageFormat("Error : %s", e.getMessage()).queue();
            });

            for (StackTraceElement stackTraceElement : e.getStackTrace()) {

                event.getChannel().sendMessage(stackTraceElement.toString()).queue();

            }

        }

    }

}
