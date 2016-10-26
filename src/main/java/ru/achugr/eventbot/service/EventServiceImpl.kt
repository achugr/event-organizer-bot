package ru.achugr.eventbot.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.achugr.eventbot.domain.Event
import ru.achugr.eventbot.domain.InvitedParticipant
import ru.achugr.eventbot.domain.Participant
import ru.achugr.eventbot.domain.TelegramParticipant
import ru.achugr.eventbot.repository.EventRepository

/**
 * Currently this service works in only one thread, so no reason to do some sync. mechanisms
 * Not sure that there are some reasons while we're using free heroku machines
 *
 * author: achugr
 * date: 25.04.2016.
 */


@Service
open class EventServiceImpl @Autowired constructor(val eventRepository: EventRepository) : EventService {

    override fun createEvent(chatId: Long): Unit {
        eventRepository.markAllEventsAsInactive(chatId)
        val event = Event(chatId, active = true)
        eventRepository.save(event)
    }

    override fun getActiveEvent(chatId: Long): Event {
        return eventRepository.getActiveEvent(chatId)
    }

    override fun addParticipant(chatId: Long, participant: Participant): Int? {
        val event = getActiveEvent(chatId)
        if (event.participants.contains(participant)) {
            return null
        } else {
            event.participants.add(participant)
            eventRepository.save(event)
            return event.participants.size
        }
    }

    override fun removeParticipant(chatId: Long, telegramId: Int): Int {
        val event = getActiveEvent(chatId)
        val iterator = event.participants.iterator()
        iterator.forEach {
            participant ->
            if (participant is TelegramParticipant && participant.telegramId == telegramId) {
                iterator.remove()
            }
        }
        eventRepository.save(event)
        return event.participants.size
    }

    override fun removeParticipantByPosition(chatId: Long, callerId: Int, positionId: Int): Event? {
        val event = eventRepository.getActiveEvent(chatId)
        val participant = event.participants[positionId - 1]
        if (participant is InvitedParticipant) {
            if (participant.invitedBy == callerId) {
                if (positionId < 1) {
                    return null
                }
                event.participants.removeAt(positionId - 1)
                eventRepository.save(event)
                return event
            } else {
                return null
            }
        } else {
            return null
        }
    }

}