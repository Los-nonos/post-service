package example.com.infrastructure.persistence

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import example.com.domain.contracts.UserRepository
import example.com.domain.entities.User
import org.bson.Document

class MongoUserRepository(database: MongoDatabase): UserRepository {

    private val collection: MongoCollection<Any> = database.getCollection("users") as MongoCollection<Any>;


    override fun save(user: User) {
        val options = UpdateOptions().upsert(true);

        val filter = Document("_id", user.getId()) // Usa el campo id como filter
        val update = Document("\$set", user.toPrimitives())

        collection.updateOne(filter, update, options)
    }

    override fun findOne(id: String): User? {
        val filter = Document("_id", id);

        val primitives = collection.find(filter).firstOrNull();

        if (primitives == null) {
            return null;
        }

        return User.fromPrimitives(primitives as Map<String, String>)
    }

    fun findAll(): List<User> {

        val primitives = collection.find().map { it as Document }.toList();

        return primitives.map {
            User.fromPrimitives(it.toMap() as Map<String, String>)
        };
    }

    override fun delete(user: User) {
        val filter = Document("_id", user.getId());

        collection.deleteOne(filter)
    }
}