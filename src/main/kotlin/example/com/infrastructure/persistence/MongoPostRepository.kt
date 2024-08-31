package example.com.infrastructure.persistence

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import example.com.domain.contracts.PostRepository
import example.com.domain.entities.Post
import org.bson.Document

class MongoPostRepository(database: MongoDatabase) : PostRepository {

    private val collection: MongoCollection<Any> = database.getCollection("posts") as MongoCollection<Any>;

    override fun save(post: Post) {
        val options = UpdateOptions().upsert(true);

        val filter = Document("_id", post.getId()) // Usa el campo id como filter
        val update = Document("\$set", post.toPrimitives())

        collection.updateOne(filter, update, options)
    }

    override fun findByOwnerId(ownerId: String): List<Post> {
        val filter = Document("ownerId", ownerId);

        val primitives = collection.find(filter)
            .sort(Document("createdAt", 1)).toList()

        return primitives.map { Post.fromPrimitives(it as Map<String, Any>) }
    }

    override fun findByFollows(followIds: List<String>): List<Post> {
        val filter = Document("ownerId", Document("\$in", followIds))

        val primitives = collection.find(filter).sort(
            Document("createdAt", 1)
        ).toList()

        return primitives.map { Post.fromPrimitives(it as Map<String, Any>) }
    }
}