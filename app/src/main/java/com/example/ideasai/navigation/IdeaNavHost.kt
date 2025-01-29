package com.example.ideasai.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ideasai.feature.favorite.favoriteScreen
import com.example.ideasai.feature.idea.BaseRoute
import com.example.ideasai.feature.idea.ideaScreen

@Composable
fun IdeaNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BaseRoute,
        modifier = modifier
    ) {
        ideaScreen()
        favoriteScreen()
    }
}