package dev.ashish.disclosure.di.module

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.ashish.disclosure.data.api.ApiKeyInterceptor
import dev.ashish.disclosure.data.api.NetworkService
import dev.ashish.disclosure.data.local.AppDatabaseService
import dev.ashish.disclosure.data.local.DatabaseService
import dev.ashish.disclosure.data.local.DisclosureDatabase
import dev.ashish.disclosure.di.BaseUrl
import dev.ashish.disclosure.di.DatabaseName
import dev.ashish.disclosure.di.NetworkAPIKey
import dev.ashish.disclosure.utils.AppConstant
import dev.ashish.disclosure.utils.DefaultDispatcherProvider
import dev.ashish.disclosure.utils.DispatcherProvider
import dev.ashish.disclosure.utils.NetworkHelper
import dev.ashish.disclosure.utils.NetworkHelperImpl
import dev.ashish.disclosure.utils.logger.AppLogger
import dev.ashish.disclosure.utils.logger.Logger
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor):
            OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelperImpl(context)
    }

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(@NetworkAPIKey apiKey: String): ApiKeyInterceptor =
        ApiKeyInterceptor(apiKey)


    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideLogger(): Logger = AppLogger()

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = AppConstant.BASE_URL


    @NetworkAPIKey
    @Provides
    fun provideApiKey(): String = AppConstant.API_KEY

    @Provides
    @Singleton
    fun provideDatabaseService(appDatabase: DisclosureDatabase): DatabaseService {
        return AppDatabaseService(appDatabase)
    }

    @DatabaseName
    @Provides
    fun provideDatabaseName(): String = AppConstant.DATABASE_NAME

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String
    ): DisclosureDatabase {
        return Room.databaseBuilder(
            context,
            DisclosureDatabase::class.java,
            databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideWorkManager(
        @ApplicationContext context: Context
    ): WorkManager {
        return WorkManager.getInstance(context)
    }
}