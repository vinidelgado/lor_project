package com.vini.lor.data.remote

import com.squareup.moshi.Json

data class AmericasLeaderboardsDto(
    @field:Json(name = "players")
    val playersData: List<AmericasLeaderboardsDataDto>
)