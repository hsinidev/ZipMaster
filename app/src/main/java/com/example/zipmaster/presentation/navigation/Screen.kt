package com.example.zipmaster.presentation.navigation

sealed class Screen(val route: String, val label: String) {
    object Dashboard : Screen("dashboard", "Dashboard")
    object ArchiveEditor : Screen("archive_editor", "Toolbox")
    object Settings : Screen("settings", "Settings")
}
