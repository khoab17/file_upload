package com.sharjahuniversity.type2dm_poc.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.DataProjectList
import com.sharjahuniversity.type2dm_poc.data.repository.Type2DMRepository
import com.sharjahuniversity.type2dm_poc.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectListViewModel @Inject constructor(
    private val repository: Type2DMRepository
) : ViewModel() {
    private var _projectsResponse = MutableLiveData<DataState<DataProjectList>>()
    val projectsResponse: LiveData<DataState<DataProjectList>> get() = _projectsResponse

    fun fetchProjectsFromServer() {
        viewModelScope.launch {
            repository.fetchProjectsFromServer()
                .onEach {
                    _projectsResponse.value = it
                }.launchIn(viewModelScope)
        }
    }
}