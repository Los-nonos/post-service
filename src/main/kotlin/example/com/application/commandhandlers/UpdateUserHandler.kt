package example.com.application.commandhandlers

import example.com.application.commands.UpdateUserCommand
import example.com.domain.contracts.UserRepository
import io.ktor.server.plugins.*

class UpdateUserHandler(
    private val userRepository: UserRepository
) {

    fun handle(command: UpdateUserCommand) {
        val user = userRepository.findOne(command.id)

        if (user == null) {
            throw NotFoundException("not found user with id: ${command.id}")
        }

        user.update(command.username)
    }
}