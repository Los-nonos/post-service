package example.com.infrastructure.http.actions

import example.com.application.commandhandlers.CreatePostHandler
import example.com.application.commands.CreatePostCommand
import example.com.infrastructure.http.dtos.CreatePostRequestBody
import io.ktor.http.*

class CreatePostAction(
    private val handler: CreatePostHandler
) {

    fun execute(body: CreatePostRequestBody, headers: Headers) {

        body.validate().let {
            val command = CreatePostCommand(
                it.content,
                headers.get("x-user-id").toString()
            )

            handler.handle(command)
        }
    }
}