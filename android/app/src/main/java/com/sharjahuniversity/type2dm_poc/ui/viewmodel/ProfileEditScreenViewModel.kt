package com.sharjahuniversity.type2dm_poc.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharjahuniversity.type2dm_poc.data.model.UserProfileData
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.UserDataUploadResponse
import com.sharjahuniversity.type2dm_poc.data.repository.UserProfileDataRepository
import com.sharjahuniversity.type2dm_poc.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEditScreenViewModel @Inject constructor(private val repository: UserProfileDataRepository) :
    ViewModel() {
    private val _userDataUploadResponse = MutableLiveData<DataState<UserDataUploadResponse>>()
    val userDataUploadResponse: LiveData<DataState<UserDataUploadResponse>> get() = _userDataUploadResponse

    fun uploadUser(user: UserProfileData, userId: String) {
        viewModelScope.launch {
            repository.uploadUserData(userId, user).onEach {
                _userDataUploadResponse.value = it
            }.launchIn(viewModelScope)
        }
    }
}