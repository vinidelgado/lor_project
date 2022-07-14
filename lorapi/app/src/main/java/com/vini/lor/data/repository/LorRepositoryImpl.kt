package com.vini.lor.data.repository

import android.util.Log
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
            emit(Resource.Success(generatePlayers()))
        }
    }

    private fun generatePlayers():List<AmericasLeaderboardsDataDto> {
        return listOf(
                AmericasLeaderboardsDataDto(name = "squallywag", rank = 1, 1200),
                AmericasLeaderboardsDataDto(name = "MajiinBae", rank = 2, 1100),
                AmericasLeaderboardsDataDto(name = "LuserBeam", rank = 3, 1000),
                AmericasLeaderboardsDataDto(name = "420 DN Blaze It", rank = 4, 900),
                AmericasLeaderboardsDataDto(name = "LiP", rank = 5, 800),
                AmericasLeaderboardsDataDto(name = "WW Minasia", rank = 6, 600),
                AmericasLeaderboardsDataDto(name = "Trivo", rank = 7, 600),
                AmericasLeaderboardsDataDto(name = "HDR Dudu de Nunu", rank = 8, 600),
                AmericasLeaderboardsDataDto(name = "ptash", rank = 9, 600),
                AmericasLeaderboardsDataDto(name = "FloppyMudkip", rank = 10, 600),
                AmericasLeaderboardsDataDto(name = "HDR Lazyguga", rank = 11, 600),
                AmericasLeaderboardsDataDto(name = "Prodigy", rank = 12, 600),
                AmericasLeaderboardsDataDto(name = "WW Seku", rank = 13, 600),
                AmericasLeaderboardsDataDto(name = "AK KuroNE", rank = 14, 600),
                AmericasLeaderboardsDataDto(name = "AK Tomaszamo", rank = 15, 600),
                AmericasLeaderboardsDataDto(name = "NJay", rank = 16, 600),
                AmericasLeaderboardsDataDto(name = "WW Jones", rank = 17, 600),
                AmericasLeaderboardsDataDto(name = "AK KuroNElson", rank = 18, 600),
                AmericasLeaderboardsDataDto(name = "AK Tomaszamo Nelson", rank = 19, 600),
                AmericasLeaderboardsDataDto(name = "NJ", rank = 20, 600),
            )
    }

}