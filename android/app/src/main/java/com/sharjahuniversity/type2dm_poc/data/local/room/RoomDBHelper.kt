package com.sharjahuniversity.type2dm_poc.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sharjahuniversity.type2dm_poc.data.local.room.entities.*


//Room database helper
@Database(
    entities = [
        FetchedUserProfileDataEntity::class,
        GoalEntity::class,
        SuggestionsEntity::class,
        SurveyQuestionsEntity::class,
        SurveyQuestionsAnswerUploadEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun type2DMDao(): Type2DMDao
}
