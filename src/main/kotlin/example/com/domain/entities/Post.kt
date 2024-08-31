package example.com.domain.entities

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.UUID

class Post(
    private val id: String,
    private val content: String,
    private val ownerId: String,
    private val createdAt: LocalDateTime
) {

    var likes: List<String> = mutableListOf()

    companion object {
        fun create(
            content: String,
            user: User,
        ): Post {


            val post = Post(
                UUID.randomUUID().toString(),
                content,
                user.getId(),
                LocalDateTime.now()
            );

            return post
        }

        fun fromPrimitives(primitives: Map<String, Any>): Post {

            val post = Post(
                primitives["id"].toString(),
                primitives["content"].toString(),
                primitives["ownerId"].toString(),
                LocalDateTime.ofEpochSecond(primitives["createdAt"] as Long, 0, ZoneOffset.UTC)
            );

            post.likes = primitives["likes"] as List<String>

            return post
        }
    }

    fun like(user: User) {
        if (likes.contains(user.getId())) {
            return;
        }

        likes = likes.plus(user.getId())
    }

    fun unlike(user: User) {
        if (!likes.contains(user.getId())) {
            return;
        }

        likes = likes.filter { it != user.getId() }
    }

    fun toPrimitives(): Map<String, Any> {
        return mapOf(
            "id" to this.id,
            "content" to this.content,
            "ownerId" to this.ownerId,
            "likes" to this.likes,
            "createdAt" to this.createdAt.toEpochSecond(ZoneOffset.UTC)
        )
    }

    fun getId(): String {
        return this.id
    }

    fun getOwnerId(): String {
        return this.ownerId
    }

    fun getContent(): String {
        return this.content
    }

    fun getLikesCount(): Int {
        return this.likes.size
    }

    fun getCreatedAt(): String {
        return this.createdAt.toString()
    }
}