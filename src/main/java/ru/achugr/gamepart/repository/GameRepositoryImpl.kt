package ru.achugr.gamepart.repository

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.*
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.Update.*
import org.springframework.stereotype.Repository
import ru.achugr.gamepart.domain.Game
import ru.achugr.gamepart.domain.Player

/**
 * author: achugr
 * date: 24.04.2016.
 */
val log = LoggerFactory.getLogger(GameRepositoryImpl::class.java)

@Repository
open class GameRepositoryImpl @Autowired constructor(val mongoTemplate: MongoTemplate) : GameRepository {

    override fun markAllGamesAsInactive(chatId: Long): Unit {
        mongoTemplate.updateMulti(query(where("active").`is`(true).and("chatId").`is`(chatId)),
                update("active", false), Game::class.java)
    }

    override fun createGame(chatId: Long): Unit {
        log.info("creating new game")
        markAllGamesAsInactive(chatId)
        val game = Game(chatId)
        game.active = true
        mongoTemplate.save(game)
    }

    override fun getActiveGame(chatId: Long): Game {
        return mongoTemplate.findOne(query(where("active").`is`(true).and("chatId").`is`(chatId)), Game::class.java)
    }

    override fun addPlayer(chatId: Long, player: Player): Int {
        val game = getActiveGame(chatId)
        game.players.add(player)
        mongoTemplate.save(game)
        return game.players.size
    }

    override fun removePlayer(chatId: Long, id: Int): Int {
        val game = getActiveGame(chatId)
        game.players.remove(Player(id, ""))
        mongoTemplate.save(game)
        return game.players.size
    }

    override fun getGamePlayers(chatId: Long): kotlin.collections.Set<Player> {
        return getActiveGame(chatId).players
    }
}