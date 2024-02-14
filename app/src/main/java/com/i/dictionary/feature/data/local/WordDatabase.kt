package com.i.dictionary.feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.i.dictionary.feature.data.local.entity.WordEntity

@Database(entities = [WordEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class WordDatabase : RoomDatabase() {

    abstract val dao: WordDao
}