package example.com.application.commands

import kotlinx.serialization.Serializable

data class CreateUserCommand(
    val username: String,
    val email: String,
) {

}