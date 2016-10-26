package ru.achugr.eventbot.domain

/**
 * author: achugr
 * date: 24.04.2016.
 */

abstract class Participant(val id: String, val name: String) {

    override fun toString(): String {
        return name
    }
}