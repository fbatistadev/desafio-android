package com.picpay.desafio.android.data.api

import com.picpay.desafio.android.data.model.response.ApiUser
import retrofit2.http.GET

interface UserService {
    @GET("v1/picpay/users")
    suspend fun getUsers(): List<ApiUser>
}