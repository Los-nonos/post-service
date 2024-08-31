package example.com.application.commandhandlers

import example.com.application.commands.CreateUserCommand
import example.com.domain.contracts.UserRepository
import example.com.domain.entities.User
import java.util.UUID

class CreateUserHandler(
    private val userRepository: UserRepository
) {
    fun handle(command: CreateUserCommand) {
        val user = User(
            UUID.randomUUID().toString(),
            command.username,
            command.email
        )

        userRepository.save(user)
    }


}