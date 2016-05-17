package ru.achugr.gamepart.domain

/**
 * author: achugr
 * date: 24.04.2016.
 */

class Player(val id: Int, val name: String) {

    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Player

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int{
        return id
    }


}