package com.sharjahuniversity.type2dm_poc.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharjahuniversity.type2dm_poc.data.local.room.entities.SuggestionsEntity
import com.sharjahuniversity.type2dm_poc.data.model.SuggestionsItem
import com.sharjahuniversity.type2dm_poc.data.model.SuggestionsList
import com.sharjahuniversity.type2dm_poc.data.model.SurveyQuestionsList
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.UserDataUploadResponse
import com.sharjahuniversity.type2dm_poc.data.repository.Type2DMRepository
import com.sharjahuniversity.type2dm_poc.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class FCMViewModel @Inject constructor(
    private val repository: Type2DMRepository
) : ViewModel() {
    private var _suggestions = MutableLiveData<DataState<SuggestionsList>>()
    val suggestions: LiveData<DataState<SuggestionsList>> get() = _suggestions
    private var _isDBUpdated = MutableLiveData<DataState<Boolean>>()
    val isDBUpdated: LiveData<DataState<Boolean>> get() = _isDBUpdated
    private val _userDataUploadResponse = MutableLiveData<DataState<UserDataUploadResponse>>()
    val userDataUploadResponse: LiveData<DataState<UserDataUploadResponse>> get() = _userDataUploadResponse

    fun uploadUserFCMToken(token: String, userId: String) {
        viewModelScope.launch {
            repository.uploadUserFCMToken(userId, token).onEach {
                _userDataUploadResponse.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun getSuggestions() {
        viewModelScope.launch {
            repository.getAllSuggestions()
                .onEach {
                    _suggestions.value = it
                }.launchIn(viewModelScope)
        }
    }

    fun insertSuggestions(suggestionsItem: SuggestionsItem) {
        Log.d("FCM", "insertSuggestions: $suggestionsItem")
        viewModelScope.launch {
            repository.insertSuggestions(suggestionsItem)
                .onEach {
                    _isDBUpdated.value = DataState.Success(true)
                }.launchIn(viewModelScope)
        }
    }

    fun deleteSuggestions(suggestionsItem: SuggestionsItem) {
        viewModelScope.launch {
            repository.deleteSuggestions(suggestionsItem)
                .onEach {
                    _isDBUpdated.value = DataState.Success(true)
                }.launchIn(viewModelScope)
        }
    }

    fun deleteAllSuggestions() {
        viewModelScope.launch {
            repository.deleteAllSuggestions()
                .onEach {
                    _isDBUpdated.value = DataState.Success(true)
                }.launchIn(viewModelScope)
        }
    }

    fun insertSurveyQuestions(surveyQuestionsList: SurveyQuestionsList) {
        viewModelScope.launch {
            repository.insertSurveyQuestions(surveyQuestionsList)
                .onEach {
                    //_isDBUpdated.value = DataState.Success(true)
                }.launchIn(viewModelScope)
        }
    }
}