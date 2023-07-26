package com.mertozan.notesaver.navigation

interface Destinations {
    val route : String
}

object ShowNote : Destinations {
    override val route = "show_note"
}

object AddNote : Destinations {
    override val route = "add_note"
}