package example.com.application.commands

data class CreatePostCommand(
    val content: String,
    val ownerId: String
)