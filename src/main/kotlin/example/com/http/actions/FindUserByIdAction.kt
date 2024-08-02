package example.com.http.actions

import example.com.application.queries.FindUserByIdQuery
import example.com.application.queryhandlers.FindUserByIdHandler

class FindUserByIdAction(
    private val handler: FindUserByIdHandler
) {

    fun execute(query: FindUserByIdQuery): Map<String, String> {
        query
            .validate()
            .let {
                return handler.handle(it)
            }

    }
}