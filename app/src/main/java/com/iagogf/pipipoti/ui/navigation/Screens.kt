package com.iagogf.pipipoti.ui.navigation

sealed class PipiPotiScreen(val route: String) {
    object Main : PipiPotiScreen("main")
    object Bath : PipiPotiScreen("bath")
    object Evento : PipiPotiScreen("evento")
    object Resumen : PipiPotiScreen("resumen")
}
