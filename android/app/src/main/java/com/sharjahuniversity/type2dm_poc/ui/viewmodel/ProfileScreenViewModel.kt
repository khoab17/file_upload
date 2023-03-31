package com.sharjahuniversity.type2dm_poc.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharjahuniversity.type2dm_poc.data.model.FetchedUserProfileData
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.FetchUserResponse
import com.sharjahuniversity.type2dm_poc.data.repository.UserProfileDataRepository
import com.sharjahuniversity.type2dm_poc.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val repository: UserProfileDataRepository,
) :
    ViewModel() {
    private val _fetchedUserResponseModel = MutableLiveData<DataState<FetchUserResponse>>()
    val fetchedUserResponseModel: LiveData<DataState<FetchUserResponse>> get() = _fetchedUserResponseModel
    private val _storedData = MutableLiveData<DataState<Boolean>>()
    val storedData: LiveData<DataState<Boolean>> get() = _storedData
    private val _fetchedUserProfileData = MutableLiveData<DataState<FetchedUserProfileData>>()
    val fetchedUserProfileData: LiveData<DataState<FetchedUserProfileData>> get() = _fetchedUserProfileData

    fun fetchUser(userId: String) {
        viewModelScope.launch {
            repository.fetchUser(userId).onEach {
                _fetchedUserResponseModel.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun storeFetchedUserIntoLocalDB(user: FetchedUserProfileData) {
        viewModelScope.launch {
            repository.storeFetchedUserIntoLocalDB(user).onCompletion {
                _storedData.value = DataState.Success(true)
            }.launchIn(viewModelScope)
        }
    }

    fun getUser() {
        viewModelScope.launch {
            repository.getFetchedUserFromLocalDB().onEach {
                _fetchedUserProfileData.value = it
            }.launchIn(viewModelScope)
        }
    }
}