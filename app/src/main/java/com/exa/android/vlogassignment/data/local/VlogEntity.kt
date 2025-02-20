package com.exa.android.vlogassignment.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(value = ["appId"], unique = true)] // Ensures uniqueness
)
data class VlogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val appId : Int,
    val author: Int,
    val date: String,
    val imageUrl: String?,
    val vlogLink: String?,
    val title: String,
    val type: String
)
