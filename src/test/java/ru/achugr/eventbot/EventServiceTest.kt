package ru.achugr.eventbot

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import ru.achugr.eventbot.config.MongoTestConfig
import ru.achugr.eventbot.domain.InvitedParticipant
import ru.achugr.eventbot.domain.TelegramParticipant
import ru.achugr.eventbot.service.EventService
import java.util.*

/**
 * author: achugr
 * date: 27.04.2016.
 */

const val CHAT_ID = 123L


@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(Application::class, MongoTestConfig::class))
class EventServiceTest {

    @Autowired
    lateinit var eventService: EventService

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    @After
    fun after() {
        mongoTemplate.db.dropDatabase()
    }

    @Test
    fun createEmptyEvent() {
        eventService.createEvent(CHAT_ID)
        val event = eventService.getActiveEvent(CHAT_ID)

        assertThat(event, notNullValue())
        assertThat(event.participants, hasSize(0))
    }

    @Test
    fun testAddParticipant() {
        eventService.createEvent(CHAT_ID)

        val participant = TelegramParticipant(UUID.randomUUID().toString(), 123, "achugr")
        val invitedParticipant = InvitedParticipant(UUID.randomUUID().toString(), 123, "achugr", "friend")
        eventService.addParticipant(CHAT_ID, participant)
        eventService.addParticipant(CHAT_ID, invitedParticipant)
        val event = eventService.getActiveEvent(CHAT_ID)

        assertThat(event.participants, hasSize(2))
        assertThat(event.participants[0] as TelegramParticipant, `is`(participant))
        assertThat(event.participants[1] as InvitedParticipant, `is`(invitedParticipant))
    }
}