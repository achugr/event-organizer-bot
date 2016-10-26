package ru.achugr.eventbot.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.Update.update
import org.springframework.stereotype.Repository
import ru.achugr.eventbot.domain.Event

/**
 * author: achugr
 * date: 24.04.2016.
 */

@Repository
open class EventRepositoryImpl @Autowired constructor(val mongoTemplate: MongoTemplate) : EventRepository {
    override fun save(event: Event) {
        mongoTemplate.save(event)
    }

    override fun markAllEventsAsInactive(chatId: Long): Unit {
        mongoTemplate.updateMulti(query(where("active").`is`(true).and("chatId").`is`(chatId)),
                update("active", false), Event::class.java)
    }

    override fun getActiveEvent(chatId: Long): Event {
        return mongoTemplate.findOne(query(where("active").`is`(true).and("chatId").`is`(chatId)), Event::class.java)
    }

}