package example.com.http.actions

import example.com.application.commands.UpdateUserCommand
import example.com.application.commandhandlers.UpdateUserHandler

class UpdateUserAction(
    private val handler: UpdateUserHandler
) {
    fun execute(body: UpdateUserCommand) {

        body.validate().let {
            handler.handle(it)
        }

    }


}