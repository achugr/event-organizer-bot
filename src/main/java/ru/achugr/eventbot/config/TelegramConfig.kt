package ru.achugr.eventbot.config

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * author: achugr
 * date: 08.05.2016.
 */

@Component
open class TelegramConfig {

//    @Value("\${telegram.token}")
    var telegramToken: String = ""

//    @Value("\${telegram.botName}")
    var telegramBotName: String = ""

    @PostConstruct
    fun initFromEnvVars() {
        if (System.getenv("BOT_NAME") != null) {
            telegramBotName = System.getenv("BOT_NAME")
            telegramToken = System.getenv("BOT_TOKEN")
        }
    }
}
