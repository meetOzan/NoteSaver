package com.mertozan.notesaver.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertozan.notesaver.ui.theme.screens.AddNote
import com.mertozan.notesaver.ui.theme.screens.AllNoteScreen

/*@Composable
fun NoteNavHost(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = ShowNote.route){
        composable(ShowNote.route){
            AllNoteScreen(onFabClicked = {
                navHostController.newNavigate(AddNote.route)
            })
        }
        composable(AddNote.route){
            com.mertozan.notesaver.ui.theme.screens.AddNote()
        }
    }
}*/

@Composable
fun NoteNavHost(navController: NavHostController) {
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
            )
        }
    }
}

fun NavHostController.newNavigate(route: String) =
    this.navigate(route) { launchSingleTop = true }