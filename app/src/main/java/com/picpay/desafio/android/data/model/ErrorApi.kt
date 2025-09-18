package com.picpay.desafio.android.data.model

import com.google.gson.annotations.SerializedName

data class ErrorApi(
    @SerializedName("message")
    val message: String = MSG_UNKNOWN_ERROR,
    @SerializedName("error_message")
    val errorMessage: String? = null,
    val code: Int? = null
){
    fun error() = errorMessage ?: message

    companion object {
        const val MSG_UNKNOWN_ERROR = "Algum erro desconhecido ocorreu. Tente novamente."
        const val NOT_HTTP_CODE = -1
        val EMPTY_ERROR = ErrorApi(
            message = MSG_UNKNOWN_ERROR,
            code = NOT_HTTP_CODE
        )
    }
}