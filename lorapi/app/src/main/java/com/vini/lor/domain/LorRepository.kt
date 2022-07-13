package com.vini.lor.domain

import com.vini.lor.data.remote.AmericasLeaderboardsDto
import com.vini.lor.domain.util.Resource

interface LorRepository {
    suspend fun getLeaderboards(): Resource<AmericasLeaderboardsDto>
}