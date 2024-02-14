package com.i.dictionary.feature.presentation

import com.i.dictionary.feature.domain.model.Word

data class WordState(
    val wordItems: List<Word> = emptyList(),
    val isLoading: Boolean = false
)