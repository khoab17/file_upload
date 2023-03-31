package com.sharjahuniversity.type2dm_poc.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharjahuniversity.type2dm_poc.data.model.UserProfileData
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.FetchUserResponse
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.UserDataUploadResponse
import com.sharjahuniversity.type2dm_poc.data.repository.UserProfileDataRepository
import com.sharjahuniversity.type2dm_poc.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PersonalDetailsInputScreenViewModel @Inject constructor(private val repository: UserProfileDataRepository) :
    ViewModel() {
    private val _userDataUploadResponse = MutableLiveData<DataState<UserDataUploadResponse>>()
    val userDataUploadResponse: LiveData<DataState<UserDataUploadResponse>> get() = _userDataUploadResponse
    private val _fetchUserResponse = MutableLiveData<DataState<FetchUserResponse>>()
    val fetchUserResponse: LiveData<DataState<FetchUserResponse>> get() = _fetchUserResponse
    private val _userFCMTokenUploadResponse = MutableLiveData<DataState<UserDataUploadResponse>>()
    val userFCMTokenUploadResponse: LiveData<DataState<UserDataUploadResponse>> get() = _userFCMTokenUploadResponse

    fun uploadUser(user: UserProfileData, userId: String) {
        viewModelScope.launch {
            repository.uploadUserData(userId, user).onEach {
                Timber.d("uploadUser: $it")
                _userDataUploadResponse.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun uploadUserFCMToken(token: String, userId: String) {
        viewModelScope.launch {
            repository.uploadUserFCMToken(userId, token).onEach {
                _userFCMTokenUploadResponse.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun fetchUser(userId: String) {
        viewModelScope.launch {
            repository.fetchUser(userId).onEach {
                _fetchUserResponse.value = it
            }.launchIn(viewModelScope)
        }
    }
}