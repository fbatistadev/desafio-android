package com.picpay.desafio.android.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiUser(
    @SerialName("img")
    val img: String,
    @SerialName("name")
    val name: String,
    @SerialName("id")
    val id: Int,
    @SerialName("username")
    val username: String,
)