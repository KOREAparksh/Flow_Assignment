package com.flow.assignment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(
    @PrimaryKey val query: String,
    @ColumnInfo(name = "create_at") val createAt: Long
)
