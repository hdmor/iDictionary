package com.i.dictionary.feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.i.dictionary.feature.domain.model.License
import com.i.dictionary.feature.domain.model.Meaning
import com.i.dictionary.feature.domain.model.Word

@Entity(tableName = "words_entity")
data class WordEntity(
    @PrimaryKey val id: Int? = null,
    val license: License,
    val meanings: List<Meaning>,
    val phonetic: String?,
    val sourceUrls: List<String>,
    val word: String
) {
    fun toWord(): Word =
        Word(license = license, meanings = meanings, phonetic = phonetic.toString(), sourceUrls = sourceUrls, word = word)
}
