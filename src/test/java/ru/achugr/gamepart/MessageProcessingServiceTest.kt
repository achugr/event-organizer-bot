package ru.achugr.gamepart


import org.hamcrest.CoreMatchers.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import ru.achugr.gamepart.config.MongoTestConfig
import ru.achugr.gamepart.domain.Player
import ru.achugr.gamepart.service.GameService
import org.hamcrest.Matchers.hasSize
import org.springframework.data.mongodb.core.MongoTemplate
import org.telegram.telegrambots.api.objects.Message
import ru.achugr.gamepart.service.MessageProcessingService
import ru.achugr.gamepart.service.MessageProcessingServiceImpl
import java.util.regex.Pattern

/**
 * author: achugr
 * date: 23.05.2016.
 */


class MessageProcessingServiceTest {
    val cmdRegexp = Pattern.compile("/(new|i|cant|game)(.*)")

    @Test
    public fun addPlayerCommand() {
        val matcher  = cmdRegexp.matcher("/i")
        matcher.matches()
        assertThat(matcher.group(1), `is`("i"))
    }

}
