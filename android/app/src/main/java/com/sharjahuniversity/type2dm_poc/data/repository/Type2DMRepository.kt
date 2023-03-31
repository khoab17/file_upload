package com.sharjahuniversity.type2dm_poc.data.repository

import android.util.Log
import com.sharjahuniversity.type2dm_poc.data.local.room.Type2DMDao
import com.sharjahuniversity.type2dm_poc.data.local.room.entities.*
import com.sharjahuniversity.type2dm_poc.data.model.*
import com.sharjahuniversity.type2dm_poc.data.model.uploadModel.*
import com.sharjahuniversity.type2dm_poc.data.remote.APIService
import com.sharjahuniversity.type2dm_poc.utils.Constants
import com.sharjahuniversity.type2dm_poc.utils.DataState
import com.sharjahuniversity.type2dm_poc.utils.Utils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Type2DMRepository @Inject constructor(
    private val apiService: APIService,
    private val db: Type2DMDao
) {
    fun uploadUserFCMToken(userId: String, token: String) = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.UploadUserFCMToken(userId, FCMTokenToPost(token))
            emit(DataState.Success(result))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }



    ///Goal
    fun fetchGoal(userId: String) = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.FetchUserGoals(userId)
            emit(DataState.Success(result))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    fun insertGoal(goal: Goal) = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.insertGoal(
                        GoalEntity(
                            goal.name,
                            goal.quantity
                        )
                    )
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    fun insertAllGoals(goals: Goals) = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    goals.forEach {
                        db.insertGoal(
                            GoalEntity(
                                it.name,
                                it.quantity
                            )
                        )
                    }

                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    fun getGoal(name: String) = flow {
        emit(DataState.Loading)
        try {
            var goal = Goal(name, Constants.GoalEnums[name]!!)
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    val goalEntity = db.getGoal(name)
                    if (goalEntity != null) goal = Goal(goalEntity.name, goalEntity.quantity)
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(goal))
        } catch (exception: Exception) {
            emit(DataState.Success(Goal(name, Constants.GoalEnums[name]!!)))
        }
    }

    fun getAllGoal() = flow {
        emit(DataState.Loading)
        try {
            val goalEntityList = Goals()
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.getAllGoal().forEach() {
                        goalEntityList.add(
                            Goal(
                                it.name,
                                it.quantity
                            )
                        )
                    }
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(goalEntityList))
        } catch (exception: Exception) {
            emit(DataState.Success(Goals()))
        }
    }

    //Fetch Suggestions
    fun fetchSuggestionsFromServer(userId: String) = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.FetchSuggestionsFromServer(userId)
            Log.d("suggestions", "fetchSuggestionsFromServer2: $result")
            emit(DataState.Success(result))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    ///Suggestions
    fun insertSuggestions(suggestionsItem: SuggestionsItem) = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.insertSuggestions(
                        SuggestionsEntity(
                            suggestionsItem.id,
                            suggestionsItem.dateTime,
                            suggestionsItem.heading,
                            suggestionsItem.description,
                            suggestionsItem.isRead,
                            suggestionsItem.isBookmarked
                        )
                    )
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    fun updateSuggestions(suggestionsItem: SuggestionsItem) = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.updateSuggestions(
                        SuggestionsEntity(
                            suggestionsItem.id,
                            suggestionsItem.dateTime,
                            suggestionsItem.heading,
                            suggestionsItem.description,
                            suggestionsItem.isRead,
                            suggestionsItem.isBookmarked
                        )
                    )
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    fun getAllSuggestions() = flow {
        emit(DataState.Loading)
        try {
            val suggestionsList = SuggestionsList()
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.getAllSuggestions().forEach {
                        suggestionsList.add(
                            SuggestionsItem(
                                it.id,
                                it.dateTime,
                                it.heading,
                                it.description,
                                it.isRead,
                                it.isBookmarked
                            )
                        )
                    }
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(suggestionsList))
        } catch (exception: Exception) {
            emit(DataState.Success(SuggestionsList()))
        }
    }

    fun deleteSuggestions(suggestionsItem: SuggestionsItem) = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.deleteSuggestion(
                        SuggestionsEntity(
                            suggestionsItem.id,
                            suggestionsItem.dateTime,
                            suggestionsItem.heading,
                            suggestionsItem.description,
                            suggestionsItem.isRead,
                            suggestionsItem.isBookmarked
                        )
                    )
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    fun deleteAllSuggestions() = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.deleteAllSuggestions()
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    //Fetch Survey
    fun fetchSurveyFromServer() = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.FetchSurveyFromServer()
            Log.d("final test", "fetchSurveyFromServer: $result")
            emit(DataState.Success(result.fetchSurveyResponseData))
        } catch (exception: Exception) {
            //emit(DataState.Error(exception))
            Log.d("##EXCEPTION:", exception.toString())
        }
    }

    fun uploadSurveyAnswer(surveyAnswerUploadModel: SurveyAnswerUploadModel) = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.UploadSurveyAnswer(surveyAnswerUploadModel)
            emit(DataState.Success(result))
        } catch (exception: Exception) {
            Log.d("uploaderror", "uploadSurveyAnswer: $exception")
            //emit(DataState.Error(exception))
        }
    }

    ///Survey
    fun insertSurveyQuestions(surveyQuestions: SurveyQuestions) = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.insertSurveyQuestions(
                        SurveyQuestionsEntity(
                            surveyQuestions.id,
                            surveyQuestions.question,
                            surveyQuestions.questionType,
                            Utils.stringListToString(surveyQuestions.options),
                            Utils.stringListToString(surveyQuestions.answers)
                        )
                    )
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    fun insertSurveyQuestions(surveyQuestionsList: SurveyQuestionsList) = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    surveyQuestionsList.forEach {
                        db.insertSurveyQuestions(
                            SurveyQuestionsEntity(
                                it.id,
                                it.question,
                                it.questionType,
                                Utils.stringListToString(it.options),
                                Utils.stringListToString((it.answers))
                            )
                        )
                    }
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
            Log.e("survey_data_exception", "insertSurveyQuestions: $exception", )
        }
    }

    fun getAllSurveyQuestions() = flow {
        emit(DataState.Loading)
        try {
            val surveyQuestionsList = SurveyQuestionsList()
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.getAllSurveyQuestions().forEach {
                        surveyQuestionsList.add(
                            SurveyQuestions(
                                it.id,
                                it.question,
                                it.questionType,
                                Utils.stringToStringList(it.options),
                                Utils.stringToStringList(it.answers)
                            )
                        )
                    }
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(surveyQuestionsList))
        } catch (exception: Exception) {
            emit(DataState.Success(SuggestionsList()))
        }
    }

    /*fun deleteSurveyQuestion(surveyQuestions: SurveyQuestions) = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.deleteSurveyQuestion(
                        SurveyQuestionsEntity(
                            surveyQuestions.question,
                            surveyQuestions.optionsType,
                            Utils.stringListToString(surveyQuestions.options),
                            Utils.stringListToString(surveyQuestions.answers)
                        )
                    )
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }*/

    fun deleteAllSurveyQuestions() = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.deleteAllSurveyQuestions()
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    /*fun insertSurveyQuestionsAnswerUpload(surveyQuestions: SurveyQuestions) = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.insertSurveyQuestionsAnswer(
                        SurveyQuestionsAnswerUploadEntity(
                            surveyQuestions.question,
                            surveyQuestions.optionsType,
                            Utils.stringListToString(surveyQuestions.options),
                            Utils.stringListToString(surveyQuestions.answers),
                            false
                        )
                    )
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }*/

    /*fun insertSurveyQuestionsAnswerUpload(surveyQuestionsList: SurveyQuestionsList) = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    surveyQuestionsList.forEach {
                        db.insertSurveyQuestionsAnswer(
                            SurveyQuestionsAnswerUploadEntity(
                                it.question,
                                it.optionsType,
                                Utils.stringListToString(it.options),
                                Utils.stringListToString(it.answers),
                                false
                            )
                        )
                    }
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }*/

    /*fun getAllSurveyQuestionsAnswer() = flow {
        emit(DataState.Loading)
        try {
            val surveyQuestionsList = SurveyQuestionsList()
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.getAllSurveyQuestionsAnswer().forEach {
                        surveyQuestionsList.add(
                            SurveyQuestions(
                                it.question,
                                it.questionType,
                                Utils.stringToStringList(it.options),
                                Utils.stringToStringList(it.answers)
                            )
                        )
                    }
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(surveyQuestionsList))
        } catch (exception: Exception) {
            emit(DataState.Success(SuggestionsList()))
        }
    }*/

    /*fun deleteSurveyQuestionAnswer(surveyQuestions: SurveyQuestions) = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.deleteSurveyQuestionAnswer(
                        SurveyQuestionsAnswerUploadEntity(
                            surveyQuestions.question,
                            surveyQuestions.questionType,
                            Utils.stringListToString(surveyQuestions.options),
                            Utils.stringListToString(surveyQuestions.answers),
                            false
                        )
                    )
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }*/

    fun deleteAllSurveyQuestionsAnswer() = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.deleteAllSurveyQuestionsAnswer()
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(true))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    // fetch projects
    fun fetchProjectsFromServer() = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.FetchProjectsFromServer()
            Log.d("final test", "fetchSurveyFromServer: $result")
            emit(DataState.Success(result.dataProjectList))
        } catch (exception: Exception) {
            //emit(DataState.Error(exception))
        }
    }

}