package com.picpay.desafio.android.data.model.response

import com.google.gson.annotations.SerializedName

data class ApiUser(
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("document")
    val id: Int,
    @SerializedName("document")
    val username: String,
)