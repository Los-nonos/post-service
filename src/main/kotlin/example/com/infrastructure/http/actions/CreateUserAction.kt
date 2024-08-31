package example.com.infrastructure.http.actions

import example.com.application.commands.CreateUserCommand
import example.com.application.commandhandlers.CreateUserHandler
import example.com.infrastructure.http.dtos.CreateUserRequestBody

class CreateUserAction(
    private val handler: CreateUserHandler
) {

    fun execute(body: CreateUserRequestBody) {

        body.validate().let {
            val command = CreateUserCommand(
                it.username,
                it.email
            )

            handler.handle(command)
        }

    }
}