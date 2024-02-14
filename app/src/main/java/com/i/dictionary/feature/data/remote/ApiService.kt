package com.i.dictionary.feature.data.remote

import com.i.dictionary.feature.data.remote.dto.WordDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/v2/entries/en/{word}")
    suspend fun translate(@Path("word") world: String): List<WordDto>

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/"
    }
}