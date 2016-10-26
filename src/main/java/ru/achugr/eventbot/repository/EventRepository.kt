package ru.achugr.eventbot.repository

import ru.achugr.eventbot.domain.Event

/**
 * author: achugr
 * date: 10.05.2016.
 */

interface EventRepository {
    fun markAllEventsAsInactive(chatId: Long): Unit

    fun getActiveEvent(chatId: Long): Event

    fun save(event: Event): Unit
}