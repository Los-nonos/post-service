package example.com

import com.fasterxml.jackson.databind.SerializationFeature
import example.com.infrastructure.http.router.postRouter
import example.com.infrastructure.http.router.userRouter
import example.com.infrastructure.persistence.configureDatabases
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.slf4j.LoggerFactory

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.errorHandler() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            logError(call, cause)

            if (cause is NotFoundException) {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to cause.message))
            } else {
                call.respond(HttpStatusCode.InternalServerError, mapOf("error" to "Internal server error"))
            }

        }
    }
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
    postRouter()
    errorHandler()
}



fun logError(call: ApplicationCall, cause: Throwable) {
    val log = LoggerFactory.getLogger("ErrorLogger")
    val requestUri = call.request.uri
    log.error("Error at $requestUri: ${cause.localizedMessage}", cause)
}