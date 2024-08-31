package example.com.infrastructure.http.dtos

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequestBody(
    val username: String = "",
    val email: String = ""
) {

    fun validate(): CreateUserRequestBody {
        checkNotNull(username) { throw IllegalArgumentException("Username must be defined") }
        checkNotNull(email) { throw IllegalArgumentException("Email must be defined") }

        return this;
    }
}