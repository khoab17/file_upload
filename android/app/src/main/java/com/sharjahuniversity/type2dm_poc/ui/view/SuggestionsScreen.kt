package com.sharjahuniversity.type2dm_poc.ui.view

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.data.model.SuggestionsList
import com.sharjahuniversity.type2dm_poc.ui.component.*
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.SuggestionsScreenViewModel
import com.sharjahuniversity.type2dm_poc.utils.NavigationItem
import com.sharjahuniversity.type2dm_poc.utils.Utils

var suggestionsList by mutableStateOf(SuggestionsList())


@Composable
fun SuggestionsScreen(
    mActivity: ComponentActivity,
    navController: NavHostController,
    suggestionsScreenViewModel: SuggestionsScreenViewModel
) {
    var showDeleteAllConfirmationDialog by remember {
        mutableStateOf(false)
    }
    var feedback by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit){
        suggestionsScreenViewModel.apply {
            if (Utils.isInternetConnected(mActivity))
                fetchSuggestionsFromServer(Utils.getUserId(mActivity)!!)
            else getSuggestions()
        }
    }
    Type2DMPocTheme() {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ) { it->
            Log.d("PADDING:", it.toString())
            BackgroundTopColoredBox(
                shape = BackgroundTopColoredShape.medium,
                color = SurveyScreenTopBoxBackgroundColor
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                if (showDeleteAllConfirmationDialog) {
                    ConfirmationDialog(
                        text = stringResource(R.string.delete_all_suggestions_confirmmation_text),
                        yesButtonClicked = {
                            suggestionsScreenViewModel.deleteAllSuggestions()
                            showDeleteAllConfirmationDialog = false
                        },
                        noButtonClicked = { showDeleteAllConfirmationDialog = false },
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 12.dp),
                ) {
                    DefaultTopBar(
                        title = stringResource(R.string.suggestions),
                        titleColor = White,
                        iconTint = White
                    ) {
                        navigate(NavigationItem.Home, navController)
                    }
                }
                Box(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Column (modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally){
                        CustomField(modifier = Modifier.background(Color.White, RoundedCornerShapes.medium).padding(20.dp), text = feedback, label = "Your Feedback", onChange = {feedback = it }, onFocusedColor = CompleteButtonColor
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomButtonWithIcon(text = "Send", onClick = {

                        },
                            icon = R.drawable.ic_send, color = if(feedback.isNotEmpty()) SurveyScreenButtonBlueColor else DefaultSubtitleColor ,
                            enabled = feedback.isNotEmpty()
                        )
                    }
                }
                Box(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Preview
@Composable
fun SuggestionsScreenPreview() {
    /*SuggestionsScreen(
        ComponentActivity(),
        rememberNavController()
    )*/
}