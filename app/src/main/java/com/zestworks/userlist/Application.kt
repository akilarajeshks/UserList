package com.zestworks.userlist

import android.app.Application
import android.content.Context
import com.zestworks.userlist.storage.database.UsersDAO
import com.zestworks.userlist.storage.database.UsersDatabase
import com.zestworks.userlist.storage.network.NetworkService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        val modules = module {
            single { provideNetworkService() }
            single { provideDAO(get()) }
        }
        startKoin {
            androidContext(this@Application)
            modules(modules)
        }
    }

    private fun provideDAO(context: Context): UsersDAO {
        return UsersDatabase.getDatabase(context).usersDAO()
    }

    private fun provideNetworkService(): NetworkService {
        val baseURL = " https://n161.tech/api/dummyapi/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(NetworkService::class.java)
    }
}