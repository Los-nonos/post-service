package example.com.http.actions

import example.com.application.commands.CreateUserCommand
import example.com.application.commandhandlers.CreateUserHandler

class CreateUserAction(
    private val handler: CreateUserHandler
) {

    fun execute(body: CreateUserCommand) {

        body.validate().let {
            handler.handle(it)
        }

    }
}