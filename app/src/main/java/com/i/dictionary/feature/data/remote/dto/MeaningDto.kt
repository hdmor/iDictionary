package com.i.dictionary.feature.data.remote.dto

import com.i.dictionary.feature.domain.model.Meaning

data class MeaningDto(
    val antonyms: List<Any>,
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
    val synonyms: List<String>
) {
    fun toMeaning(): Meaning =
        Meaning(antonyms = antonyms, definitions = definitions.map { it.toDefinition() }, partOfSpeech = partOfSpeech, synonyms = synonyms)
}