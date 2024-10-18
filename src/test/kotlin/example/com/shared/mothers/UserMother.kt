package example.com.shared.mothers

import example.com.domain.entities.User
import io.github.serpro69.kfaker.Faker
import java.util.UUID

class UserMother {

    companion object {
        val faker = Faker()

        fun random(): User {
            return User.fromPrimitives(
                mapOf(
                    "id" to UUID.randomUUID().toString(),
                    "username" to faker.southPark.characters(),
                    "email" to faker.internet.email(),
                    "followers" to emptyList<String>(),
                    "follows" to emptyList<String>()
                )
            )
        }

    }
}