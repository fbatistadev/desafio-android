package com.picpay.desafio.android

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log

class MainViewModel : ViewModel() {

    data class UiState(
        val users: List<User> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val url = "http://192.168.0.5:3003/api/"

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val service: UserService by lazy {
        retrofit.create(UserService::class.java)
    }

    fun loadUsers() {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            errorMessage = null
        )

        service.getUsers().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Erro ao carregar usuários"
                )
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body() ?: emptyList()
                    Log.d("MainViewModel", "Received ${users.size} users from API")
                    users.forEach { user ->
                        Log.d("MainViewModel", "User: ${user.name}, img: ${user.img}")
                    }

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        users = users,
                        errorMessage = null
                    )
                } else {
                    Log.e("MainViewModel", "API response not successful: ${response.code()}")
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Erro ao carregar usuários"
                    )
                }
            }
        })
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}