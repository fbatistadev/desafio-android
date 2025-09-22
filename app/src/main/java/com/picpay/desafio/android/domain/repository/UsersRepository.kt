package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getUsers(): Flow<List<User>>
}