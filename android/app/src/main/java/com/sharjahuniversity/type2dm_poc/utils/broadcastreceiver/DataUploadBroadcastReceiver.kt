package com.sharjahuniversity.type2dm_poc.utils.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.*
import com.sharjahuniversity.type2dm_poc.utils.Constants
import com.sharjahuniversity.type2dm_poc.utils.worker.DataUploadWorker

class DataUploadBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Constants.ACTION_UPLOAD_NEW_DATA) {
            Log.d("DataUploadBroadcastReceiver", "onReceive: $intent")
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val dataUploadWorkRequest = OneTimeWorkRequest.Builder(DataUploadWorker::class.java)
                .addTag(Constants.DATA_UPLOAD_WORK_TAG)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    Constants.DATA_UPLOAD_WORK_TAG,
                    ExistingWorkPolicy.REPLACE,
                    dataUploadWorkRequest
                )
        }
    }
}