package com.picpay.desafio.android

class ExampleService(
    private val service: UserService
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}