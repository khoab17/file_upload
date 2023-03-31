package com.sharjahuniversity.type2dm_poc.data.repository

import com.sharjahuniversity.type2dm_poc.data.local.room.Type2DMDao
import com.sharjahuniversity.type2dm_poc.data.local.room.entities.FetchedUserProfileDataEntity
import com.sharjahuniversity.type2dm_poc.data.model.FetchedUserProfileData
import com.sharjahuniversity.type2dm_poc.data.model.UserProfileData
import com.sharjahuniversity.type2dm_poc.data.model.uploadModel.CredentialToPost
import com.sharjahuniversity.type2dm_poc.data.model.uploadModel.FCMTokenToPost
import com.sharjahuniversity.type2dm_poc.data.model.uploadModel.UserProfileDataToUpload
import com.sharjahuniversity.type2dm_poc.data.remote.APIService
import com.sharjahuniversity.type2dm_poc.utils.DataState
import com.sharjahuniversity.type2dm_poc.utils.Utils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserProfileDataRepository @Inject constructor(
    private val apiService: APIService,
    private val db: Type2DMDao
) {
    fun uploadUserData(userId: String, userProfileData: UserProfileData) = flow {
        emit(DataState.Loading)
        try {
            val data = UserProfileDataToUpload(
                name = userProfileData.name,
                email = userProfileData.email,
                surname = userProfileData.surname,
                phone = userProfileData.phone,
                gender = userProfileData.gender,
                age = userProfileData.age,
                education_level = userProfileData.education_level,
                income = userProfileData.income,
                occupation = userProfileData.occupation,
                organization = userProfileData.organization,
                status = userProfileData.status
//                date_of_birth = Utils.convertStringDateToUploadFormat(userProfileData.DOB),
//                weight = userProfileData.weightKG.toFloat(),
//                height = userProfileData.heightCM.toFloat(),
                //weight_goal = userProfileData.weightGoal.toFloat()
            )
            val result = apiService.UploadUserProfileData(userId, data)
            emit(DataState.Success(result))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    fun uploadUserFCMToken(userId: String, token: String) = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.UploadUserFCMToken(userId, FCMTokenToPost(token))
            emit(DataState.Success(result))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    fun fetchUser(userId: String) = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.FetchUser(userId)
            emit(DataState.Success(result))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    fun storeFetchedUserIntoLocalDB(user: FetchedUserProfileData) = flow {
        emit(DataState.Loading)
        try {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    db.insertFetchedUserData(
                        FetchedUserProfileDataEntity(
                            id = user.id,
                            name = user.name,
                            email = user.email,
                            imageUrl = user.imageUrl,
                            phone = user.phone,
                            studentId = user.studentId,
                            gender = user.gender,
                            age = user.age,
                            income = user.income,
                            occupation = user.occupation,
                            surname = user.surname,
                            educationLevel = user.education_level,
                            organization = user.organization
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

    fun getFetchedUserFromLocalDB() = flow {
        emit(DataState.Loading)
        try {
            var data = FetchedUserProfileData(
                email = "email",
                id = "id",
                imageUrl = "imageUrl",
                name = "name",
                phone = "phone",
                studentId = "studentId",
                surname = "surname",
                age = 0.0,
                education_level = "ed_level",
                organization = "organization",
                occupation = "occupation",
                income = 0.0,
                gender = "gender"
            )
            val job = CoroutineScope(Dispatchers.IO).launch {
                val task = async {
                    val entity = db.getUserProfileData()
                    if (entity!=null)data = FetchedUserProfileData(
                        email = entity.email,
                        id = entity.id,
                        imageUrl = entity.imageUrl,
                        name = entity.name,
                        phone = entity.phone,
                        studentId = entity.studentId,
                        gender = entity.gender,
                        income = entity.income,
                        occupation = entity.occupation,
                        organization = entity.organization,
                        education_level = entity.educationLevel,
                        age = entity.age,
                        surname = entity.surname
                    )
                }
                task.await()
            }
            while (job.isActive) delay(100)
            emit(DataState.Success(data))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }

    fun uploadUserGoogleTokenId(token: String) = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.UploadUserGoogleTokenId(
                CredentialToPost(token)
            )
            emit(DataState.Success(result))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }
}