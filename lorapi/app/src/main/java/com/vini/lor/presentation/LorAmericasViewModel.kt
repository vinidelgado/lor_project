package com.vini.lor.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.lor.data.remote.AmericasLeaderboardsDataDto
import com.vini.lor.domain.LorRepository
import com.vini.lor.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LorAmericasViewModel @Inject constructor(
    private val repository: LorRepository,
) : ViewModel() {
    var state by mutableStateOf(LorAmericasLeaderboards())
        private set

    fun loadAmericasLeaderboard() {
        viewModelScope.launch {
            state = state.copy(
                playersData = ArrayList(),
                isLoading = false,
                error = null
            )
            when (val result = repository.getLeaderboards()) {
                is Resource.Success -> {
                    state = state.copy(
                        playersData = result.data?.playersData?.take(1335) ?: ArrayList(),
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        playersData = ArrayList(),
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    fun filterName(name: String) {
        try {
            var filtredPlayer = state.playersData.filter { player ->
                player.name == name
            }.single()
            state = state.copy(selected = filtredPlayer.rank)
        } catch (exception: NoSuchElementException) {
            exception.printStackTrace()
        } catch (exception: IllegalArgumentException) {
            exception.printStackTrace()
        }

    }

    fun loadMockLeaderboard() {
        val listPlayers = listOf(
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
        state = state.copy(
            playersData = listPlayers,
            isLoading = false,
            error = null
        )
    }

}

data class LorAmericasLeaderboards(
    val playersData: List<AmericasLeaderboardsDataDto> = ArrayList(),
    val selected:Int = -1,
    val isLoading: Boolean = false,
    val error: String? = null
)