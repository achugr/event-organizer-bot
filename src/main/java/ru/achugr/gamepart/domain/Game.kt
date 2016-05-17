package ru.achugr.gamepart.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * author: achugr
 * date: 24.04.2016.
 */

@Document(collection = "games")
//TODO how to make chatId required parameter? If we do it val, we need empty constructor without it.
class Game(var chatId: Long = 0L, val players: MutableSet<Player> = mutableSetOf(), @Id val id: ObjectId = ObjectId(),
           var active: Boolean = false) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Game

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        val sb = StringBuilder()

        for ((index, player) in players.withIndex()) {
            sb.append("#${index + 1}: ${player.name}\n")
        }
        return sb.toString()
    }
}
