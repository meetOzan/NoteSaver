package com.mertozan.notesaver.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertozan.notesaver.ui.theme.screens.AddNote
import com.mertozan.notesaver.ui.theme.screens.AllNoteScreen
import com.mertozan.notesaver.viewModel.NoteViewModel

@Composable
fun NoteNavHost(navController: NavHostController) {

    val viewModel = hiltViewModel<NoteViewModel>()

    NavHost(
        navController = navController, startDestination = ShowNote.route
    ) {
        composable(ShowNote.route) {
            AllNoteScreen(onFabClicked = {
                navController.newNavigate(AddNote.route)
            })
        }

        composable(
            route = AddNote.route
        ) {
            AddNote(
                onNavigateNotes = {
                    navController.newNavigate(ShowNote.route)
                })
        }
    }
}

fun NavHostController.newNavigate(route: String) =
    this.navigate(route) { launchSingleTop = true }