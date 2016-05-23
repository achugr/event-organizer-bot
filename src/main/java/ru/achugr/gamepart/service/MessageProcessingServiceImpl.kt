package ru.achugr.gamepart.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.api.objects.Message
import ru.achugr.gamepart.domain.Player
import ru.achugr.gamepart.handler.MainHandler
import java.util.*
import java.util.regex.Pattern


/**
 * author: achugr
 * date: 02.05.2016.
 */

val log = LoggerFactory.getLogger(MainHandler::class.java)

@Service
open class MessageProcessingServiceImpl @Autowired constructor(val gameService: GameService) : MessageProcessingService {

    val cmdRegexp = Pattern.compile("/(new|i|cant|game)[@GamepartBot]?(.*)")

    override fun process(message: Message): String {
        val preparedCommandStr = message.text.replace("@GamepartBot", "", ignoreCase = true)
        val m = cmdRegexp.matcher(preparedCommandStr)
        if (m.matches()) {
            log.info("Command ${m.group(1)} with arguments ${m.group(2)} will be processed")
            return process(message, m.group(1), m.group(2))
        } else {
            return "Can't process message: ${message.text}"
        }
    }

    fun process(message: Message, command: String, args: String?): String {
        val user = message.from
        val chatId = message.chatId

        when (command) {
            "new" -> {
                gameService.createGame(chatId)
                return "Game created"
            }
            "i" -> {
                val userName = if (user.firstName != null || user.lastName != null)
                    user.firstName + " " + user.lastName
                else user.userName

                val number: Int

                if (args == null || args.trim().isEmpty()) {
                    number = gameService.addPlayer(chatId, Player(user.id, userName))
                    return "User $userName added to game, players count: $number"
                } else {
                    val argsTrimed = args.trim();
                    val invitedUserName = "$argsTrimed (invited by $userName)"
                    number = gameService.addPlayer(chatId, Player(Random().nextInt(), invitedUserName))
                    return "User $argsTrimed added to game, players count: $number"
                }
            }
            "cant" -> {
                val number = gameService.removePlayer(chatId, user.id)
                return "User ${user.userName} removed from game, players count: $number"
            }
            "game" -> {
                val game = gameService.getActiveGame(chatId)
                return game.toString()
            }
        }

        return "Unknown command"
    }
}