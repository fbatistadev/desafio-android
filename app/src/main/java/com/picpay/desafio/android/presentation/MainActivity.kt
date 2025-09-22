package com.picpay.desafio.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.picpay.desafio.android.presentation.navigation.PicPayNavHost
import com.picpay.desafio.android.presentation.theme.PicPayTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,  false)

        setContent {
            PicPayTheme {
                KoinAndroidContext {
                    PicPayNavHost()
                }
            }
        }
    }
}