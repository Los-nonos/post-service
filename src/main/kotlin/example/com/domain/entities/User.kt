package example.com.domain.entities

class User(
    private val id: String,
    private var username: String,
    private val email: String,
) {

    private var followers: List<String> = mutableListOf()
    private var follows: List<String> = mutableListOf()

    companion object {
        fun fromPrimitives(primitives: Map<String, Any>): User {

            val user = User(
                primitives["id"] as String,
                primitives["username"] as String,
                primitives["email"] as String
            );

            user.followers = primitives["followers"] as List<String>
            user.follows = primitives["follows"] as List<String>

            return user;
        }
    }

    fun getId(): String {
        return this.id;
    }

    fun update(username: String) {
        this.username = username
    }

    fun toPrimitives(): Map<String, Any> {
        return mapOf(
            "id" to this.id,
            "username" to this.username,
            "email" to this.email,
            "followers" to this.followers,
            "follows" to this.follows
        )
    }

    fun getFollows(): List<String> {
        return this.follows
    }

    fun getUsername(): String{
        return this.username
    }

    fun getEmail(): String {
        return this.email
    }
}