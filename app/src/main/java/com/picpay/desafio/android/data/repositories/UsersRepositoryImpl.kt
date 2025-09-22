package com.picpay.desafio.android.data.repositories

import com.picpay.desafio.android.data.api.UserService
import com.picpay.desafio.android.data.mappers.toDomain
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class UsersRepositoryImpl(
    private val userService: UserService
) : UsersRepository {

    override fun getUsers(): Flow<List<User>> = flow {
        emit(userService.getUsers())
    }.map { response ->
        response.map { it.toDomain() }
    }
}