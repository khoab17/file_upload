package com.sharjahuniversity.type2dm_poc.data.remote

import com.sharjahuniversity.type2dm_poc.data.model.responseModel.*
import com.sharjahuniversity.type2dm_poc.data.model.uploadModel.*
import com.sharjahuniversity.type2dm_poc.data.remote.APIUrls.PathIdValue
import retrofit2.http.*

interface APIService {
    @PUT(APIUrls.UpdateUser)
    suspend fun UploadUserProfileData(
        @Path(PathIdValue) id: String,
        @Body userProfileDataToUpload: UserProfileDataToUpload
    ): UserDataUploadResponse

    @PUT(APIUrls.UpdateUser)
    suspend fun UploadUserFCMToken(
        @Path(PathIdValue) id: String,
        @Body token: FCMTokenToPost
    ): UserDataUploadResponse

    @GET(APIUrls.User)
    suspend fun FetchUser(@Path(PathIdValue) id: String): FetchUserResponse

    @GET(APIUrls.User)
    suspend fun FetchUserGoals(@Path(PathIdValue) id: String): FetchUserGoalsResponse

    @POST(APIUrls.AuthUserSignUpGoogle)
    suspend fun UploadUserGoogleTokenId(@Body token: CredentialToPost): AuthResponse

    @GET(APIUrls.Foods)
    suspend fun FetchFoodsFromServer(): FetchFoodsResponse

    @GET(APIUrls.Exercises)
    suspend fun FetchExercisesFromServer(): FetchExercisesResponse

    @POST(APIUrls.Days)
    suspend fun UploadDailyData(@Body data: DailyCalorieIntakeToUploadModel): DailyDataUploadResponse

    @POST(APIUrls.Days)
    suspend fun UploadDailyData(@Body data: DailyExerciseToUploadModel): DailyDataUploadResponse

    @POST(APIUrls.Days)
    suspend fun UploadDailyData(@Body data: DailyWaterToUploadModel): DailyDataUploadResponse

    @POST(APIUrls.Days)
    suspend fun UploadDailyData(@Body data: DailyStepsToUploadModel): DailyDataUploadResponse

    @POST(APIUrls.Days)
    suspend fun UploadDailyData(@Body data: DailySleepToUploadModel): DailyDataUploadResponse

    @POST(APIUrls.Days)
    suspend fun UploadDailyData(@Body data: DailyWeightToUploadModel): DailyDataUploadResponse

    @GET(APIUrls.Suggestions)
    suspend fun FetchSuggestionsFromServer(@Path(PathIdValue) id: String): FetchSuggestionsResponse

    @GET(APIUrls.Survey)
    suspend fun FetchSurveyFromServer(): FetchSurveyResponse

    @PUT(APIUrls.UploadSurveyAnswer)
    suspend fun UploadSurveyAnswer(@Body data: SurveyAnswerUploadModel): UploadSurveyAnswerResponse

    @GET(APIUrls.Projects)
    suspend fun FetchProjectsFromServer(): FetchAllProjects
}