package example.com.application.commandhandlers

import example.com.application.commands.DeleteUserCommand
import example.com.domain.contracts.UserRepository
import io.ktor.server.plugins.*

class DeleteUserHandler(
    private val userRepository: UserRepository
) {

    fun handle(command: DeleteUserCommand) {

        val user = userRepository.findOne(command.id)

        if (user == null) {
            throw NotFoundException("not found user with id: ${command.id}")
        }

        userRepository.delete(user);
    }
}