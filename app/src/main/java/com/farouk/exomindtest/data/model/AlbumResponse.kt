package com.farouk.exomindtest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlbumResponse(
    @PrimaryKey(autoGenerate= false)
    val id: Int,
    val title: String,
    val userId: Int
)