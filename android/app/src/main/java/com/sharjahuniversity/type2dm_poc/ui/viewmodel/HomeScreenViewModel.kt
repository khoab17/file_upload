package com.sharjahuniversity.type2dm_poc.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharjahuniversity.type2dm_poc.data.model.*
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.FetchUserGoalsResponse
import com.sharjahuniversity.type2dm_poc.data.repository.Type2DMRepository
import com.sharjahuniversity.type2dm_poc.utils.DataState
import com.sharjahuniversity.type2dm_poc.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: Type2DMRepository
) : ViewModel() {
    private var _fetchedGoalsResponse = MutableLiveData<DataState<FetchUserGoalsResponse>>()
    val fetchedGoalsResponse: LiveData<DataState<FetchUserGoalsResponse>> get() = _fetchedGoalsResponse
    private var _calorie = MutableLiveData<DataState<Float>>()
    val calorie: LiveData<DataState<Float>> get() = _calorie

    private var _burntCal = MutableLiveData<DataState<Float>>()
    val burntCal: LiveData<DataState<Float>> get() = _burntCal

    private var _isGoalUpdated = MutableLiveData<DataState<Boolean>>()
    val isGoalUpdated: LiveData<DataState<Boolean>> get() = _isGoalUpdated
    private var _goals = MutableLiveData<DataState<Goals>>()
    val goals: LiveData<DataState<Goals>> get() = _goals


    fun fetchAllGoals(userId: String){
        viewModelScope.launch {
            repository.fetchGoal(userId)
                .onEach {
                    _fetchedGoalsResponse.value = it
                }
                .launchIn(viewModelScope)
        }
    }

    fun setGoal(goal: Goal) {
        viewModelScope.launch {
            repository.insertGoal(goal)
                .onEach {
                    _isGoalUpdated.value = it
                }
                .launchIn(viewModelScope)
        }
    }

    fun getAllGoal() {
        viewModelScope.launch {
            repository.getAllGoal()
                .onEach {
                    _goals.value = it
                }
                .launchIn(viewModelScope)
        }
    }

    fun getAll() {
        getAllGoal()
    }
}