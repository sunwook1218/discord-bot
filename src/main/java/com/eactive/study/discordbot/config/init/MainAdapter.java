package com.eactive.study.discordbot.config.init;

import com.eactive.study.discordbot.service.message.GptMessageService;
import com.eactive.study.discordbot.service.message.IconMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainAdapter extends ListenerAdapter {

    private final IconMessageService iconMessageService;
    private final GptMessageService gptMessageService;


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) {
            return;
        }

        if (!event.getMessage().getContentRaw().startsWith("! ")) {
            return;
        }

        log.info("MESSAGE Received");

        // TODO determine event service 추가하기
        if (event.getMessage().getContentRaw().startsWith("! icon")) {
            iconMessageService.sendImage(event.getChannel(), "9.jpg", false);
        }

        if (event.getMessage().getContentRaw().startsWith("! gpt")) {
            event.getChannel().sendMessage(gptMessageService.getReply(event.getMessage().getContentRaw().split("! gpt")[1])).queue();
        }

        super.onMessageReceived(event);

    }

}
