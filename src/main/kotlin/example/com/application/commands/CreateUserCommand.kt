package example.com.application.commands

import kotlinx.serialization.Serializable

@Serializable()
data class CreateUserCommand(
    val name: String,
    val lastName: String,
    val email: String,
) {
    fun validate(): CreateUserCommand {
        checkNotNull(name) { throw IllegalArgumentException("Name must be defined") }
        checkNotNull(lastName) { throw IllegalArgumentException("LastName must be defined") }
        checkNotNull(email) { throw IllegalArgumentException("Email must be defined") }

        return this;
    }
}