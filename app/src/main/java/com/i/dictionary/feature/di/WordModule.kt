package com.i.dictionary.feature.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.i.dictionary.feature.data.local.Converters
import com.i.dictionary.feature.data.local.WordDao
import com.i.dictionary.feature.data.local.WordDatabase
import com.i.dictionary.feature.data.local.WordDatabase_Impl
import com.i.dictionary.feature.data.remote.ApiService
import com.i.dictionary.feature.data.repository.WordRepositoryImpl
import com.i.dictionary.feature.data.util.GsonParser
import com.i.dictionary.feature.domain.repository.WordRepository
import com.i.dictionary.feature.domain.usecase.GetWord
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordModule {

    @Provides
    @Singleton
    fun provideGetWordUseCase(repository: WordRepository): GetWord = GetWord(repository)

    @Provides
    @Singleton
    fun provideWordRepository(apiService: ApiService, wordDatabase: WordDatabase): WordRepository =
        WordRepositoryImpl(apiService, wordDatabase.dao)

    @Provides
    @Singleton
    fun provideWordDatabase(application: Application): WordDatabase =
        Room.databaseBuilder(application, WordDatabase::class.java, "words_application_db")
            .addTypeConverter(Converters(GsonParser(Gson()))).build()

    @Provides
    @Singleton
    fun provideApiService(): ApiService =
        Retrofit.Builder().baseUrl(ApiService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)
}