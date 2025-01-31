package com.example.ideasai.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.ideasai.feature.favorite.navigateToFavorite
import com.example.ideasai.feature.idea.navigateToIdea
import com.example.ideasai.navigation.TopLevelDestination
import kotlin.reflect.KClass

@Composable
fun rememberIdeaAppState(
    navController: NavHostController = rememberNavController()
): IdeaAppState {
    return remember(navController) {
        IdeaAppState(navController)
    }
}

@Stable
class IdeaAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(destination: TopLevelDestination) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            restoreState = true
            launchSingleTop = true
        }

        when (destination) {
            TopLevelDestination.IDEA -> navController.navigateToIdea(navOptions)
            TopLevelDestination.FAVORITE -> navController.navigateToFavorite(navOptions)
        }
    }

    fun isRouteInHierarchies(
        destination: NavDestination?,
        route: KClass<*>
    ): Boolean =
        destination?.hierarchy?.any {
            it.hasRoute(route)
        } ?: false
}