package com.i.dictionary.feature.data.remote.dto

import com.i.dictionary.feature.domain.model.License

data class LicenseDto(
    val name: String,
    val url: String
) {
    fun toLicense(): License = License(name = name, url = url)
}