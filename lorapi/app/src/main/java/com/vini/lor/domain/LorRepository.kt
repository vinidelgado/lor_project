package com.vini.lor.domain

import com.vini.lor.data.remote.AmericasLeaderboardsDataDto
import com.vini.lor.data.remote.AmericasLeaderboardsDto
import com.vini.lor.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface LorRepository {
    suspend fun getLeaderboards(): Resource<AmericasLeaderboardsDto>
    suspend fun leaderboardsStream(): Flow<Resource<List<AmericasLeaderboardsDataDto>>>
    suspend fun fakeLeaderboardsStream(): Flow<Resource<List<AmericasLeaderboardsDataDto>>>
}