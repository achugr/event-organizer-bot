package ru.achugr.eventbot.handler

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import ru.achugr.eventbot.config.TelegramConfig
import ru.achugr.eventbot.service.MessageProcessingService

/**
 * author: achugr
 * date: 02.05.2016.
 */

val log = LoggerFactory.getLogger(MainHandler::class.java)

@Component("mainHandler")
@Slf4j
class MainHandler @Autowired constructor(val messageService: MessageProcessingService,
                                         val telegramConfig: TelegramConfig) : TelegramLongPollingBot() {


    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val msg = update.message
            log.info("Message accepted: ${msg.text}")
            reply(msg)
        }
    }

    private fun reply(msg: Message) {
        val sendMsg = SendMessage()
        sendMsg.chatId = msg.chatId.toString()
        sendMsg.text = messageService.process(msg)
        sendMessage(sendMsg)
    }

    override fun getBotUsername(): String? {
        return telegramConfig.telegramBotName
    }

    override fun getBotToken(): String? {
        return telegramConfig.telegramToken
    }
}