package com.picpay.desafio.android.data.api.exception

import java.io.IOException

class NetworkUnavailableException(message: String = "Verifique sua conexão com a internet") : IOException(message)

class NetworkException(message: String): Exception(message)