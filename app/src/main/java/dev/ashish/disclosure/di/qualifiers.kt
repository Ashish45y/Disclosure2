package dev.ashish.disclosure.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ActivityContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LanguageListQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsSourceListQualifier
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CountryListQualifier

