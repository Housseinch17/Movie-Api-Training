package com.example.movieapiapptraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.movieapiapptraining.ui.MovieApiAppTrainingTheme
import com.example.movieapiapptraining.ui.navigation.Navigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieApiAppTrainingTheme {
                val navController = rememberNavController()
                Scaffold { innerPadding->
                    Navigation(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding()
                            .navigationBarsPadding(),
                        navController = navController
                    )
                }
            }
        }
    }
}
