package example.com.shared.mocks

import example.com.domain.contracts.PostRepository
import example.com.domain.entities.Post

class MockPostRepository : PostRepository {

    private var posts: Array<Post> = emptyArray()

    override fun save(post: Post) {
        this.posts = this.posts.filter { it.getId() != post.getId() }.toTypedArray()
        this.posts = this.posts.plus(post)
    }

    override fun findByOwnerId(ownerId: String): List<Post> {
        return this.posts.filter { it.getOwnerId() == ownerId }.toList()
    }

    override fun findByFollows(followIds: List<String>): List<Post> {
        return this.posts.filter { followIds.contains(it.getOwnerId()) }.toList()
    }

    fun clean() {
        posts = emptyArray();
    }
}