package example.com.domain.contracts

import example.com.domain.entities.User

interface UserRepository {
    fun save(user: User)

    fun findOne(id: String): User?

    fun delete(user: User)
}