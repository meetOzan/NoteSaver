package com.mertozan.notesaver.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertozan.notesaver.ui.screen.AddNote
import com.mertozan.notesaver.ui.screen.AllNoteScreen
import com.mertozan.notesaver.ui.screen.SplashScreen

@Composable
fun NoteNavHost(navController: NavHostController) {

    NavHost(
        navController = navController, startDestination = Splash.route
    ) {
        composable(Splash.route) {
            SplashScreen(onNavigate = { navController.newNavigate(ShowNote.route) })
        }
        composable(ShowNote.route) {
            AllNoteScreen(onFabClicked = {
                navController.newNavigate(AddNote.route)
            })
        }
        composable(route = AddNote.route) {
            AddNote(onNavigateNotes = {
                navController.newNavigate(ShowNote.route)
            })
        }
    }
}

fun NavHostController.newNavigate(route: String) =
    this.navigate(route) { launchSingleTop = true }