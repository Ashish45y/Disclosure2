package dev.ashish.disclosure.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkAPIKey

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DatabaseName
