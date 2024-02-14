package com.i.dictionary.feature.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.i.dictionary.feature.data.local.entity.WordEntity

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(words: List<WordEntity>)

    @Query("DELETE FROM words_entity WHERE word IN (:words)")
    suspend fun deleteAll(words: List<String>)

    @Query("SELECT * FROM words_entity WHERE word LIKE '%' || :word || '%'")
    suspend fun get(word: String): List<WordEntity>
}