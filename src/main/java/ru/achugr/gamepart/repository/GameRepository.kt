package ru.achugr.gamepart.repository

import ru.achugr.gamepart.domain.Game
import ru.achugr.gamepart.domain.Player

/**
 * author: achugr
 * date: 10.05.2016.
 */

interface GameRepository {
    fun markAllGamesAsInactive(chatId: Long): Unit

    open fun createGame(chatId: Long): Unit

    open fun getActiveGame(chatId: Long): Game

    open fun addPlayer(chatId: Long, player: Player): Int

    open fun removePlayer(chatId: Long, id: Int): Int

    open fun getGamePlayers(chatId: Long): kotlin.collections.Set<Player>
}