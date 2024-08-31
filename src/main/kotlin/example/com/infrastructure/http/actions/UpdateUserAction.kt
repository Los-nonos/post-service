package example.com.infrastructure.http.actions

import example.com.application.commands.UpdateUserCommand
import example.com.application.commandhandlers.UpdateUserHandler
import example.com.infrastructure.http.dtos.UpdateUserRequestBody
import io.ktor.http.*

class UpdateUserAction(
    private val handler: UpdateUserHandler
) {
    fun execute(body: UpdateUserRequestBody, params: Parameters) {

        body.validate().let {

            val command = UpdateUserCommand(
                params.get("id").toString(),
                it.username
            )

            handler.handle(command)
        }

    }


}