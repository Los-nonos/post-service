package example.com.unit.application

import example.com.application.commandhandlers.CreateUserHandler
import example.com.application.commands.CreateUserCommand
import example.com.shared.mocks.MockUserRepository
import example.com.shared.mothers.UserMother
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull

class CreateUserHandlerTest {

    val mockUserRepository = MockUserRepository()

    var sut: CreateUserHandler = CreateUserHandler(mockUserRepository)

    @BeforeTest
    fun beforeEach() {
        mockUserRepository.clean()
    }

    @Test
    fun `should create a user and persist into database`() {
        // arrange
        val username = UserMother.faker.southPark.characters();

        val command = CreateUserCommand(
            username,
            UserMother.faker.internet.email(),
        )

        // act
        sut.handle(command)

        //assertion
        val user = mockUserRepository.findByUsername(username)

        assertNotNull(user)
        assert(user.getEmail() == command.email)
    }
}