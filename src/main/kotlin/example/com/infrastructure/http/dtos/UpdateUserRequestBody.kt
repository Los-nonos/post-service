package example.com.infrastructure.http.dtos

import kotlinx.serialization.Serializable

@Serializable
class UpdateUserRequestBody(
    val username: String = ""
) {
    fun validate(): UpdateUserRequestBody {
        checkNotNull(username) { throw IllegalArgumentException("Username must be defined") }

        return this;
    }
}