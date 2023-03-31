package com.sharjahuniversity.type2dm_poc.ui.view

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.FetchSurveyResponseDataSurvey
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.FetchSurveyResponseDataSurveyQuestion
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.FetchSurveyResponseDataSurveyQuestionOption
import com.sharjahuniversity.type2dm_poc.data.model.uploadModel.SurveyAnswerUploadModel
import com.sharjahuniversity.type2dm_poc.data.model.uploadModel.SurveyAnswerUploadModelSurvey
import com.sharjahuniversity.type2dm_poc.data.model.uploadModel.SurveyAnswerUploadModelSurveyQuestion
import com.sharjahuniversity.type2dm_poc.data.model.uploadModel.SurveyAnswerUploadModelSurveyQuestionOption
import com.sharjahuniversity.type2dm_poc.ui.component.BackgroundTopColoredBox
import com.sharjahuniversity.type2dm_poc.ui.component.DefaultTopBar
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.SurveyScreenViewModel
import com.sharjahuniversity.type2dm_poc.utils.*

var surveys by mutableStateOf(ArrayList<FetchSurveyResponseDataSurvey>())

@Composable
fun SurveyScreen(
    mActivity: ComponentActivity,
    navController: NavHostController,
    surveyScreenViewModel: SurveyScreenViewModel
) {

    var showScreen by remember {
        mutableStateOf(SurveyScreen.Complete)
    }
    showScreen = if (surveys.isNotEmpty()) SurveyScreen.Introduction else SurveyScreen.Complete
    var surveyAnswerUploadModelSurveyQuestion: List<SurveyAnswerUploadModelSurveyQuestion> =
        ArrayList()
    var rating = 0

    val projectIdSelected = surveyScreenViewModel.projectIdSelected
    val projectNameSelected = surveyScreenViewModel.projectNameSelected

    /*surveyQuestionsList.add(
        SurveyQuestions(
            "1. What do you say about your overall health?",
            "Checkbox",
            listOf("a", "b", "c"),
            listOf()
        )
    )
    surveyQuestionsList.add(
        SurveyQuestions(
            "2. What do you say about your overall health?",
            "RadioButton",
            listOf("a", "b", "c"),
            listOf()
        )
    )
    surveyQuestionsList.add(
        SurveyQuestions(
            "3. What do you say about your overall health?",
            "TextField",
            listOf("a", "b", "c"),
            listOf()
        )
    )*/

    LaunchedEffect(Unit){
        surveyScreenViewModel.fetchSurveyFromServer()


    }
    Type2DMPocTheme() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {

                BackgroundTopColoredBox(
                    shape = BackgroundTopColoredShape.medium,
                    color = SuggestionsScreenTopBoxBackgroundColor
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(ScrollState(0))
                        .padding(paddingValues = it)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 12.dp),
                    ) {
                        DefaultTopBar(
                            title = stringResource(R.string.survey),
                            titleColor = White,
                            iconTint = White
                        ) {
                            navigate(NavigationItem.Home, navController)
                        }
                    }
                    when (showScreen) {
                        SurveyScreen.Introduction -> introductionScreen {
                            showScreen = SurveyScreen.QuestionsAnswers
                        }
                        SurveyScreen.QuestionsAnswers -> {
                            var index by remember {
                                mutableStateOf(0)
                            }
                            if (index < surveys.first().fetchSurveyResponseDataSurveyQuestions.size) {
                                surveys.first().fetchSurveyResponseDataSurveyQuestions[index].apply {
                                    var answers = listOf<String>()
                                    Questions(
                                        surveyQuestions = this,
                                        answers = {
                                            answers = it
                                        },
                                        nextButtonClicked = {
                                            /*surveyQuestionsAnswersList.add(
                                                FetchSurveyResponseDataSurveyQuestion(
                                                    this.question,
                                                    this.optionsType,
                                                    this.options,
                                                    answers
                                                )
                                            )*/
                                            var textAnswer = ""
                                            val surveyAnswerUploadModelSurveyQuestionOption =
                                                ArrayList<SurveyAnswerUploadModelSurveyQuestionOption>()
                                            if (questionType == SurveyQuestionsType.TextField.value) {
                                                textAnswer = answers.first()
                                            } else {
                                                this.fetchSurveyResponseDataSurveyQuestionOptions.forEach {
                                                    surveyAnswerUploadModelSurveyQuestionOption.add(
                                                        SurveyAnswerUploadModelSurveyQuestionOption(
                                                            it.id,
                                                            answers.contains(it.text),
                                                            it.text
                                                        )
                                                    )
                                                }
                                            }
                                            surveyAnswerUploadModelSurveyQuestion =
                                                surveyAnswerUploadModelSurveyQuestion.plus(
                                                    SurveyAnswerUploadModelSurveyQuestion(
                                                        textAnswer,
                                                        this.id,
                                                        surveyAnswerUploadModelSurveyQuestionOption,
                                                        this.questionText,
                                                        this.questionType
                                                    )
                                                )
                                            index++
                                        }
                                    )
                                }
                            } else if (index == surveys.first().fetchSurveyResponseDataSurveyQuestions.size) {
                                SurveyScreenRating {
                                    rating = it
                                    index++
                                }
                            } else {
                                val survey = surveys.first()
                                val data = SurveyAnswerUploadModel(
                                    userId = Utils.getUserId(mActivity.applicationContext)!!,
                                    surveyAnswerUploadModelSurvey = SurveyAnswerUploadModelSurvey(
                                        survey.id,
                                        survey.name,
                                        projectIdSelected,
                                        projectNameSelected,
                                        surveyAnswerUploadModelSurveyQuestion,
                                        rating
                                    )
                                )
                                surveyScreenViewModel.uploadSurveyAnswer(data)
                                rating = 0
                                surveys = ArrayList()
                                surveyAnswerUploadModelSurveyQuestion = ArrayList()
                                showScreen = SurveyScreen.Complete
                            }
                        }
                        SurveyScreen.Complete -> {
                            Complete() {
                                navigate(NavigationItem.Home, navController)
                            }
                        }
                    }
                    Box(modifier = Modifier.height(70.dp))
                }
            }
        )
    }
}

@Composable
fun introductionScreen(
    startButtonClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 24.dp),
    ) {
        Column(
            modifier = Modifier
                .background(White, RoundedCornerShapes.medium)
        ) {
            Text(
                text = stringResource(R.string.health_survey_card_title),
                fontWeight = FontWeight(500),
                fontSize = 20.sp,
                lineHeight = 24.sp,
                color = SurveyScreenTitleBlueColor,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 16.dp)
            )
        }
        Box(modifier = Modifier.height(height = 16.dp))
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(White, RoundedCornerShapes.medium)
        ) {
            Column() {
                Text(
                    text = stringResource(R.string.health_survey_que),
                    fontWeight = FontWeight(500),
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    color = SurveyScreenTitleBlueColor,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 8.dp)
                )
                Text(
                    text = stringResource(R.string.health_survey_intro),
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = DefaultTitleColor,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 24.dp)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
                    .background(SurveyScreenButtonBlueColor, RoundedCornerShapes.small)
                    .clickable {
                        startButtonClicked()
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
        }
    }
}

@Composable
fun Complete(
    doneButtonClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 24.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(White, RoundedCornerShapes.medium)
                .padding(start = 0.dp, top = 72.dp, end = 0.dp, bottom = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_complete_right_icon),
                contentDescription = stringResource(id = R.string.complete),
                modifier = Modifier
                    .size(100.dp)
            )
            Text(
                text = stringResource(R.string.congratulations),
                fontWeight = FontWeight(500),
                fontSize = 20.sp,
                lineHeight = 20.sp,
                color = DefaultTitleColor,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 24.dp, top = 36.dp, end = 24.dp, bottom = 16.dp)
            )
            Text(
                text = stringResource(R.string.survey_successfull_completion_msg),
                fontWeight = FontWeight(500),
                fontSize = 17.sp,
                lineHeight = 26.sp,
                color = DefaultTitleColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 36.dp, top = 16.dp, end = 36.dp, bottom = 16.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 0.dp)
                    .background(SurveyScreenButtonBlueColor, RoundedCornerShapes.small)
                    .clickable {
                        doneButtonClicked()
                    }
            ) {
                Text(
                    text = stringResource(R.string.done),
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = White,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 16.dp)
                )
            }
        }
    }
}

@Composable
fun Questions(
    surveyQuestions: FetchSurveyResponseDataSurveyQuestion,
    answers: (List<String>) -> Unit,
    nextButtonClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(start = 24.dp, top = 0.dp, end = 24.dp, bottom = 24.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(White, RoundedCornerShapes.medium)
        ) {
            var isAnswered by remember {
                mutableStateOf(false)
            }
            Column() {
                Text(
                    text = surveyQuestions.questionText,
                    fontWeight = FontWeight(500),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    color = SurveyScreenTitleBlueColor,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 16.dp)
                )
                getOptions(
                    surveyQuestions.questionType,
                    surveyQuestions.fetchSurveyResponseDataSurveyQuestionOptions
                ) {
                    isAnswered =
                        if (surveyQuestions.questionType == SurveyQuestionsType.TextField.value) it.first() != ""
                        else it.isNotEmpty()
                    answers(it)
                }
            }
            Column(
                modifier = Modifier
                    .alpha(if (isAnswered) 1f else 0.5f)
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurveyScreenButtonBlueColor, RoundedCornerShapes.small)
                        .clickable(
                            enabled = isAnswered
                        ) {
                            nextButtonClicked()
                            isAnswered = false
                        }
                ) {
                    Text(
                        text = stringResource(R.string.next),
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        color = White,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 12.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun getOptions(
    optionsType: String,
    options: List<FetchSurveyResponseDataSurveyQuestionOption>,
    answers: (List<String>) -> Unit
) {
    when (optionsType) {
        SurveyQuestionsType.Checkbox.value -> SurveyCustomCheckbox(
            options = options,
            answers = {
                answers(it)
            })
        SurveyQuestionsType.TextField.value -> SurveyCustomTextField(
            answers = {
                answers(it)
            })
        SurveyQuestionsType.RadioButton.value -> SurveyCustomRadioButton(
            options = options,
            answers = {
                answers(it)
            })
    }
}
@Composable
fun SurveyCustomCheckbox(
    options: List<FetchSurveyResponseDataSurveyQuestionOption>,
    answers: (List<String>) -> Unit
) {
    var answers by remember {
        mutableStateOf(listOf<String>())
    }
    val checkedOptions by remember { mutableStateOf(mutableMapOf<String, Boolean>()) }
    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 28.dp, bottom = 24.dp)

    ) {
        options.forEach { option ->
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(modifier = Modifier.height(42.dp))

                var isChecked by remember(option.id) {
                    mutableStateOf(false)
                }

                Checkbox(

                    colors = CheckboxDefaults.colors(
                        checkedColor = SurveyScreenButtonBlueColor,
                    ),
                    checked = isChecked,
                    onCheckedChange = {
                        checkedOptions[option.id] = it
                        isChecked = it
                        answers =
                            if (it) answers.plus(option.text) else answers.minus(option.text)
                        answers(answers)
                    },
                    modifier = Modifier
                        .size(20.dp)
                )

                Box(modifier = Modifier.width(16.dp))
                Text(
                    text = option.text,
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Start,
                    color = if (isChecked) SurveyScreenTitleBlueColor else DefaultTitleColor,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun SurveyCustomTextField(
    answers: (List<String>) -> Unit
) {
    var answers: List<String>
    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 28.dp, bottom = 24.dp)
    ) {
        var text by remember {
            mutableStateOf("")
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, DefaultBorderColor, RoundedCornerShapes.small)
        ) {
            BasicTextField(
                value = text,
                onValueChange = {
                    text = it
                    answers = listOf(it)
                    answers(answers)
                },
                modifier = Modifier
                    .height(200.dp)
                    .padding(12.dp)
            )
        }

    }
}

@Composable
fun SurveyCustomRadioButton(
    options: List<FetchSurveyResponseDataSurveyQuestionOption>,
    answers: (List<String>) -> Unit
) {
    var answers by remember {
        mutableStateOf(listOf<String>())
    }
    var text by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 28.dp, bottom = 24.dp)
    ) {
        options.forEach { option ->
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(modifier = Modifier.height(42.dp))
                RadioButton(
                    selected = text == option.text,
                    onClick = {
                        text = option.text
                        answers = listOf(text)
                        answers(answers)
                        Log.d("survey", "$answers")
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = SurveyScreenButtonBlueColor
                    ),
                    modifier = Modifier.size(20.dp)
                )
                Box(modifier = Modifier.width(16.dp))
                Text(
                    text = option.text,
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Start,
                    color = if (text == option.text) SurveyScreenTitleBlueColor else DefaultTitleColor,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun SurveyScreenRating(
    nextButtonClicked: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 24.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(White, RoundedCornerShapes.medium)
                .padding(start = 0.dp, top = 28.dp, end = 0.dp, bottom = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.please_rate),
                fontWeight = FontWeight(500),
                fontSize = 17.sp,
                lineHeight = 20.sp,
                color = DefaultTitleColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 36.dp, top = 20.dp, end = 36.dp, bottom = 16.dp)
            )
            var rating by remember {
                mutableStateOf(0)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 28.dp)
            ) {
                for (i in Constants.MinimumRating..Constants.MaximumRating) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(
                                color = if (i == rating) CustomBluePrimary else Color.Transparent,
                                shape = CircleShape
                            )
                            .size(24.dp)
                            .clip(CircleShape)
                            .clickable {
                                rating = i
                            }
                            .border(1.dp, CustomBluePrimary, CircleShape)
                        //.padding(horizontal = 4.dp)
                    ) {
                        Text(
                            text = i.toString(),
                            fontWeight = FontWeight(400),
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            textAlign = TextAlign.Center,
                            color = if (i == rating) White else DefaultTitleColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .alpha(if (rating != 0) 1f else 0.5f)
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 0.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurveyScreenButtonBlueColor, RoundedCornerShapes.small)
                        .clickable(
                            enabled = rating != 0
                        ) {
                            nextButtonClicked(rating)
                        }
                ) {
                    Text(
                        text = stringResource(R.string.next),
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        color = White,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 12.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SurveyScreenPreview() {
    /*SurveyScreen(
        ComponentActivity(),
        rememberNavController()
    )*/
    SurveyScreenRating() {}
}
