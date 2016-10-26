package ru.achugr.eventbot.config

import com.mongodb.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory

/**
 * author: achugr
 * date: 16.05.2016.
 */

open class MongoTestConfig {

    @Bean
    open fun mongoClient(): MongoClient {
        return MongoClient("localhost", 27017)
    }

    @Bean
    open fun mongoDbFactory(): MongoDbFactory {
        return SimpleMongoDbFactory(mongoClient(), "testdb")
    }

    @Bean
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoDbFactory())
    }

}