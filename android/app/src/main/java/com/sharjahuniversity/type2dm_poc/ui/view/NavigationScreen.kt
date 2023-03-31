package com.sharjahuniversity.type2dm_poc.ui.view

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.ui.component.BackgroundTopColoredBox
import com.sharjahuniversity.type2dm_poc.ui.component.DefaultTopBar
import com.sharjahuniversity.type2dm_poc.ui.component.NavigationItemCard
import com.sharjahuniversity.type2dm_poc.ui.theme.BackgroundTopColoredShape
import com.sharjahuniversity.type2dm_poc.ui.theme.Black
import com.sharjahuniversity.type2dm_poc.ui.theme.NavigationScreenTopBoxBackgroundColor
import com.sharjahuniversity.type2dm_poc.ui.theme.Type2DMPocTheme
import com.sharjahuniversity.type2dm_poc.utils.NavigationItem

var currentRoute: NavigationItem = NavigationItem.Home

@Composable
fun NavigationScreen(
    mActivity: ComponentActivity,
    navController: NavHostController
) {
    val itemList = listOf(
        NavigationItem.Food,
        NavigationItem.Water,
        NavigationItem.Exercise,
        NavigationItem.Steps,
        NavigationItem.Sleep,
        NavigationItem.Weight
    )
    Type2DMPocTheme {
        Scaffold(
            modifier = Modifier.fillMaxWidth(),
        ) {
            BackgroundTopColoredBox(
                shape = BackgroundTopColoredShape.medium,
                color = NavigationScreenTopBoxBackgroundColor
            )
            Column(
                modifier = Modifier
                    .verticalScroll(ScrollState(0))
                    .padding(start = 24.dp, top = 8.dp, end = 24.dp),
            ) {
                DefaultTopBar(
                    title = stringResource(R.string.activities),
                    titleColor = Black,
                    iconTint = Black
                ) {
                    navigate(currentRoute, navController)
                }
                val isOddList = itemList.size % 2 == 1
                for (i in 0 until itemList.size - 1 step 2) {
                    if (i == itemList.size - 1) break
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .weight(1f)
                                .height(IntrinsicSize.Max)
                        ) {
                            SetNavScreenItemInTheScreen(
                                navigationItem = itemList[i],
                                navController = navController
                            )
                        }
                        Box(modifier = Modifier.width(16.dp))
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier
                                .weight(1f)
                                .height(IntrinsicSize.Max)
                        ) {
                            SetNavScreenItemInTheScreen(
                                navigationItem = itemList[i + 1],
                                navController = navController
                            )
                        }
                    }
                }
                if (isOddList) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier
                                //.weight(.5f)
                                .fillMaxWidth(0.5f)
                                .height(IntrinsicSize.Min)
                        ) {
                            SetNavScreenItemInTheScreen(
                                navigationItem = itemList.last(),
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SetNavScreenItemInTheScreen(navigationItem: NavigationItem, navController: NavHostController) {
    NavigationItemCard(
        title = stringResource(id = navigationItem.titleId),
        iconId = navigationItem.icon,
        iconBackgroundBrush = navigationItem.iconBackgroundBrush,
        iconBorderColor = navigationItem.iconBorderColor
    ) {
        navigate(navigationItem, navController)
    }
}

fun navigate(navigationItem: NavigationItem, navController: NavHostController) {
    navController.navigate(navigationItem.route) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Preview
@Composable
fun NavigationScreenPreview() {
//    NavigationScreen(
//        mActivity = ComponentActivity(),
//        navController = rememberNavController()
//    )
}