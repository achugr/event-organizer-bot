package ru.achugr.eventbot.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.User
import ru.achugr.eventbot.config.CommandsConfig
import ru.achugr.eventbot.config.TelegramConfig
import ru.achugr.eventbot.domain.InvitedParticipant
import ru.achugr.eventbot.domain.Participant
import ru.achugr.eventbot.domain.TelegramParticipant
import ru.achugr.eventbot.handler.MainHandler
import java.util.*
import java.util.regex.Pattern


/**
 * author: achugr
 * date: 02.05.2016.
 */

@Service
open class MessageProcessingService @Autowired constructor(val telegramConfig: TelegramConfig, val commandsConfig: CommandsConfig, val eventService: EventService) {
    val log = LoggerFactory.getLogger(MainHandler::class.java)

    val cmdRegexp = Pattern.compile("""/(${commandsConfig.cNew}|${commandsConfig.cI}|""" +
            """${commandsConfig.cCant}|${commandsConfig.cEvent})(.*)""")


    fun process(message: Message): String {
        val preparedCommandStr = message.text.replace("@${telegramConfig.telegramBotName}", "", ignoreCase = true)
        val m = cmdRegexp.matcher(preparedCommandStr)
        if (m.matches()) {
            log.info("Command ${m.group(1)} with arguments ${m.group(2)} would be processed")
            return process(message, m.group(1), m.group(2))
        } else {
            return "Can't process message: ${message.text}"
        }
    }

    fun process(message: Message, command: String, args: String?): String {
        val user = message.from
        val chatId = message.chatId

        when (command) {
            commandsConfig.cNew -> return createEvent(chatId)
            commandsConfig.cI -> return addParticipant(args, chatId, user)
            commandsConfig.cCant -> return removeParticipant(args, chatId, user)
            commandsConfig.cEvent -> return getActiveEvent(chatId)
        }
        return "Please, speak my robo-language(new, i, cant, event)"
    }

    private fun getActiveEvent(chatId: Long): String {
        val event = eventService.getActiveEvent(chatId)
        return event.toString()
    }

    private fun removeParticipant(args: String?, chatId: Long, user: User): String {
        if (args == null || args.trim().isEmpty()) {
            val number = eventService.removeParticipant(chatId, user.id)
            return "User ${getUserName(user)} removed from event, participants count: $number"
        } else {
            val trimmedArg = args.trim()
            val position = Integer.parseInt(trimmedArg)
            val event = eventService.removeParticipantByPosition(chatId, user.id, position)
            if (event != null) {
                return event.toString()
            } else {
                return "User at position $trimmedArg couldn't be removed. Maybe you don't have rights, or specified wrong index"
            }
        }
    }

    private fun addParticipant(args: String?, chatId: Long, user: User): String {
        val userName = getUserName(user)

        val number: Int?
        val participant: Participant
        if (args == null || args.trim().isEmpty()) {
            participant = TelegramParticipant(UUID.randomUUID().toString(), user.id, userName)
        } else {
            val invitedUserName = args.trim()
            participant = InvitedParticipant(UUID.randomUUID().toString(),
                    user.id,
                    userName,
                    invitedUserName)
        }

        number = eventService.addParticipant(chatId, participant)
        if (number != null) {
            return "User ${participant.name} added to event, participants count: $number"
        } else {
            return "User hasn't added, probably he's already joined"
        }
    }

    private fun createEvent(chatId: Long): String {
        eventService.createEvent(chatId)
        return "Event created"
    }

    private fun getUserName(user: User): String {
//        try to build best name from nullable values
        if (user.firstName != null && user.lastName != null) {
            return user.firstName + " " + user.lastName
        } else if (user.userName != null) {
            return user.userName
        } else if (user.lastName != null) {
            return user.lastName
        } else if (user.firstName != null) {
            return user.firstName
        }

        return "Unknown telegram user"
    }
}