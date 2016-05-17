package ru.achugr.gamepart.service

import ru.achugr.gamepart.domain.Game
import ru.achugr.gamepart.domain.Player

/**
 * author: achugr
 * date: 10.05.2016.
 */

interface GameService {
    open fun createGame(chatId: Long): Unit

    open fun getActiveGame(chatId: Long): Game

    fun addPlayer(chatId: Long, player: Player): Int

    fun removePlayer(chatId: Long, id: Int): Int

    fun getPlayers(chatId: Long): String
}