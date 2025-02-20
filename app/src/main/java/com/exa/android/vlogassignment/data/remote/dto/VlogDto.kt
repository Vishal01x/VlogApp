package com.exa.android.vlogassignment.data.remote.dto

data class VlogDto(
    val author: Int,
    val date: String,
    val id: Int,
    val jetpack_featured_media_url: String?,
    val link: String?,
    val title: Title,
    val type: String
)