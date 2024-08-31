package example.com.application.queryhandlers

import example.com.application.queries.FindMyTimelineQuery
import example.com.domain.contracts.PostRepository
import example.com.domain.contracts.UserRepository
import example.com.domain.entities.Post
import io.ktor.server.plugins.*

class FindMyTimelineHandler(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
){

    fun handle(query: FindMyTimelineQuery): List<Post> {

        val user = userRepository.findOne(query.userId)

        if (user == null) {
            throw NotFoundException("user not found")
        }

        val posts = postRepository.findByFollows(user.getFollows())

        return posts
    }
}