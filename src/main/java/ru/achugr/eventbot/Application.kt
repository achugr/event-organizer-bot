package ru.achugr.eventbot

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.exceptions.TelegramApiException

/**
 * author: achugr
 * date: 24.04.2016.
 */

@SpringBootApplication
@ComponentScan(basePackages = arrayOf("ru.achugr.eventbot"))
open class Application {

}

val log = LoggerFactory.getLogger(Application::class.java)

fun main(args: Array<String>) {
    val ctx = SpringApplication.run(Application::class.java, *args)

    val telegramBotsApi = TelegramBotsApi()
    try {
        telegramBotsApi.registerBot(ctx.getBean("mainHandler") as TelegramLongPollingBot)
    } catch (e: TelegramApiException) {
        log.error("Something went wrong", e)
    }
}