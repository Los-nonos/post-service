package example.com

import com.fasterxml.jackson.databind.SerializationFeature
import example.com.http.router.userRouter
import example.com.infrastructure.configureDatabases
import io.ktor.serialization.jackson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    configureDatabases()
    userRouter()
}
