package example.com.infrastructure.http.router

import example.com.application.commands.CreateUserCommand
import example.com.application.commands.UpdateUserCommand
import example.com.application.commandhandlers.CreateUserHandler
import example.com.application.commandhandlers.DeleteUserHandler
import example.com.application.commandhandlers.UpdateUserHandler
import example.com.application.commands.DeleteUserCommand
import example.com.application.queries.FindUserByIdQuery
import example.com.application.queryhandlers.FindUserByIdHandler
import example.com.infrastructure.http.actions.CreateUserAction
import example.com.infrastructure.http.actions.DeleteUserAction
import example.com.infrastructure.http.actions.FindUserByIdAction
import example.com.infrastructure.http.actions.UpdateUserAction
import example.com.infrastructure.http.dtos.CreateUserRequestBody
import example.com.infrastructure.http.dtos.UpdateUserRequestBody
import example.com.infrastructure.persistence.MongoUserRepository
import example.com.infrastructure.persistence.connectToMongoDB
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.userRouter() {
    val mongoDatabase = connectToMongoDB()

    val userMongoUserRepository = MongoUserRepository(mongoDatabase)

    val createUserAction = CreateUserAction(CreateUserHandler(userMongoUserRepository))

    val updateUserAction = UpdateUserAction(UpdateUserHandler(userMongoUserRepository))
    val findUserByIdAction = FindUserByIdAction(FindUserByIdHandler(userMongoUserRepository))
    val deleteUserAction = DeleteUserAction(DeleteUserHandler(userMongoUserRepository))

    routing {

        post("/users") {
            val body = call.receive<CreateUserRequestBody>()
            createUserAction.execute(body);

            call.respond(HttpStatusCode.Created, mapOf("message" to "ok"))
        }

        put("/users/{id}") {
            val body = call.receive<UpdateUserRequestBody>()

            updateUserAction.execute(body, call.parameters);

            call.respond(HttpStatusCode.OK, mapOf("message" to "updated"))
        }

        get("/users/{id}") {
            val result = findUserByIdAction.execute(call.parameters)

            call.respond(HttpStatusCode.OK, result)

        }

        get("/users") {
            val users = userMongoUserRepository.findAll();

            call.respond(HttpStatusCode.OK, users.map { it.toPrimitives() })
        }


        delete("/users/{id}") {
            deleteUserAction.execute(call.parameters)

            call.respond(HttpStatusCode.NoContent)
        }
    }
}