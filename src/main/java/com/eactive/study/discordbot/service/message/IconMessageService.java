package com.eactive.study.discordbot.service.message;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.utils.FileUpload;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
public class IconMessageService {

    private final DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();

    private static final String BASE_DIRECTORY_100 = "file:src/main/resources/static/img/icon/icon_100/";
    private static final String BASE_DIRECTORY_500 = "file:src/main/resources/static/img/icon/icon_500/";

    public void sendImage(MessageChannel channel, String fileName, boolean bigSizeImg) {

        String baseDir = bigSizeImg ? BASE_DIRECTORY_500 : BASE_DIRECTORY_100;

        try {
            File icon = defaultResourceLoader.getResource(baseDir + fileName).getFile();
            channel.sendFiles(FileUpload.fromData(icon)).queue();
        } catch (Exception e) {
            channel.sendMessage("파일을 가져오는 과정 중 오류가 발생하였습니다.");
            log.error("파일을 가져오는 과정 중 오류가 발생하였습니다.", e);
        }
    }

}
