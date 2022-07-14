package com.vini.lor.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vini.lor.R
import com.vini.lor.data.remote.AmericasLeaderboardsDataDto
import com.vini.lor.presentation.LorLeaderboardsViewModel
import com.vini.lor.presentation.components.LorTopAppBar
import com.vini.lor.ui.theme.typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalLayoutApi
@Composable
fun LeaderboardsScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            LeaderboardsTitle()
        },
        containerColor = Color.Transparent
    ){ innerPadding ->
        Text("Teste",modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding))
        BoxWithConstraints(
            modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
                LeaderboardsContent()
            }
        }
    }
}

@Composable
fun LeaderboardsTitle() {
    LorTopAppBar(
        titleRes = R.string.app_name,
        navigationIcon = Icons.Default.Search,
        navigationIconContentDescription = stringResource(
            id = R.string.app_name
        ),
        actionIcon = Icons.Default.AccountCircle,
        actionIconContentDescription = stringResource(
            id = R.string.app_name
        ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
        )
    )
}

@Composable
fun LeaderboardsContent(viewModel: LorLeaderboardsViewModel = hiltViewModel()) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    viewModel.loadAmericasLeaderboardStream()
    Column(modifier = Modifier.background(Color(0xFF141136))) {

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardPlayersOthers(player: AmericasLeaderboardsDataDto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 0.dp),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2E2651)
        )
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




