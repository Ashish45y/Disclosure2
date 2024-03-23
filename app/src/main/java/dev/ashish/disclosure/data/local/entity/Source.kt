package dev.ashish.disclosure.data.local.entity

import androidx.room.ColumnInfo

data class Source(
    @ColumnInfo(name = "sourceId") val id: String?,
    @ColumnInfo(name = "sourceName") val name: String = ""
)
