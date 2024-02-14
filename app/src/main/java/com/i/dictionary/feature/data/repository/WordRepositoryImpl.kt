package com.i.dictionary.feature.data.repository

import com.i.dictionary.core.util.Resource
import com.i.dictionary.feature.data.local.WordDao
import com.i.dictionary.feature.data.remote.ApiService
import com.i.dictionary.feature.domain.model.Word
import com.i.dictionary.feature.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordRepositoryImpl(private val apiService: ApiService, private val wordDao: WordDao) : WordRepository {

    override fun translate(word: String): Flow<Resource<List<Word>>> = flow {

        emit(Resource.Loading())

        val words = wordDao.get(word).map { it.toWord() }
        emit(Resource.Loading(words))

        try {
            val remoteWord = apiService.translate(word)
            wordDao.deleteAll(remoteWord.map { it.word })
            wordDao.insertAll(remoteWord.map { it.toWordEntity() })
        } catch (exception: HttpException) {
            emit(Resource.Error(message = "Oops, something went wrong!", data = words))
        } catch (exception: IOException) {
            emit(Resource.Error(message = "Couldn't reach server, check your internet connection.", data = words))

        }

        val newWord = wordDao.get(word).map { it.toWord() }
        emit(Resource.Success(newWord))
    }
}