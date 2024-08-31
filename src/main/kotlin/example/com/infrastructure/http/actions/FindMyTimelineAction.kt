package example.com.infrastructure.http.actions

import example.com.application.queries.FindMyTimelineQuery
import example.com.application.queryhandlers.FindMyTimelineHandler
import io.ktor.http.*

class FindMyTimelineAction(private val handler: FindMyTimelineHandler){

    fun execute(
        headers: Headers
    ): List<Map<String, Any>> {
        val query = FindMyTimelineQuery(headers.get("x-user-id").toString())

        return handler.handle(query).map {
            return@map mapOf(
                "user" to it.getOwnerId(),
                "content" to it.getContent(),
                "created_at" to it.getCreatedAt(),
                "likes" to it.getLikesCount()
            )
        }
    }
}