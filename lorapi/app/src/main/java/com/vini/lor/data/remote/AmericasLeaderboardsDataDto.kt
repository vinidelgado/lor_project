package com.vini.lor.data.remote

import com.squareup.moshi.Json

data class AmericasLeaderboardsDataDto(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "rank")
    val rank: Int,
    @field:Json(name = "lp")
    val lp: Int,
)
