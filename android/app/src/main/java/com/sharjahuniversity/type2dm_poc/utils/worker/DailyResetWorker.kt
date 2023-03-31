package com.sharjahuniversity.type2dm_poc.utils.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sharjahuniversity.type2dm_poc.data.repository.Type2DMRepository
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.DailyResetWorkerViewModel

import com.sharjahuniversity.type2dm_poc.utils.Utils
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DailyResetWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters,
    type2DMRepository: Type2DMRepository
) :
    CoroutineWorker(context, workerParams) {
    private val dailyResetWorkerViewModel = DailyResetWorkerViewModel(type2DMRepository)

    override suspend fun doWork(): Result {
        return try {
            dailyResetWorkerViewModel.apply {

                Log.d("DailyResetWorker", "doWork: Complete")
            }
            Result.success()
        } catch (exception: Exception) {
            Result.retry()
        }
    }
}