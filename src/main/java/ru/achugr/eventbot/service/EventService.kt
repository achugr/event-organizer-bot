package ru.achugr.eventbot.service

import ru.achugr.eventbot.domain.Event
import ru.achugr.eventbot.domain.Participant

/**
 * author: achugr
 * date: 10.05.2016.
 */

interface EventService {
    fun createEvent(chatId: Long): Unit

    fun getActiveEvent(chatId: Long): Event

    fun addParticipant(chatId: Long, participant: Participant): Int?

    fun removeParticipant(chatId: Long, telegramId: Int): Int

    fun removeParticipantByPosition(chatId: Long, callerId: Int, positionId: Int): Event?
}