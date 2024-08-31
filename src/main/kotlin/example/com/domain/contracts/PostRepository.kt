package example.com.domain.contracts

import example.com.domain.entities.Post

interface PostRepository {

    fun save(post: Post)

    fun findByOwnerId(ownerId: String): List<Post>

    fun findByFollows(followIds: List<String>): List<Post>
}