package fr.ydelerm.bankintest.api

import dagger.Module
import dagger.Provides
import fr.ydelerm.bankintest.repository.NetworkDataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val BASE_URL =
            "https://raw.githubusercontent.com/bankin-engineering/challenge-android/master/"
    }

    @Singleton
    @Provides
    fun networkDataSource(bankinApi: BankinApi): NetworkDataSource {
        return NetworkDataSourceImpl(bankinApi)
    }

    @Singleton
    @Provides
    fun bankinApi(): BankinApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(BankinApi::class.java)
    }
}