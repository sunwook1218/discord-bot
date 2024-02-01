package com.eactive.study.discordbot.service.message;

import bot.discord.ww.common.base.BaseService;
import bot.discord.ww.common.jda.JDAContextHolder;
import bot.discord.ww.common.string.CommonStrings;
import bot.discord.ww.core.domain.character.Character;
import bot.discord.ww.core.domain.island.Island;
import bot.discord.ww.core.message.embed.EmbedMapper;
import bot.discord.ww.core.search.ScrapService;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends BaseService {

    private final ScrapService scrapService;

    private final EmbedMapper embedMapper;

    public MessageService(ScrapService scrapService, EmbedMapper embedMapper) {
        this.scrapService = scrapService;
        this.embedMapper = embedMapper;
    }

    public void service(MessageReceivedEvent event) throws Exception {

        String contentRaw = event.getMessage().getContentRaw().replaceAll("DEBUG ", "");
        User author = event.getAuthor();

        if(event.getChannel().getId().equals("940093794492248064")) {
            TextChannel tsuChannel = JDAContextHolder.getInstance().getJda().getTextChannelById("246804865005780993");

            tsuChannel.sendMessage(contentRaw).queue();
        }

        if(contentRaw.startsWith("ww ")){

            String[] messages = contentRaw.split(" ");

            if(messages.length < 2) {
                event.getChannel().sendMessage("명령을 입력해주세요");
                return;
            }

            String command = getCommand(messages);
            String content = getContent(messages);

            logger.info("command : {}, content : {}", command, content);

            if(command.equals("s")) {

                character_response(event, content);
            }

            if(command.equals("섬")) {

                island_response(event);
            }

        } else {

            if(contentRaw.endsWith("ww") && !contentRaw.equals(CommonStrings.WWWW)) {
                end_ww_response(event);
            }





        }

    }

    private void end_ww_response(MessageReceivedEvent event) {
        event.getChannel().sendMessage(CommonStrings.WWWW).queue();
    }

    private void character_response(MessageReceivedEvent event, String content) throws Exception {

        Character character = scrapService.getCharacterByName(content);
        MessageEmbed messageEmbed = embedMapper.mkCharacterEmbed(character);

        event.getChannel().sendMessageEmbeds(messageEmbed).queue();
    }

    private void island_response(MessageReceivedEvent event) throws Exception {

        Island island = scrapService.getIsland();

        event.getChannel().sendMessage(CommonStrings.WAITING).queue(message -> {
            message.editMessageFormat("다음 시작 : %s | 남은 시간 : %s", island.getNextStartTime(), island.getTimeToNextStart()).queue();
        });

        island.getIslandList().forEach(islandName -> {
            event.getChannel().sendMessage(islandName).queue();
        });
    }

    private String getCommand(String[] messages) {
        if(messages.length < 2) {
            return "";
        }
        return messages[1];
    }

    private String getContent(String[] messages) {
        if(messages.length < 3) {
            return "";
        }
        return messages[2];
    }

}
