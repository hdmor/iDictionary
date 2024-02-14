package com.i.dictionary.feature.domain.usecase

import com.i.dictionary.core.util.Resource
import com.i.dictionary.feature.domain.model.Word
import com.i.dictionary.feature.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWord(private val repository: WordRepository) {

    operator fun invoke(word: String): Flow<Resource<List<Word>>> {
        if (word.isBlank()) return flow { }
        return repository.translate(word)
    }
}