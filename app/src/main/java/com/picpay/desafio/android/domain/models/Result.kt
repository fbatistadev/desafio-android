package com.picpay.desafio.android.domain.models

import com.picpay.desafio.android.domain.UseCase

interface Result<out T : UseCase.Response> {
    data class Success<out T : UseCase.Response>(val data: T) : Result<T>
    object NetworkException : Result<Nothing>
    class Error(val throwable: Throwable) : Result<Nothing>
}