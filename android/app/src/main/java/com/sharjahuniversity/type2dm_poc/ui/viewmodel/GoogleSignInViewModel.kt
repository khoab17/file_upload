package com.sharjahuniversity.type2dm_poc.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.AuthResponse
import com.sharjahuniversity.type2dm_poc.data.repository.UserProfileDataRepository
import com.sharjahuniversity.type2dm_poc.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoogleSignInViewModel @Inject constructor(private val repository: UserProfileDataRepository) :
    ViewModel() {
    private val _authResponse = MutableLiveData<DataState<AuthResponse>>()
    val authResponse: LiveData<DataState<AuthResponse>> get() = _authResponse

    fun uploadUserGoogleTokenId(token: String) {
        Log.d("token", "uploadUserGoogleTokenId: $token")
        viewModelScope.launch {
            repository.uploadUserGoogleTokenId(token).onEach {
                Log.d("token", "uploadUserGoogleTokenId: $it")
                _authResponse.value = it
            }.launchIn(viewModelScope)
        }
    }
}