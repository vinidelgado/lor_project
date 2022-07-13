package com.vini.lor.data.remote

import retrofit2.http.GET

interface LorRiotAmericasApi {

    @GET("lor/ranked/v1/leaderboards")
    suspend fun getAmericasLeaderboards(): AmericasLeaderboardsDto
}