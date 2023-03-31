package com.sharjahuniversity.type2dm_poc.ui.view

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.Project
import com.sharjahuniversity.type2dm_poc.ui.component.BackgroundTopColoredBox
import com.sharjahuniversity.type2dm_poc.ui.component.CustomButton
import com.sharjahuniversity.type2dm_poc.ui.component.DefaultTopBar
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.ProjectListViewModel
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.SurveyScreenViewModel
import com.sharjahuniversity.type2dm_poc.utils.Constants
import com.sharjahuniversity.type2dm_poc.utils.NavigationItem

var projects by mutableStateOf(ArrayList<Project>())

@Composable
fun ProjectSelectionScreen(
    mActivity: ComponentActivity,
    navController: NavHostController,
    projectListViewModel: ProjectListViewModel,
    surveyScreenViewModel: SurveyScreenViewModel
) {

    var selectedIndex by remember { mutableStateOf(-1) }

    var showDropDown by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        projectListViewModel.fetchProjectsFromServer()
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

                    DefaultTopBar(
                        title = stringResource(R.string.projects),
                        titleColor = White,
                        iconTint = White
                    ) {
                        navigate(NavigationItem.Home, navController)
                    }


                    Spacer(modifier = Modifier.height(100.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShapes.medium as RoundedCornerShape)
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(20.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier.weight(0.5f)
                            ) {
                                Text(
                                    text = stringResource(R.string.select_project),
                                    color = DefaultTitleColor,
                                    fontWeight = FontWeight(400),
                                    fontSize = 14.sp,
                                    lineHeight = 14.sp,
                                    modifier = Modifier.padding(bottom = 14.dp, start = 5.dp)
                                )
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 18.dp)
                                        .wrapContentHeight()
                                        .clip(RoundedCornerShapes.small)
                                        .border(1.dp, DefaultBorderColor, RoundedCornerShapes.small)
                                        .clickable { showDropDown = !showDropDown }
                                        .onFocusChanged { showDropDown = false }
                                ){
                                    Text(
                                        text = if(selectedIndex >-1) projects[selectedIndex].name else stringResource(
                                            id = R.string.select_option
                                        ),
                                        color = DefaultTextFieldTextColor,
                                        fontWeight = FontWeight(400),
                                        fontSize = 14.sp,
                                        lineHeight = 14.sp,
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .padding(
                                                top = 16.dp,
                                                start = 16.dp,
                                                end = 8.dp,
                                                bottom = 16.dp
                                            ),
                                    )

                                    if (showDropDown) {
                                        DropdownMenu(
                                            expanded = showDropDown,
                                            onDismissRequest = { showDropDown = false
                                            }
                                        ) {
                                            projects.forEachIndexed { index, project ->
                                                DropdownMenuItem(onClick = {
                                                    selectedIndex = index
                                                    showDropDown = false
                                                }
                                                    ) {
                                                    Text(text = project.name)
                                                }
                                            }
                                        }
                                    }
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_down),
                                        contentDescription = Constants.DropDownIcon,
                                        modifier = Modifier
                                            .padding(end = 16.dp)
                                            .size(12.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Box() {
                        CustomButton(text = stringResource(id = R.string.lets_start), onClick = {
                            if (projects.size > 0) {
                                surveyScreenViewModel.projectIdSelected =
                                    projects[selectedIndex]._id
                                surveyScreenViewModel.projectNameSelected =
                                    projects[selectedIndex].name
                                moveToSurveyScreen(navController)
                            }
                        }, enabled = selectedIndex>-1, color = SurveyScreenButtonBlueColor)
                    }
/*                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
                            .background(SurveyScreenButtonBlueColor, RoundedCornerShapes.small)
                            .clickable {

                                if (projects.size > 0) {
                                    surveyScreenViewModel.projectIdSelected =
                                        projects[selectedIndex]._id
                                    surveyScreenViewModel.projectNameSelected =
                                        projects[selectedIndex].name

                                    moveToSurveyScreen(navController)
                                }

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
                    }*/

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


fun moveToSurveyScreen(navController: NavHostController) {
    navController.navigate(NavigationItem.Survey.route) {
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
fun ProjectSelectionScreenPreview() {
    /*HomeScreen(
        ComponentActivity(),
        rememberNavController()
    )*/
}