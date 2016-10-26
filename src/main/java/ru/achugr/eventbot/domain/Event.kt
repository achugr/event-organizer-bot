package ru.achugr.eventbot.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * author: achugr
 * date: 24.04.2016.
 */

@Document(collection = "events")
//TODO how to make chatId required parameter? If we do it val, we need empty constructor without it.
class Event(val chatId: Long = 0L, val participants: MutableList<Participant> = mutableListOf(), @Id val id: ObjectId = ObjectId(),
            var active: Boolean = false) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Event

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        if (participants.size > 0) {
            val sb = StringBuilder()

            for ((index, participant) in participants.withIndex()) {
                sb.append("#${index + 1}: $participant\n")
            }
            return sb.toString()
        } else {
            return "Event hasn't participants"
        }
    }
}
