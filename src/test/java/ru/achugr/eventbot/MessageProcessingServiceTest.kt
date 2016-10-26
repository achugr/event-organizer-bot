package ru.achugr.eventbot


import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.util.regex.Pattern

/**
 * author: achugr
 * date: 23.05.2016.
 */


class MessageProcessingServiceTest {
    val cmdRegexp = Pattern.compile("/(new|i|cant|event)(.*)")

    @Test
    public fun addParticipantCommand() {
        val matcher  = cmdRegexp.matcher("/i")
        matcher.matches()
        assertThat(matcher.group(1), `is`("i"))
    }

}
