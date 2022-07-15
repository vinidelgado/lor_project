package com.vini.lor


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.vini.lor.presentation.components.LorBackground
import com.vini.lor.presentation.ui.LeaderboardsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        this.window.statusBarColor = ContextCompat.getColor(this,R.color.black30)
        setContent {
            LorBackground{
                LeaderboardsScreen()
            }
        }
    }
}