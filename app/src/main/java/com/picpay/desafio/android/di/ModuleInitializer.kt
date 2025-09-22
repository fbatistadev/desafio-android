package com.picpay.desafio.android.di

import android.util.Log
import org.koin.core.module.Module

object ModuleInitializer {
    private val modules = mutableListOf<Module>()

    fun add(moduleList: List<Module>) {
        Log.d("ModuleInitializer", "Adding ${moduleList.size} modules")
        modules.addAll(moduleList)
        Log.d("ModuleInitializer", "Total modules now: ${modules.size}")
    }

    fun getAllModules(): List<Module> {
        Log.d("ModuleInitializer", "Returning ${modules.size} modules")
        return modules.toList()
    }
}