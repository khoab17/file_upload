package com.sharjahuniversity.type2dm_poc.utils.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sharjahuniversity.type2dm_poc.data.repository.Type2DMRepository
import com.sharjahuniversity.type2dm_poc.utils.Utils
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DataUploadWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters,
    type2DMRepository: Type2DMRepository
) :
    CoroutineWorker(context, workerParams) {
    //private val dataUploadWorkerViewModel = DataUploadWorkerViewModel(type2DMRepository)
    private val userId = Utils.getUserId(context)

    override suspend fun doWork(): Result {
        return try {
//            dataUploadWorkerViewModel.apply {
//                uploadDailyCalorieIntake(userId!!)
//                uploadDailyExercise(userId!!)
//                uploadDailyWaterIntake(userId!!)
//                uploadDailySteps(userId!!)
//                uploadDailySleep(userId!!)
//                //uploadDailyWeight(userId!!)
//            }
            Result.success()
        } catch (exception: Exception) {
            Result.retry()
        }
    }
}