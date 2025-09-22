package com.picpay.desafio.android.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.picpay.desafio.android.data.api.UserService
import com.picpay.desafio.android.data.repositories.UsersRepositoryImpl
import com.picpay.desafio.android.domain.UseCase
import com.picpay.desafio.android.domain.repository.UsersRepository
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.presentation.feature.users.UsersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

class PicPayModuleInitialization : ModuleInitialization() {

    //region Network
    private val networkModule = module {
        single {
            Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl("http://192.168.0.5:3003/api/")
                .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
                .build()
        }
    }

    private val serviceModule = module {
        single<UserService> {
            get<Retrofit>().create(UserService::class.java)
        }
    }
    //endregion

    //region Repositories
    private val repositoriesModule = module {
        single<UsersRepository> {
            UsersRepositoryImpl(get())
        }
    }
    //endregion

    //region Use Cases
    private val useCaseModule = module {
        single {
            UseCase.Configuration(Dispatchers.IO)
        }

        single {
            GetUsersUseCase(get(), get())
        }
    }
    //endregion

    //region ViewModels
    private val viewModelModule = module {
        viewModel {
            UsersViewModel(get())
        }
    }
    //endregion

    override fun init(): List<Module> = listOf(
        networkModule,
        serviceModule,
        repositoriesModule,
        useCaseModule,
        viewModelModule
    )
}