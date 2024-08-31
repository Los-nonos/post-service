package example.com.application.commands

import kotlinx.serialization.Serializable

data class UpdateUserCommand(
    val id: String,
    val username: String,
) {


}