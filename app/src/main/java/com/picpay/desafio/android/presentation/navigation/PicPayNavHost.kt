package com.picpay.desafio.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.picpay.desafio.android.presentation.feature.users.UsersRoute

@Composable
fun PicPayNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.UsersRoute) {
        composable<Route.UsersRoute> {
            UsersRoute()
        }
    }
}