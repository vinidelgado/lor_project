package com.vini.lor.data.repository

import android.util.Log
import com.vini.lor.data.remote.AmericasLeaderboardsDto
import com.vini.lor.data.remote.LorRiotAmericasApi
import com.vini.lor.domain.LorRepository
import com.vini.lor.domain.util.Resource
import javax.inject.Inject

class LorRepositoryImpl @Inject constructor(
    private val api: LorRiotAmericasApi
): LorRepository {

    override suspend fun getLeaderboards(): Resource<AmericasLeaderboardsDto> {
        return try {
            Log.d("LorRepositoryImpl","Call Leaderboards")
            Resource.Success(
                data = api.getAmericasLeaderboards()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Log.d("LorRepositoryImpl","Error: $e")
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

}