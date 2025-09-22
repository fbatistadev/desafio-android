package com.picpay.desafio.android.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object UsersRoute
}