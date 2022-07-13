package com.vini.lor.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.vini.lor.util.Constants.LOR_DATABASE_TABLE

@Entity(tableName = LOR_DATABASE_TABLE)
data class AmericasLeaderboardsDataDto(
    @PrimaryKey
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "rank")
    val rank: Int,
    @field:Json(name = "lp")
    val lp: Int,
)
