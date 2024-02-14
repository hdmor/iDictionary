package com.i.dictionary.feature.data.remote.dto

import com.i.dictionary.feature.data.local.entity.WordEntity
import com.i.dictionary.feature.domain.model.Word

data class WordDto(
    val license: LicenseDto,
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
) {
    fun toWord(): Word =
        Word(license = license.toLicense(), meanings = meanings.map { it.toMeaning() }, phonetic = phonetic, sourceUrls = sourceUrls, word = word)

    fun toWordEntity(): WordEntity =
        WordEntity(
            license = license.toLicense(),
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            sourceUrls = sourceUrls,
            word = word
        )
}