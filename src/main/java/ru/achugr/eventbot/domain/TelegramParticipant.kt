package ru.achugr.eventbot.domain

/**
 * author: achugr
 * date: 25.10.2016.
 */


class TelegramParticipant(id: String, val telegramId: Int, name: String) : Participant(id, name) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as TelegramParticipant

        if (telegramId != other.telegramId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + telegramId
        return result
    }

    override fun toString(): String {
        return name
    }


}