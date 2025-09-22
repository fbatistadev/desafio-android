package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.UseCase
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUsersUseCase(
    configuration: Configuration,
    private val usersRepository: UsersRepository
) : UseCase<GetUsersUseCase.Request, GetUsersUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> {
        return usersRepository.getUsers().map { users ->
            Response(users)
        }
    }

    object Request : UseCase.Request

    data class Response(val users: List<User>) : UseCase.Response
}