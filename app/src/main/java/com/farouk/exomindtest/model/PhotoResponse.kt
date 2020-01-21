package com.farouk.exomindtest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoResponse(
    @PrimaryKey(autoGenerate= false)
    val id: Int,
    val albumId: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)