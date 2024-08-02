package example.com.application.commands


class DeleteUserCommand(
    val id: String
) {

    fun validate(): DeleteUserCommand{
        checkNotNull(id) {throw IllegalArgumentException("Id must be defined")}
        return this
    }
}