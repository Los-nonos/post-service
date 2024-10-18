package example.com.shared.mothers

import example.com.domain.entities.Post
import io.github.serpro69.kfaker.Faker
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.UUID

class PostMother {

    companion object {
        val faker = Faker()

        fun random(ownerId: String): Post {
            return Post.fromPrimitives(
                mapOf(
                    "id" to UUID.randomUUID().toString(),
                    "content" to faker.lorem.words(),
                    "ownerId" to ownerId,
                    "createdAt" to LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                    "likes" to emptyList<String>()
                )
            )
        }
    }
}