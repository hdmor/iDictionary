package com.i.dictionary.feature.domain.repository

import com.i.dictionary.core.util.Resource
import com.i.dictionary.feature.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    fun translate(word: String): Flow<Resource<List<Word>>>
}