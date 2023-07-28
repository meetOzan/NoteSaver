package com.mertozan.notesaver.navigation

interface Destinations {
    val route : String
}

object Splash : Destinations{
    override val route: String
        get() = "splash"
}

object ShowNote : Destinations {
    override val route = "show_note"
}

object AddNote : Destinations {
    override val route = "add_note"
}