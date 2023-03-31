package com.sharjahuniversity.type2dm_poc.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharjahuniversity.type2dm_poc.data.model.SurveyQuestions
import com.sharjahuniversity.type2dm_poc.data.model.SurveyQuestionsList
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.FetchSurveyResponseData
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.UploadSurveyAnswerResponse
import com.sharjahuniversity.type2dm_poc.data.model.uploadModel.SurveyAnswerUploadModel
import com.sharjahuniversity.type2dm_poc.data.repository.Type2DMRepository
import com.sharjahuniversity.type2dm_poc.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurveyScreenViewModel @Inject constructor(
    private val repository: Type2DMRepository
) : ViewModel() {
    private var _surveyResponse = MutableLiveData<DataState<FetchSurveyResponseData>>()
    val surveyResponse: LiveData<DataState<FetchSurveyResponseData>> get() = _surveyResponse
    private var _uploadResponse = MutableLiveData<DataState<UploadSurveyAnswerResponse>>()
    val uploadResponse: LiveData<DataState<UploadSurveyAnswerResponse>> get() = _uploadResponse
    private var _isDBUpdated = MutableLiveData<DataState<Boolean>>()
    val isDBUpdated: LiveData<DataState<Boolean>> get() = _isDBUpdated

    var projectIdSelected: String = ""
    var projectNameSelected: String = ""

    fun fetchSurveyFromServer() {
        viewModelScope.launch {
            repository.fetchSurveyFromServer()
                .onEach {
                    _surveyResponse.value = it
                }.launchIn(viewModelScope)
        }
    }

    fun insertSurveyQuestions(surveyQuestionsList: SurveyQuestionsList) {
        viewModelScope.launch {
            repository.insertSurveyQuestions(surveyQuestionsList)
                .onEach {
                    _isDBUpdated.value = DataState.Success(true)
                }.launchIn(viewModelScope)
        }
    }

    fun uploadSurveyAnswer(surveyAnswerUploadModel: SurveyAnswerUploadModel) {
        viewModelScope.launch {
            repository.uploadSurveyAnswer(surveyAnswerUploadModel)
                .onEach {
                    _uploadResponse.value = it
                }.launchIn(viewModelScope)
        }
    }

    fun getSurveyQuestions() {
        viewModelScope.launch {
            repository.getAllSurveyQuestions()
                .onEach {
                    //_surveyQuestions.value = it as DataState<SurveyQuestionsList>
                }.launchIn(viewModelScope)
        }
    }

    /*fun insertSurveyQuestionsAnswersList(surveyQuestionsList: SurveyQuestionsList) {
        viewModelScope.launch {
            repository.insertSurveyQuestionsAnswerUpload(surveyQuestionsList)
                .onEach {
                    //_isDBUpdated.value = DataState.Success(true)
                }.launchIn(viewModelScope)
        }
    }*/

    /*fun deleteSurveyQuestions(surveyQuestions: SurveyQuestions) {
        viewModelScope.launch {
            repository.deleteSurveyQuestion(surveyQuestions)
                .onEach {
                    //_isDBUpdated.value = DataState.Success(true)
                }.launchIn(viewModelScope)
        }
    }*/

    fun deleteAllSurveyQuestions() {
        viewModelScope.launch {
            repository.deleteAllSurveyQuestions()
                .onEach {
                    //_isDBUpdated.value = DataState.Success(true)
                }.launchIn(viewModelScope)
        }
    }
}