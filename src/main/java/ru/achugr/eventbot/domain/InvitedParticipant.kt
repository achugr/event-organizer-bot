package ru.achugr.eventbot.domain

/**
 * author: achugr
 * date: 25.10.2016.
 */


class InvitedParticipant(id: String, val invitedBy: Int, val invitedByName: String, name: String) : Participant(id, name) {

    override fun toString(): String {
        return "$name (invited by $invitedByName)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as InvitedParticipant

        if (invitedBy != other.invitedBy) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + invitedBy
        result = 31 * result + invitedByName.hashCode()
        return result
    }

}