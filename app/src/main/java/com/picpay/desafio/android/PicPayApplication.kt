package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.PicPayModuleInitialization
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupTimber()
    }

    private fun setupKoin() =
        startKoin {
            androidLogger()
            androidContext(this@PicPayApplication)
            modules(
                PicPayModuleInitialization().init()
            )
        }

    private fun setupTimber() =
        Timber.plant(Timber.DebugTree())
}