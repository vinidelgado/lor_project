package com.vini.lor.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.vini.lor.R
import com.vini.lor.presentation.LorAmericasViewModel
import com.vini.lor.presentation.components.LorTopAppBar

@Composable
fun LeaderboardsScreen(
    viewModel: LorAmericasViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        LeaderboardsTitle()
    }
}

@Composable
fun LeaderboardsTitle() {
    LorTopAppBar(
        titleRes = R.string.app_name,
        navigationIcon = Icons.Rounded.Search,
        navigationIconContentDescription = stringResource(
            id = R.string.app_name
        ),
        actionIcon = Icons.Rounded.Check,
        actionIconContentDescription = stringResource(
            id = R.string.app_name
        ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}


