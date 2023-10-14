package com.github.cwramirezg.misrecetas.home.di

import android.content.Context
import androidx.room.Room
import com.github.cwramirezg.misrecetas.home.data.local.HomeDao
import com.github.cwramirezg.misrecetas.home.data.local.HomeDatabase
import com.github.cwramirezg.misrecetas.home.data.remote.HomeApi
import com.github.cwramirezg.misrecetas.home.data.repository.HomeRepositoryImpl
import com.github.cwramirezg.misrecetas.home.domain.detail.usecase.DetailUseCases
import com.github.cwramirezg.misrecetas.home.domain.detail.usecase.GetRecetaByIdUseCase
import com.github.cwramirezg.misrecetas.home.domain.home.usecase.GetRecetasUseCase
import com.github.cwramirezg.misrecetas.home.domain.home.usecase.HomeUseCases
import com.github.cwramirezg.misrecetas.home.domain.home.usecase.RequestRecetas
import com.github.cwramirezg.misrecetas.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun provideHomeUseCases(repository: HomeRepository): HomeUseCases {
        return HomeUseCases(
            getRecetas = GetRecetasUseCase(repository = repository),
            requestRecetas = RequestRecetas(repository = repository)
        )
    }

    @Singleton
    @Provides
    fun provideDetailUseCases(repository: HomeRepository): DetailUseCases {
        return DetailUseCases(
            getRecetaByIdUseCase = GetRecetaByIdUseCase(repository = repository)
        )
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
    }

    @Singleton
    @Provides
    fun provideHomeApi(cliente: OkHttpClient): HomeApi {
        return Retrofit
            .Builder()
            .baseUrl(HomeApi.BASE_URL)
            .client(cliente)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(HomeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeDao(@ApplicationContext context: Context): HomeDao {
        return Room.databaseBuilder(
            context,
            HomeDatabase::class.java,
            "misrecetas.db",
        ).build().dao
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        dao: HomeDao,
        api: HomeApi
    ): HomeRepository {
        return HomeRepositoryImpl(dao = dao, api = api)
    }
}