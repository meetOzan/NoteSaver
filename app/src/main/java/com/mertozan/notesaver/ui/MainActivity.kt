package com.mertozan.notesaver.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.mertozan.notesaver.navigation.NoteNavHost
import com.mertozan.notesaver.ui.theme.NoteSaverTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteSaverTheme {

                val navController = rememberNavController()

                NoteNavHost(navController = navController)

            }
        }
    }
}
