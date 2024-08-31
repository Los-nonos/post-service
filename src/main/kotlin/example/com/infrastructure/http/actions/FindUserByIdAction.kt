package example.com.infrastructure.http.actions

import example.com.application.queries.FindUserByIdQuery
import example.com.application.queryhandlers.FindUserByIdHandler
import io.ktor.http.*
import io.ktor.server.application.*

class FindUserByIdAction(
    private val handler: FindUserByIdHandler
) {

    fun execute(parameters: Parameters): Map<String, Any> {

        val query = FindUserByIdQuery(parameters.get("id").toString())

        query
            .validate()
            .let {
                return handler.handle(it)
            }

    }
}