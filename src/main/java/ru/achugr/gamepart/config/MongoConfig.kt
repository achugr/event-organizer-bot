package ru.achugr.gamepart.config

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.stereotype.Component
import ru.achugr.gamepart.repository.GameRepositoryImpl
import javax.annotation.PostConstruct

const val MONGODB_URI = "MONGODB_URI"
const val MONGODB_DBNAME = "MONGODB_DBNAME"

val log = LoggerFactory.getLogger(MongoConfig::class.java)

@Component
open class MongoConfig {


//    @Value("\${mongo.uri}")
    var mongoUri: String = ""

//    @Value("\${mongo.dbname}")
    var dbName: String = ""

    @PostConstruct
    fun initFromEnvVars() {
        if (System.getenv(MONGODB_URI) != null) {
            log.info("Initializing mongo connection with env vars")
            mongoUri = System.getenv(MONGODB_URI)
            dbName = System.getenv(MONGODB_DBNAME)
        }
    }

    @Bean
    open fun mongoDbFactory(): org.springframework.data.mongodb.MongoDbFactory {
        return SimpleMongoDbFactory(MongoClient(MongoClientURI(mongoUri)), dbName)
    }

    @Bean
    open fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoDbFactory())
    }
}
