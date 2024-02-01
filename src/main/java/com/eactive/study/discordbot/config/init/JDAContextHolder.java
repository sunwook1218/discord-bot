package com.eactive.study.discordbot.config.init;

import lombok.Data;
import net.dv8tion.jda.api.JDA;

@Data
public class JDAContextHolder {

    private JDAContextHolder() {}

    private static class InnerInstanceClass {
        private static final JDAContextHolder instance = new JDAContextHolder();
    }

    public static JDAContextHolder getInstance() {
        return InnerInstanceClass.instance;
    }

    /**
     * JDAContextHolder helps you can use JDA object everywhere.
     */

    private JDA jda = null;

}

