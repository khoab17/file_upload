package com.sharjahuniversity.type2dm_poc.ui.view

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.data.model.CircularProgressbar
import com.sharjahuniversity.type2dm_poc.ui.component.*
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.HomeScreenViewModel
import com.sharjahuniversity.type2dm_poc.utils.*
import java.util.*

@Composable
fun HomeScreen(
    mActivity: ComponentActivity,
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel
) {

    LaunchedEffect(Unit) {
        homeScreenViewModel.apply {
            if (Utils.isInternetConnected(mActivity)) Utils.getUserId(mActivity)
                ?.let { it1 -> fetchAllGoals(it1) }
            getAll()

        }
    }

    Type2DMPocTheme {
        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            content = {
                Log.d("PADDING:", it.toString())
                BackgroundTopColoredBox(
                    shape = BackgroundTopColoredShape.medium,
                    color = HomeScreenTopBoxBackgroundColor
                )
                Column(
                    modifier = Modifier
                        .verticalScroll(ScrollState(0))
                        .padding(start = 24.dp, top = 8.dp, end = 24.dp),
                ) {
                    HomeScreenTopBar()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        IntroductionCard()
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
                            .background(SurveyScreenButtonBlueColor, RoundedCornerShapes.small)
                            .clickable {
                                letStartClick(navController)
                            }
                    ) {
                        Text(
                            text = stringResource(R.string.lets_start),
                            fontWeight = FontWeight(600),
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            color = White,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier
                                .padding(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 16.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth()
                    )
                }
            }
        )
    }
}


fun letStartClick(navController: NavHostController) {
    navController.navigate(NavigationItem.Projects.route) {
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


@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    /*HomeScreen(
        ComponentActivity(),
        rememberNavController()
    )*/
}