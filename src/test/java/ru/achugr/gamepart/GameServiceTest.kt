package ru.achugr.gamepart

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.After
import ru.achugr.gamepart.config.MongoTestConfig
import ru.achugr.gamepart.domain.Player
import ru.achugr.gamepart.service.GameService
import org.hamcrest.Matchers.hasSize
import org.springframework.data.mongodb.core.MongoTemplate

/**
 * author: achugr
 * date: 27.04.2016.
 */

const val CHAT_ID = 123L


@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(Application::class, MongoTestConfig::class))
class GameServiceTest {

    @Autowired
    lateinit var gameService: GameService

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    @After
    fun after() {
        mongoTemplate.db.dropDatabase()
    }

    @Test
    fun createEmptyGame() {
        gameService.createGame(CHAT_ID)
        val game = gameService.getActiveGame(CHAT_ID)

        assertThat(game, notNullValue())
        assertThat(game.players, hasSize(0))
    }

    @Test
    fun testAddPlayer() {
        gameService.createGame(CHAT_ID)

        val player = Player(123, "achugr")
        gameService.addPlayer(CHAT_ID, player)

        val game = gameService.getActiveGame(CHAT_ID)

        assertThat(game.players, hasSize(1))
        assertThat(game.players.elementAt(0), `is`(player))
    }
}