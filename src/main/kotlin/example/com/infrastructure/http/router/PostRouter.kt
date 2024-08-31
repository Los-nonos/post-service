package example.com.infrastructure.http.router

import example.com.application.commandhandlers.CreatePostHandler
import example.com.application.queryhandlers.FindMyTimelineHandler
import example.com.infrastructure.http.actions.CreatePostAction
import example.com.infrastructure.http.actions.FindMyTimelineAction
import example.com.infrastructure.http.dtos.CreatePostRequestBody
import example.com.infrastructure.persistence.MongoPostRepository
import example.com.infrastructure.persistence.MongoUserRepository
import example.com.infrastructure.persistence.connectToMongoDB
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.postRouter() {
    val mongoDatabase = connectToMongoDB()

    val userMongoRepository = MongoUserRepository(mongoDatabase)
    val postMongoRepository = MongoPostRepository(mongoDatabase)


    val createPostHandler = CreatePostHandler(
        userMongoRepository,
        postMongoRepository
    )
    val findMyTimelineHandler = FindMyTimelineHandler(userMongoRepository, postMongoRepository)

    val createPostAction = CreatePostAction(createPostHandler)
    val findMyTimelineAction = FindMyTimelineAction(findMyTimelineHandler)

    routing {
        post("/posts") {
            val body = call.receive<CreatePostRequestBody>()

            createPostAction.execute(body, call.request.headers)

            call.respond(HttpStatusCode.Created, mapOf("message" to "ok"))
        }

        get("/timeline") {
            val response = findMyTimelineAction.execute(call.request.headers)

            call.respond(HttpStatusCode.OK, response)
        }
    }
}