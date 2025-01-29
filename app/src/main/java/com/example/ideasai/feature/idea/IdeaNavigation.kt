package com.example.ideasai.feature.idea

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable
data object BaseRoute

@Serializable
data object IdeaRoute

fun NavController.navigateToIdea(navOptions: NavOptions? = null) = navigate(IdeaRoute, navOptions)

fun NavGraphBuilder.ideaScreen() {
    navigation<BaseRoute>(startDestination = IdeaRoute) {
        composable<IdeaRoute> {
            IdeaScreen()
        }
    }
}
