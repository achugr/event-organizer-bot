package ru.achugr.eventbot.config

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * author: achugr
 * date: 08.05.2016.
 */

@Component
open class CommandsConfig {
    val log = LoggerFactory.getLogger(CommandsConfig::class.java)

//    @Value("\${command.new}")
    var cNew: String = ""

//    @Value("\${command.i}")
    var cI: String = ""

//    @Value("\${command.cant}")
    var cCant: String = ""

//    @Value("\${command.event}")
    var cEvent: String = ""

    @PostConstruct
    fun initFromEnvVars() {
        if (System.getenv("C_NEW") != null) {
            log.info("Initializing commands name with env vars")
            cNew = System.getenv("C_NEW")
            cI = System.getenv("C_I")
            cCant = System.getenv("C_CANT")
            cEvent = System.getenv("C_EVENT")
        }
    }
}
