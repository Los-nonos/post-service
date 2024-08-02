package example.com.application.commands

import kotlinx.serialization.Serializable

@Serializable()
data class UpdateUserCommand(
    val name: String,
    val lastName: String,
) {

    var id: String = ""

    fun validate(): UpdateUserCommand {
        checkNotNull(id) { throw IllegalArgumentException("Id must be defined") }
        check(id.isNotEmpty()) { throw IllegalArgumentException("Id must be defined") }
        checkNotNull(name) { throw IllegalArgumentException("Name must be defined") }
        checkNotNull(lastName) { throw IllegalArgumentException("LastName must be defined") }

        return this;
    }
}