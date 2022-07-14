package com.vini.lor.data.repository

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.vini.lor.data.remote.AmericasLeaderboardsDataDto
import com.vini.lor.data.remote.AmericasLeaderboardsDto
import com.vini.lor.data.remote.LorRiotAmericasApi
import com.vini.lor.domain.LorRepository
import com.vini.lor.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class LorRepositoryImpl @Inject constructor(
    private val api: LorRiotAmericasApi
) : LorRepository {

    override suspend fun getLeaderboards(): Resource<AmericasLeaderboardsDto> {
        return try {
            Log.d("LorRepositoryImpl", "Call Leaderboards")
            Resource.Success(
                data = api.getAmericasLeaderboards()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("LorRepositoryImpl", "Error: $e")
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun leaderboardsStream(): Flow<Resource<List<AmericasLeaderboardsDataDto>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val data = api.getAmericasLeaderboards()
                if (data.playersData.isNotEmpty()) {
                    emit(Resource.Success(data.playersData))
                } else {
                    emit(Resource.Error("Leaderboards error"))
                }
            } catch (exception: HttpException) {
                emit(Resource.Error("Leaderboards error: HttpException: ${exception.code()}"))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun fakeLeaderboardsStream(): Flow<Resource<List<AmericasLeaderboardsDataDto>>> {
        return flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(generatePlayers(80)))
        }
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private fun generatePlayers(times: Int = 1): List<AmericasLeaderboardsDataDto> {
        val list = ArrayList<AmericasLeaderboardsDataDto>()
        repeat(times) {
            when (it) {
                2 -> {
                    list.add(AmericasLeaderboardsDataDto(name = "MajiinBae", rank = it, 980))
                }
                7 -> {
                    list.add(AmericasLeaderboardsDataDto(name = "Trivo", rank = it, 930))
                }
                9 -> {
                    list.add(AmericasLeaderboardsDataDto(name = "Mafraju", rank = it, 910))
                }
                60 -> {
                    list.add(AmericasLeaderboardsDataDto(name = "Njay", rank = it, 405))
                }
                else -> {
                    list.add(
                        AmericasLeaderboardsDataDto(
                            name = getRandomString(8),
                            rank = it,
                            (1000 - (10 * it))
                        )
                    )
                }
            }
        }
        return list
    }

}