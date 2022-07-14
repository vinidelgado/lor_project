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
class LorLeaderboardsViewModel @Inject constructor(
    private val repository: LorRepository,
) : ViewModel() {

    var state by mutableStateOf(LorAmericasLeaderboards())
        private set

    fun loadAmericasLeaderboardStream() {
        viewModelScope.launch {
            repository.fakeLeaderboardsStream().collect{ result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let { data ->
                            state = state.copy(
                                playersData = data,
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }


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


}

data class LorAmericasLeaderboards(
    val playersData: List<AmericasLeaderboardsDataDto> = ArrayList(),
    val selected: Int = -1,
    val isLoading: Boolean = false,
    val error: String? = null
)