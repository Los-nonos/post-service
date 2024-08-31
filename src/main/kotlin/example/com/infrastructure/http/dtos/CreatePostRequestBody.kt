package example.com.infrastructure.http.dtos

data class CreatePostRequestBody(
    val content: String = ""
) {

    fun validate(): CreatePostRequestBody {
        check(content.isEmpty()) { throw IllegalArgumentException("Content must be defined and not empty") }

        return this
    }
}