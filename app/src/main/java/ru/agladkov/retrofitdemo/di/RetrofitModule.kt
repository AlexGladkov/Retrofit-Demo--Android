package ru.agladkov.retrofitdemo.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.agladkov.retrofitdemo.R
import ru.agladkov.retrofitdemo.network.*
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun httpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        sharedPreferences: SharedPreferences
    ): OkHttpClient =
        OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                val request = chain.request().newBuilder()
//                    .addHeader(
//                        "Authorization",
//                        "Bearer ${sharedPreferences.getString("API_KEY", "").orEmpty()}"
//                    )
//                    .build()
//
//                return@addInterceptor chain.proceed(request)
//            }
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    @DotaRetrofitClient
    fun dotaRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.opendota.com/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @VkRetrofitClient
    fun vkRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.vk.com/method/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @QuestGoClient
    fun questGoRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideDotaApi(@DotaRetrofitClient retrofit: Retrofit): DotaApi =
        retrofit.create(DotaApi::class.java)

    @Provides
    @Singleton
    fun provideQuestGoApi(@QuestGoClient retrofit: Retrofit): QuestGoApi =
        retrofit.create(QuestGoApi::class.java)
}