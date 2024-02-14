package com.i.dictionary.feature.data.remote.dto

import com.i.dictionary.feature.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
) {
    fun toDefinition(): Definition =
        Definition(antonyms = antonyms, definition = definition, example = example, synonyms = synonyms)
}