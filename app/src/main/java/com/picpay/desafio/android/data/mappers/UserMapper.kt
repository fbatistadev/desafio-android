package com.picpay.desafio.android.data.mappers

import com.picpay.desafio.android.data.model.response.ApiUser
import com.picpay.desafio.android.domain.models.User

fun ApiUser.toDomain(): User {
    return User(
        img = this.img,
        name = this.name,
        id = this.id,
        username = this.username
    )
}
