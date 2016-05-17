package ru.achugr.gamepart.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.achugr.gamepart.domain.Game
import ru.achugr.gamepart.domain.Player
import ru.achugr.gamepart.repository.GameRepository

/**
 * author: achugr
 * date: 25.04.2016.
 */

@Service
open class GameServiceImpl @Autowired constructor(val gameRepository: GameRepository) : GameService {

    override fun createGame(chatId: Long): Unit {
        gameRepository.createGame(chatId)
    }

    override fun getActiveGame(chatId: Long): Game {
        return gameRepository.getActiveGame(chatId)
    }

    override fun addPlayer(chatId: Long, player: Player): Int {
        return gameRepository.addPlayer(chatId, player)
    }

    override fun removePlayer(chatId: Long, id: Int): Int {
        return gameRepository.removePlayer(chatId, id)
    }

    override fun getPlayers(chatId: Long): String {
        return gameRepository.getGamePlayers(chatId).joinToString(separator = "\n")
    }
}