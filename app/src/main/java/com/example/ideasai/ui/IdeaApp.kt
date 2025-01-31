package com.example.ideasai.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.ideasai.core.testing.data.TestTag
import com.example.ideasai.navigation.IdeaNavHost

@Composable
fun IdeaApp(
    appState: IdeaAppState = rememberIdeaAppState()
) {
    val currentDestination = appState.currentDestination
    Scaffold(
        modifier = Modifier.testTag(TestTag.IDEA_APP),
        bottomBar = {
            NavigationBar {
                appState.topLevelDestinations.forEach { destination ->
                    val selected = appState.isRouteInHierarchies(
                        currentDestination,
                        destination.baseRoute
                    )
                    NavigationBarItem(
                        selected = selected,
                        icon = {
                            Icon(
                                imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                                contentDescription = "navigation item"
                            )
                        },
                        label = {
                            Text(stringResource(destination.labelId))
                        },
                        onClick = {
                            appState.navigateToTopLevelDestination(destination)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        IdeaNavHost(
            navController = appState.navController,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
    }
}