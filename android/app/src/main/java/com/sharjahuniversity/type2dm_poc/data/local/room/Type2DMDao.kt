package com.sharjahuniversity.type2dm_poc.data.local.room

import androidx.room.*
import com.sharjahuniversity.type2dm_poc.data.local.room.entities.*

@Dao
interface Type2DMDao {
    //UserProfileData
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFetchedUserData(vararg fetchedUserProfileDataEntity: FetchedUserProfileDataEntity)

    @Query("SELECT * FROM FetchedUserProfileDataEntity")
    fun getUserProfileData(): FetchedUserProfileDataEntity

    ///Goals
    @Query("SELECT * FROM GoalEntity")
    fun getAllGoal(): List<GoalEntity>

    @Query("SELECT * FROM GoalEntity WHERE name = :name")
    fun getGoal(name: String): GoalEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGoal(vararg goalEntity: GoalEntity)

    @Delete
    fun deleteGoal(goalEntity: GoalEntity)

    ///Suggestions
    @Query("SELECT * FROM SuggestionsEntity ORDER BY date_time DESC")
    fun getAllSuggestions(): List<SuggestionsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSuggestions(vararg suggestionsEntity: SuggestionsEntity)

    @Update
    fun updateSuggestions(vararg suggestionsEntity: SuggestionsEntity)

    @Delete
    fun deleteSuggestion(suggestionsEntity: SuggestionsEntity)

    @Query("DELETE FROM SuggestionsEntity")
    fun deleteAllSuggestions()

    ///Surveys
    @Query("SELECT * FROM SurveyQuestionsEntity")
    fun getAllSurveyQuestions(): List<SurveyQuestionsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSurveyQuestions(vararg surveyQuestionsEntity: SurveyQuestionsEntity)

    @Delete
    fun deleteSurveyQuestion(surveyQuestionsEntity: SurveyQuestionsEntity)

    @Query("DELETE FROM SurveyQuestionsEntity")
    fun deleteAllSurveyQuestions()

    @Query("SELECT * FROM SurveyQuestionsAnswerUploadEntity")
    fun getAllSurveyQuestionsAnswer(): List<SurveyQuestionsAnswerUploadEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSurveyQuestionsAnswer(vararg surveyQuestionsAnswerUploadEntity: SurveyQuestionsAnswerUploadEntity)

    @Delete
    fun deleteSurveyQuestionAnswer(surveyQuestionsAnswerUploadEntity: SurveyQuestionsAnswerUploadEntity)

    @Query("DELETE FROM SurveyQuestionsAnswerUploadEntity")
    fun deleteAllSurveyQuestionsAnswer()
}
