package example.com.infrastructure.http.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TimelineResult(
    val posts: List<Map<String, String>>
) {
}