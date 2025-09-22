package com.picpay.desafio.android.presentation.feature.users

import com.picpay.desafio.android.domain.models.User

data class UsersUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String? = null
)