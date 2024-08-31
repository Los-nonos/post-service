package example.com.infrastructure.http.actions

import example.com.application.queries.FindMyTimelineQuery
import example.com.application.queryhandlers.FindMyTimelineHandler
import example.com.infrastructure.http.dtos.TimelineResult
import io.ktor.http.*

class FindMyTimelineAction(private val handler: FindMyTimelineHandler){

    fun execute(
        headers: Headers
    ): TimelineResult {
        val query = FindMyTimelineQuery(headers.get("x-user-id").toString())

        val result = handler.handle(query).map {
            return@map mapOf(
                "user" to it.getOwnerId(),
                "content" to it.getContent(),
                "created_at" to it.getCreatedAt(),
                "likes" to it.getLikesCount().toString()
            )
        }

        return TimelineResult(result)
    }
}