package com.i.dictionary.feature.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.i.dictionary.feature.data.util.JsonParser
import com.i.dictionary.feature.domain.model.License
import com.i.dictionary.feature.domain.model.Meaning

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {

    @TypeConverter
    fun fromLicenseJson(json: String): License =
        jsonParser.fromJson<License>(json, object : TypeToken<License>() {}.type) ?: License("", "")

    @TypeConverter
    fun toLicenseJson(license: License): String =
        jsonParser.toJson(license, object : TypeToken<License>() {}.type) ?: "[]"

    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> =
        jsonParser.fromJson<ArrayList<Meaning>>(json, object : TypeToken<ArrayList<Meaning>>() {}.type) ?: emptyList()

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String = jsonParser.toJson(meanings, object : TypeToken<ArrayList<Meaning>>() {}.type) ?: "[]"

    @TypeConverter
    fun fromSourceUrlsJson(json: String): List<String> =
        jsonParser.fromJson<ArrayList<String>>(json, object : TypeToken<ArrayList<String>>() {}.type) ?: emptyList()

    @TypeConverter
    fun toSourceUrlsJson(sourceUrls: List<String>): String = jsonParser.toJson(sourceUrls, object : TypeToken<ArrayList<String>>() {}.type) ?: "[]"

}