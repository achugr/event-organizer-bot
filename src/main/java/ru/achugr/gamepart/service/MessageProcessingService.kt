package ru.achugr.gamepart.service

import org.telegram.telegrambots.api.objects.Message

/**
 * author: achugr
 * date: 10.05.2016.
 */

interface MessageProcessingService {
    fun process(message: Message): String

}