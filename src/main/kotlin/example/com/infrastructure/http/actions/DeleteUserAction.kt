package example.com.infrastructure.http.actions

import example.com.application.commandhandlers.DeleteUserHandler
import example.com.application.commands.DeleteUserCommand
import io.ktor.http.*

class DeleteUserAction(
    private val handler: DeleteUserHandler
) {

    fun execute(parameters: Parameters) {

        val command = DeleteUserCommand(parameters["id"].toString())

        command.validate().let {
            handler.handle(it)
        }
    }
}