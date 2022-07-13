package com.vini.lor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vini.lor.data.remote.AmericasLeaderboardsDataDto
import com.vini.lor.presentation.LorAmericasViewModel
import com.vini.lor.ui.theme.LorTheme
import com.vini.lor.ui.theme.typography
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: LorAmericasViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.loadMockLeaderboard()
//        viewModel.filterName("NJ")
        viewModel.loadAmericasLeaderboard()
        setContent {
            LorTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color(0xFF171138)
                ) {
                    val listState = rememberLazyListState()
                    val coroutineScope = rememberCoroutineScope()
                    Column() {
                        Button(onClick = { viewModel.filterName("Ferocius") }) {
                            Text(text = "Click")
                        }

                        if (viewModel.state.isLoading) {
                            CircularProgressIndicator(
                            )
                        }

                        viewModel.state.playersData.let { playerList ->
                            LazyColumn(
                                state = listState,
                                contentPadding = PaddingValues(all = 4.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(playerList.size) { index ->
                                    LeaderboardPlayersOthers(playerList[index])
                                }
                            }
                        }

                        viewModel.state.selected.let {
                            if (it != -1) {
                                coroutineScope.launch {
                                    delay(3000)
                                    listState.animateScrollToItem(index = it)
                                }
                            }
                        }

                        viewModel.state.error?.let { error ->
                            Text(
                                text = error,
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LeaderboardPlayersOthers(player: AmericasLeaderboardsDataDto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 0.dp),
        shape = RoundedCornerShape(6.dp),
        backgroundColor = Color(0xFF2E2651)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = player.rank.toString(),
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 12.dp),
                color = Color(0xFFAAABCA),
                fontWeight = FontWeight.Bold,
                style = typography.body1,
                textAlign = TextAlign.Center,
            )
            Text(
                player.name,
                modifier = Modifier
                    .weight(3f),
                color = Color(0xFFFFFFFF),
                fontWeight = FontWeight.Medium,
                style = typography.body2,
                textAlign = TextAlign.Start,
            )
            Text(
                player.lp.toString(),
                modifier = Modifier
                    .weight(1f),
                color = Color(0xFF8890B5),
                fontWeight = FontWeight.Bold,
                style = typography.body1,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LorTheme {
        val player = AmericasLeaderboardsDataDto(name = "Vini", rank = 1, 200)
        LeaderboardPlayersOthers(player)
    }
}