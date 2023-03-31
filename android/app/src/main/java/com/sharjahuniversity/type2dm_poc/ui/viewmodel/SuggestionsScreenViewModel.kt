package com.sharjahuniversity.type2dm_poc.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharjahuniversity.type2dm_poc.data.model.SuggestionsItem
import com.sharjahuniversity.type2dm_poc.data.model.SuggestionsList
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.FetchSuggestionsResponse
import com.sharjahuniversity.type2dm_poc.data.repository.Type2DMRepository
import com.sharjahuniversity.type2dm_poc.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionsScreenViewModel @Inject constructor(
    private val repository: Type2DMRepository
) : ViewModel() {
    private var _suggestionsResponse = MutableLiveData<DataState<FetchSuggestionsResponse>>()
    val suggestionsResponse: LiveData<DataState<FetchSuggestionsResponse>> get() = _suggestionsResponse
    private var _suggestions = MutableLiveData<DataState<SuggestionsList>>()
    val suggestions: LiveData<DataState<SuggestionsList>> get() = _suggestions
    private var _isDBUpdated = MutableLiveData<DataState<Boolean>>()
    val isDBUpdated: LiveData<DataState<Boolean>> get() = _isDBUpdated

    fun fetchSuggestionsFromServer(userId: String) {
        viewModelScope.launch {
            repository.fetchSuggestionsFromServer(userId)
                .onEach {
                    Log.d("suggestions", "fetchSuggestionsFromServer: $it")
                    _suggestionsResponse.value = it
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
        viewModelScope.launch {
            repository.insertSuggestions(suggestionsItem)
                .onEach {
                    _isDBUpdated.value = DataState.Success(true)
                }.launchIn(viewModelScope)
        }
    }

    fun updateSuggestions(suggestionsItem: SuggestionsItem) {
        viewModelScope.launch {
            repository.updateSuggestions(suggestionsItem)
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
}