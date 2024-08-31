package example.com.application.commandhandlers

import example.com.application.commands.CreatePostCommand
import example.com.domain.contracts.PostRepository
import example.com.domain.contracts.UserRepository
import example.com.domain.entities.Post
import io.ktor.server.plugins.*

class CreatePostHandler(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
) {


    fun handle(command: CreatePostCommand) {

        val user = this.userRepository.findOne(command.ownerId)

        if (user == null) {
            throw NotFoundException("user not found")
        }

        val post = Post.create(command.content, user)

        this.postRepository.save(post)
    }
}