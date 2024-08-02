package example.com.http.actions

import example.com.application.commandhandlers.DeleteUserHandler
import example.com.application.commands.DeleteUserCommand

class DeleteUserAction(
    private val handler: DeleteUserHandler
) {

    fun execute(command: DeleteUserCommand) {
        command.validate().let {
            handler.handle(it)
        }
    }
}