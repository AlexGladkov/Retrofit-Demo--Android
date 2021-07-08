package ru.agladkov.retrofitdemo.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DotaRetrofitClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class VkRetrofitClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class QuestGoClient