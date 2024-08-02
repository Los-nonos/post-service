package example.com.application.queryhandlers

import example.com.application.queries.FindUserByIdQuery
import example.com.infrastructure.persistence.MongoUserRepository
import io.ktor.server.plugins.*

class FindUserByIdHandler(
    private val userRepository: MongoUserRepository
) {

    fun handle(query: FindUserByIdQuery): Map<String, String> {

        val user = userRepository.findOne(query.id)

        if (user == null) {
            throw NotFoundException("user with id: ${query.id} not found")
        }

        return user.toPrimitives()
    }
}