package com.sharjahuniversity.type2dm_poc.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharjahuniversity.type2dm_poc.data.repository.Type2DMRepository
import com.sharjahuniversity.type2dm_poc.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyResetWorkerViewModel @Inject constructor(
    private val repository: Type2DMRepository
) : ViewModel() {

}