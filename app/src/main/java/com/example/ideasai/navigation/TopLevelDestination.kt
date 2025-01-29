package com.example.ideasai.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ideasai.R
import com.example.ideasai.feature.favorite.FavoriteRoute
import com.example.ideasai.feature.idea.BaseRoute
import com.example.ideasai.feature.idea.IdeaRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val labelId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route
) {
    IDEA(
        selectedIcon = Icons.Rounded.Lightbulb,
        unselectedIcon = Icons.Outlined.Lightbulb,
        labelId = R.string.idea_screen_label,
        route = IdeaRoute::class,
        baseRoute = BaseRoute::class
    ),
    FAVORITE(
        selectedIcon = Icons.Rounded.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        labelId = R.string.favorite_screen_label,
        route = FavoriteRoute::class
    )
}