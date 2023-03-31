package com.sharjahuniversity.type2dm_poc.ui.view

import android.content.IntentFilter
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sharjahuniversity.type2dm_poc.data.model.*
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.DataProjectList
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.FetchAllProjects
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.FetchSurveyResponseDataSurvey
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.Project
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.*
import com.sharjahuniversity.type2dm_poc.utils.*
import com.sharjahuniversity.type2dm_poc.utils.broadcastreceiver.DataUploadBroadcastReceiver
import com.sharjahuniversity.type2dm_poc.utils.broadcastreceiver.NotifierBroadcastReceiver
import com.sharjahuniversity.type2dm_poc.utils.worker.DailyResetWorker
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import java.util.concurrent.TimeUnit

var hasNewGoalsSet by mutableStateOf(false)

@ActivityScoped
@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private lateinit var notifierBroadcastReceiver: NotifierBroadcastReceiver
    private lateinit var dataUploadBroadcastReceiver: DataUploadBroadcastReceiver
    private lateinit var workManager: WorkManager
    private val navigationAnimationTransitionTime = NavigationAnimationTransitionTime.Slow.value


    private val homeScreenViewModel: HomeScreenViewModel by viewModels()

    private val suggestionsScreenViewModel: SuggestionsScreenViewModel by viewModels()
    private val surveyScreenViewModel: SurveyScreenViewModel by viewModels()
    private val profileScreenViewModel: ProfileScreenViewModel by viewModels()
    private val profileEditScreenViewModel: ProfileEditScreenViewModel by viewModels()
    private val projectListViewModel: ProjectListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContent {
            content()
        }
    }

    private fun init() {
        setupDailyResetWorker()
        observeDBUpdateInViewModels()
        if (Utils.getHasNewSuggestion(applicationContext)) hasNewSuggestions = true
        if (Utils.getHasNewSurvey(applicationContext)) hasNewSurvey = true
        if (Utils.getHasNewGoalsSet(applicationContext)) hasNewGoalsSet = true
    }

    private fun observeDBUpdateInViewModels() {
        homeScreenViewModel.apply {
            if (hasNewGoalsSet) fetchAllGoals(user.value.id)
            getAllGoal()
            goals.observe(this@HomeActivity) {
                when (it) {
                    is DataState.Success -> {
                        if (it.data.isEmpty()) {
                            Constants.GoalEnums.forEach { map ->
                                setGoal(Goal(map.key, map.value))
                            }
                        }
                    }
                    else -> {}
                }
            }
            isGoalUpdated.observe(this@HomeActivity) {
                when (it) {
                    is DataState.Success -> {
                        getAllGoal()
                    }
                    else -> {}
                }
            }
            fetchedGoalsResponse.observe(this@HomeActivity) {
                when (it) {
                    is DataState.Success -> {
                        if (it.data.success) {
                            it.data.fetchUserGoalsResponseData.fetchUserGoalsResponseDataUser.apply {
                                setGoal(Goal(GoalEnum.Weight.itemName, this.weightGoal))
                                setGoal(Goal(GoalEnum.Water.itemName, this.waterGoal))
                                setGoal(Goal(GoalEnum.Steps.itemName, this.stepGoal))
                                setGoal(Goal(GoalEnum.Sleep.itemName, this.sleepGoal))
                                setGoal(
                                    Goal(
                                        GoalEnum.CalorieIntake.itemName,
                                        this.calorieIntakeGoal
                                    )
                                )
                                setGoal(Goal(GoalEnum.CalorieBurn.itemName, this.calorieBurnGoal))
                            }
                            hasNewGoalsSet = false
                            Utils.setHasNewGoalsSet(applicationContext, false)
                        }
                    }
                    else -> {}
                }
            }
        }
        surveyScreenViewModel.apply {
            surveyResponse.observe(this@HomeActivity) {
                when (it) {
                    is DataState.Success -> {
                        val surveyQuestionList = SurveyQuestionsList()
                        surveys =
                            it.data.fetchSurveyResponseDataSurveys as ArrayList<FetchSurveyResponseDataSurvey>
                        Log.e("survey_data", "observeDBUpdateInViewModels: $surveys")

                        surveys.forEach { survey ->
                            survey.fetchSurveyResponseDataSurveyQuestions.forEach { question ->
                                val options: MutableList<String> = mutableListOf()
                                val answers: MutableList<String> = mutableListOf()

                                question.fetchSurveyResponseDataSurveyQuestionOptions.forEachIndexed { index, option ->
                                    options.add(index, option.text)
                                    if (option.selected)
                                        answers.add(index, option.text)
                                }

                                val surveyQuestion = SurveyQuestions(question.id, question.questionText, question.questionType, options, answers)
                                surveyQuestionList.add(surveyQuestion)
                            }
                        }
                        surveyScreenViewModel.insertSurveyQuestions(surveyQuestionList)

                        surveyScreenViewModel.isDBUpdated.observe(this@HomeActivity){
                            Log.d("isDBUpdate", "isDBUpdated: $it")
                        }
                        
                        hasNewSurvey = if (surveys.isNotEmpty()) {
                            Utils.setHasNewSurvey(applicationContext, true)
                            true
                        } else {
                            Utils.setHasNewSurvey(applicationContext, false)
                            false
                        }
                    }
                    else -> {}
                }
            }
            uploadResponse.observe(this@HomeActivity) {
                when (it) {
                    is DataState.Success -> if (it.data.success) {
                        hasNewSurvey = false
                        Utils.setHasNewSurvey(applicationContext, false)
                    }
                    else -> {}
                }
            }
        }

        // project work starts
        projectListViewModel.apply {
            projectsResponse.observe(this@HomeActivity) {
                when (it) {
                    is DataState.Success -> {
                        projects =
                            it.data.project as ArrayList<Project>
                        hasNewSurvey = if (surveys.isNotEmpty()) {
                            Utils.setHasNewSurvey(applicationContext, true)
                            true
                        } else {
                            Utils.setHasNewSurvey(applicationContext, false)
                            false
                        }
                    }
                    else -> {}
                }
            }

        }
        // project work ends

        suggestionsScreenViewModel.apply {
            suggestionsResponse.observe(this@HomeActivity) {
                when (it) {
                    is DataState.Success -> {
                        if (it.data.success) {
                            it.data.fetchSuggestionsResponseData.fetchSuggestionsResponseDataSuggestions.forEach { suggestion ->
                                insertSuggestions(
                                    SuggestionsItem(
                                        suggestion.id,
                                        Utils.serverDateTimeToLocalDBDateTime(suggestion.updatedAt),
                                        "",
                                        suggestion.description,
                                        false,
                                        false
                                    )
                                )
                            }
                            hasNewSuggestions = false
                            Utils.setHasNewSuggestion(applicationContext, false)
                        } else getSuggestions()
                    }
                    else -> getSuggestions()
                }
            }
            suggestions.observe(this@HomeActivity) {
                when (it) {
                    is DataState.Success -> suggestionsList = it.data
                    else -> {}
                }
            }
            isDBUpdated.observe(this@HomeActivity) {
                when (it) {
                    is DataState.Success -> {
                        getSuggestions()
                    }
                    else -> {}
                }
            }
        }

        profileScreenViewModel.apply {
            Utils.getUserId(this@HomeActivity)?.let { fetchUser(it) }
            fetchedUserResponseModel.observe(this@HomeActivity) {
                when (it) {
                    is DataState.Success -> if (it.data.success) {
                        it.data.fetchUserResponseData.fetchUserResponseDataUser.apply {
                            storeFetchedUserIntoLocalDB(
                                FetchedUserProfileData(
                                    email = this.email,
                                    id = this.id,
                                    imageUrl = this.imageURL,
                                    name = this.name,
                                    phone = this.phone,
                                    studentId = this.studentID,
                                    gender = this.gender,
                                    organization = this.organization,
                                    education_level = this.educationLevel,
                                    surname = this.surname,
                                    occupation = this.occupation,
                                    income = this.income,
                                    age = this.age

                                )
                            )
                        }
                    } else {
                        getUser()
                    }
                    is DataState.Error -> getUser()
                    else -> {}
                }
            }
            storedData.observe(this@HomeActivity) {
                when (it) {
                    is DataState.Success -> getUser()
                    else -> {}
                }
            }
            fetchedUserProfileData.observe(this@HomeActivity) {
                when (it) {
                    is DataState.Success -> {
                        user.value = it.data
                    }
                    else -> {}
                }
            }
        }
        profileEditScreenViewModel.apply {
            userDataUploadResponse.observe(this@HomeActivity) {
                when (it) {
                    is DataState.Success -> {
                        Log.d("userdata", "observeDBUpdateInViewModels: $it")
                        profileScreenViewModel.fetchUser(it.data.userDataUploadResponseData.userDataUploadResponseDataUser.id)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun setupDailyResetWorker() {
        val dueDate = Calendar.getInstance()
        dueDate.set(Calendar.HOUR_OF_DAY, 0)
        dueDate.set(Calendar.MINUTE, 0)
        dueDate.set(Calendar.SECOND, 0)

        val dailyResetWorkRequest = PeriodicWorkRequest.Builder(
            DailyResetWorker::class.java,
            24,
            TimeUnit.HOURS
        )
            .setInitialDelay(
                Utils.getInitialIntervalTillDailyResetTime(dueDate),
                TimeUnit.MILLISECONDS
            )
            .addTag(Constants.DAILY_RESET_WORK_TAG)
            .build()

        workManager = WorkManager.getInstance(this)
        workManager.enqueueUniquePeriodicWork(
            Constants.DAILY_RESET_WORK_TAG,
            ExistingPeriodicWorkPolicy.KEEP,
            dailyResetWorkRequest
        )
        workManager.getWorkInfoByIdLiveData(dailyResetWorkRequest.id).observe(this) {
            if (it?.state == WorkInfo.State.ENQUEUED)
                homeScreenViewModel.getAll()
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun content() {
        val navController = rememberAnimatedNavController()
        Type2DMPocTheme {
            // A surface container using the 'background' color from the theme
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundColor),
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController
                    )
                }
            ) {
                AnimatedNavHost(navController, startDestination = NavigationItem.Home.route) {
//                    composable(
//                        route = NavigationItem.Navigation.route,
//                        enterTransition = {
//                            scaleIn(
//                                animationSpec = tween(navigationAnimationTransitionTime),
//                                transformOrigin = TransformOrigin(
//                                    getDirectionForScaleInTransition(
//                                        0.5f
//                                    ), 1f
//                                )
//                            )
//                        },
//                        exitTransition = {
//                            scaleOut(
//                                animationSpec = tween(navigationAnimationTransitionTime),
//                                transformOrigin = TransformOrigin(
//                                    getDirectionForScaleInTransition(
//                                        0.5f
//                                    ), 1f
//                                )
//                            )
//                        }
//                    ) {
//                        navAddButtonBrush = FABBlueBrush
//                        rememberSystemUiController().setSystemBarsColor(
//                            color = NavigationScreenTopBoxBackgroundColor,
//                            darkIcons = true,
//                        )
//                        NavigationScreen(
//                            mActivity = this@HomeActivity,
//                            navController = navController
//                        )
//                    }
                    composable(
                        route = NavigationItem.Home.route,
                        enterTransition = {
                            scaleIn(
                                animationSpec = tween(navigationAnimationTransitionTime),
                                transformOrigin = TransformOrigin(
                                    getDirectionForScaleInTransition(
                                        0.1f
                                    ), 1f
                                )
                            )
                        },
                        exitTransition = {
                            scaleOut(
                                animationSpec = tween(navigationAnimationTransitionTime),
                                transformOrigin = TransformOrigin(
                                    getDirectionForScaleInTransition(
                                        0.1f
                                    ), 1f
                                )
                            )
                        }
                    ) {
                        currentRoute = NavigationItem.Home
                        navAddButtonBrush = FABBlueBrush
                        rememberSystemUiController().setSystemBarsColor(
                            color = HomeScreenTopBoxBackgroundColor,
                            darkIcons = false,
                        )
                        HomeScreen(
                            mActivity = this@HomeActivity,
                            navController = navController,
                            homeScreenViewModel
                        )
                    }
                    composable(
                        route = NavigationItem.Survey.route,
                        enterTransition = {
                            scaleIn(
                                animationSpec = tween(navigationAnimationTransitionTime),
                                transformOrigin = TransformOrigin(
                                    getDirectionForScaleInTransition(
                                        0.3f
                                    ), 1f
                                )
                            )
                        },
                        exitTransition = {
                            scaleOut(
                                animationSpec = tween(navigationAnimationTransitionTime),
                                transformOrigin = TransformOrigin(
                                    getDirectionForScaleInTransition(
                                        0.3f
                                    ), 1f
                                )
                            )
                        }
                    ) {
                        currentRoute = NavigationItem.Survey
                        navAddButtonBrush = FABBlueBrush
                        rememberSystemUiController().setSystemBarsColor(
                            color = SurveyScreenTopBoxBackgroundColor,
                            darkIcons = false,
                        )
                        SurveyScreen(
                            mActivity = this@HomeActivity,
                            navController = navController,
                            surveyScreenViewModel = surveyScreenViewModel
                        )
                    }


                    composable(
                        route = NavigationItem.Projects.route,
                        enterTransition = {
                            scaleIn(
                                animationSpec = tween(navigationAnimationTransitionTime),
                                transformOrigin = TransformOrigin(
                                    getDirectionForScaleInTransition(
                                        0.3f
                                    ), 1f
                                )
                            )
                        },
                        exitTransition = {
                            scaleOut(
                                animationSpec = tween(navigationAnimationTransitionTime),
                                transformOrigin = TransformOrigin(
                                    getDirectionForScaleInTransition(
                                        0.3f
                                    ), 1f
                                )
                            )
                        }
                    ) {
                        currentRoute = NavigationItem.Projects
                        navAddButtonBrush = FABBlueBrush
                        rememberSystemUiController().setSystemBarsColor(
                            color = SurveyScreenTopBoxBackgroundColor,
                            darkIcons = false,
                        )
                        ProjectSelectionScreen(
                            mActivity = this@HomeActivity,
                            navController = navController,
                            projectListViewModel = projectListViewModel,
                            surveyScreenViewModel = surveyScreenViewModel
                        )
                    }

                    composable(
                        route = NavigationItem.Suggestions.route,
                        enterTransition = {
                            scaleIn(
                                animationSpec = tween(navigationAnimationTransitionTime),
                                transformOrigin = TransformOrigin(
                                    getDirectionForScaleInTransition(
                                        0.7f
                                    ), 1f
                                )
                            )
                        },
                        exitTransition = {
                            scaleOut(
                                animationSpec = tween(navigationAnimationTransitionTime),
                                transformOrigin = TransformOrigin(
                                    getDirectionForScaleInTransition(
                                        0.7f
                                    ), 1f
                                )
                            )
                        }
                    ) {
                        currentRoute = NavigationItem.Suggestions
                        navAddButtonBrush = FABBlueBrush
                        rememberSystemUiController().setSystemBarsColor(
                            color = SuggestionsScreenTopBoxBackgroundColor,
                            darkIcons = false,
                        )
                        SuggestionsScreen(
                            mActivity = this@HomeActivity,
                            navController = navController,
                            suggestionsScreenViewModel = suggestionsScreenViewModel
                        )
                    }
                    composable(
                        route = NavigationItem.Profile.route,
                        enterTransition = {
                            scaleIn(
                                animationSpec = tween(navigationAnimationTransitionTime),
                                transformOrigin = TransformOrigin(
                                    getDirectionForScaleInTransition(
                                        0.9f
                                    ), 1f
                                )
                            )
                        },
                        exitTransition = {
                            scaleOut(
                                animationSpec = tween(navigationAnimationTransitionTime),
                                transformOrigin = TransformOrigin(
                                    getDirectionForScaleInTransition(
                                        0.9f
                                    ), 1f
                                )
                            )
                        }
                    ) {
                        currentRoute = NavigationItem.Profile
                        navAddButtonBrush = FABBlueBrush
                        rememberSystemUiController().setSystemBarsColor(
                            color = SuggestionsScreenTopBoxBackgroundColor,
                            darkIcons = false,
                        )
                        ProfileScreen(
                            mActivity = this@HomeActivity,
                            navController = navController,
                            profileScreenViewModel = profileScreenViewModel
                        )
                    }
                    composable(
                        route = NavigationItem.ProfileEdit.route,
                        enterTransition = {
                            scaleIn(
                                animationSpec = tween(navigationAnimationTransitionTime),
                                transformOrigin = TransformOrigin.Center
                            )
                        },
                        exitTransition = {
                            scaleOut(
                                animationSpec = tween(navigationAnimationTransitionTime),
                                transformOrigin = TransformOrigin.Center
                            )
                        }
                    ) {
                        currentRoute = NavigationItem.ProfileEdit
                        navAddButtonBrush = FABBlueBrush
                        rememberSystemUiController().setSystemBarsColor(
                            color = SuggestionsScreenTopBoxBackgroundColor,
                            darkIcons = false,
                        )
                        ProfileEditScreen(
                            mActivity = this@HomeActivity,
                            navController = navController,
                            profileEditScreenViewModel = profileEditScreenViewModel
                        )
                    }


                }
            }
        }
    }

    private fun initBroadcastReceiver() {
        val intentFilter = IntentFilter(Constants.ACTION_NEW_SUGGESTIONS)
        intentFilter.addAction(Constants.ACTION_NEW_SURVEY)
        intentFilter.addAction(Constants.ACTION_NEW_GOALS_SET)
        notifierBroadcastReceiver = NotifierBroadcastReceiver()

        LocalBroadcastManager.getInstance(applicationContext)
            .registerReceiver(notifierBroadcastReceiver, intentFilter)

        dataUploadBroadcastReceiver = DataUploadBroadcastReceiver()
        LocalBroadcastManager.getInstance(applicationContext)
            .registerReceiver(
                dataUploadBroadcastReceiver,
                IntentFilter(Constants.ACTION_UPLOAD_NEW_DATA)
            )
    }

    private fun getDirectionForScaleInTransition(value: Float): Float {
        return if (resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL) 1f - value else value
    }

    private fun unregisterBroadcastReceiver() {
        LocalBroadcastManager.getInstance(applicationContext)
            .unregisterReceiver(notifierBroadcastReceiver)

        LocalBroadcastManager.getInstance(applicationContext)
            .unregisterReceiver(dataUploadBroadcastReceiver)
    }

    override fun onStart() {
        super.onStart()
        initBroadcastReceiver()
    }

    override fun onStop() {
        super.onStop()
        unregisterBroadcastReceiver()
    }
}