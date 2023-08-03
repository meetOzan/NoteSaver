package com.mertozan.notesaver.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertozan.notesaver.ui.screen.AddNote
import com.mertozan.notesaver.ui.screen.AllNoteScreen
import com.mertozan.notesaver.ui.screen.SplashScreen
import com.mertozan.notesaver.viewModel.NoteViewModel

@Composable
fun NoteNavHost(
    navController: NavHostController
    ) {
    NavHost(
        navController = navController, startDestination = Splash.route
    ) {
        splashScreenRoute(
            onNavigate = {
                navController.newNavigate(ShowNote.route)
            }
        )
        showNoteRoute(
            onFabClicked = {
                navController.newNavigate(AddNote.route)
            }
        )
        addNoteRoute(
            onNavigateNotes = {
                navController.newNavigate(ShowNote.route)
            }
        )
    }
}

fun NavGraphBuilder.splashScreenRoute(onNavigate: () -> Unit) {
    composable(Splash.route) {
        SplashScreen(
            onNavigate = onNavigate
        )
    }
}

fun NavGraphBuilder.showNoteRoute(onFabClicked: () -> Unit) {
    composable(ShowNote.route) {
        val viewModel = hiltViewModel<NoteViewModel>()
        val notes by viewModel.allNotes.collectAsState()

        AllNoteScreen(
            onFabClicked = onFabClicked,
            allNoteZ = notes,
            delete = {
                viewModel.deleteNote(it)
            }
        )
    }
}

fun NavGraphBuilder.addNoteRoute(onNavigateNotes: () -> Unit) {
    composable(AddNote.route) {
        val viewModel = hiltViewModel<NoteViewModel>()
        AddNote(
            onNavigateNotes = onNavigateNotes,
            viewModel = viewModel
        )
    }
}

fun NavHostController.newNavigate(route: String) =
    this.navigate(route) { launchSingleTop = true }