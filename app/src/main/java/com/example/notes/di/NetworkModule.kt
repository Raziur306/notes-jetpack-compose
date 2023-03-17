package com.example.notes.di

import com.example.notes.common.Constants
import com.example.notes.data.remote.Api
import com.example.notes.data.repository.AuthRepositoryImp
import com.example.notes.data.repository.NotesRepositoryImp
import com.example.notes.domain.repository.AuthRepository
import com.example.notes.domain.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNotesApi(): Api {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: Api): AuthRepository {
        return AuthRepositoryImp(api)
    }

    @Provides
    @Singleton
    fun provideNotesRepository(api: Api): NotesRepository {
        return NotesRepositoryImp(api)
    }
}