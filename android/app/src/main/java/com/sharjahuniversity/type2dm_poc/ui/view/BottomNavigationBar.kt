package com.sharjahuniversity.type2dm_poc.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.utils.NavigationItem

var navAddButtonBrush by mutableStateOf(DefaultBlueBrush)
var hasNewSuggestions by mutableStateOf(false)
var hasNewSurvey by mutableStateOf(false)

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Projects,
        NavigationItem.Suggestions,
        NavigationItem.Profile
    )
    BottomNavigation(
        backgroundColor = White,
        contentColor = NavigationIconColorDefault,
        modifier = Modifier.height(70.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            when (item) {
                NavigationItem.Navigation -> {
                    BottomNavigationItem(
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .shadow(
                                        elevation = 4.dp,
                                        shape = CircleShape
                                    )
                                    .background(navAddButtonBrush, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painterResource(id = item.icon),
                                    contentDescription = stringResource(id = item.titleId),
                                    tint = White,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        },
                        selectedContentColor = NavigationIconColorSelected,
                        unselectedContentColor = NavigationIconColorDefault,
                        selected = currentRoute == item.route,
                        onClick = {
                            onClickItem(navController, item)
                        }
                    )
                }
                NavigationItem.Suggestions -> {
                    BottomNavigationItem(
                        icon = {
                            Box(
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Icon(
                                    painterResource(id = item.icon),
                                    contentDescription = stringResource(id = item.titleId)
                                )
                                if (hasNewSuggestions) Icon(
                                    painterResource(id = R.drawable.ic_red_dot),
                                    contentDescription = stringResource(id = item.titleId),
                                    tint = RedDotColor,
                                    modifier = Modifier
                                        .size(8.dp)
                                )
                            }
                        },
                        selectedContentColor = NavigationIconColorSelected,
                        unselectedContentColor = NavigationIconColorDefault,
                        selected = currentRoute == item.route,
                        onClick = {
                            onClickItem(navController, item)
                        }
                    )
                }
                NavigationItem.Projects -> {
                    BottomNavigationItem(
                        icon = {
                            Box(
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Icon(
                                    painterResource(id = item.icon),
                                    contentDescription = stringResource(id = item.titleId)
                                )
                                if (hasNewSurvey) Icon(
                                    painterResource(id = R.drawable.ic_red_dot),
                                    contentDescription = stringResource(id = item.titleId),
                                    tint = RedDotColor,
                                    modifier = Modifier
                                        .size(8.dp)
                                )
                            }
                        },
                        selectedContentColor = NavigationIconColorSelected,
                        unselectedContentColor = NavigationIconColorDefault,
                        selected = currentRoute == item.route,
                        onClick = {
                            onClickItem(navController, item)
                        }
                    )
                }
                else -> {
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painterResource(id = item.icon),
                                contentDescription = stringResource(id = item.titleId)
                            )
                        },
                        selectedContentColor = NavigationIconColorSelected,
                        unselectedContentColor = NavigationIconColorDefault,
                        selected = currentRoute == item.route,
                        onClick = {
                            onClickItem(navController, item)
                        }
                    )
                }
            }
        }
    }
}

fun onClickItem(navController: NavHostController, navigationItem: NavigationItem) {
    navController.navigate(navigationItem.route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    //BottomNavigationBar(navController)
    Box(
        contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            painterResource(id = R.drawable.ic_survey),
            contentDescription = "item.title"
        )
        Icon(
            painterResource(id = R.drawable.ic_red_dot),
            contentDescription = "item.title",
            tint = RedDotColor,
            modifier = Modifier
                .size(8.dp)
        )
    }
}