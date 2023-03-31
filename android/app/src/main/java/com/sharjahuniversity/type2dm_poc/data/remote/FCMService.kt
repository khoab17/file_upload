package com.sharjahuniversity.type2dm_poc.data.remote

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.ui.view.MainActivity
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.FCMViewModel
import com.sharjahuniversity.type2dm_poc.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FCMService : FirebaseMessagingService() {
    @Inject
    lateinit var fcmViewModel: FCMViewModel

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Utils.getUserId(applicationContext)?.let { fcmViewModel.uploadUserFCMToken(token, it) }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        remoteMessage.notification?.apply {
            val title: String = title!!
            val content: String = body!!
            Log.d("FCM", "onMessageReceived: \ntitle: $title \ncontent: $content\n")

            /*when (title) {
                NotificationTitle.NewSuggestions.value -> LocalBroadcastManager.getInstance(applicationContext)
                    .sendBroadcast(Intent(Constants.ACTION_NEW_SUGGESTIONS))
                NotificationTitle.NewSurvey.value -> LocalBroadcastManager.getInstance(applicationContext)
                    .sendBroadcast(Intent(Constants.ACTION_NEW_SURVEY))
                NotificationTitle.NewGoalsSet.value -> LocalBroadcastManager.getInstance(applicationContext)
                    .sendBroadcast(Intent(Constants.ACTION_NEW_GOALS_SET))
            }*/
        }
        remoteMessage.data.apply {
            Log.d("FCM_Data", "onMessageReceived: ${this.entries}")
            val title: String = this[NotificationDataKey.Title.value] ?: ""
            val body: String = this[NotificationDataKey.Body.value] ?: ""
            var notificationItem: NotificationItem = NotificationItem.Others

            when (this[NotificationDataKey.Action.value]) {
                NotificationDataAction.NewSuggestions.value -> {
                    notificationItem = NotificationItem.NewSuggestion
                    LocalBroadcastManager.getInstance(
                        applicationContext
                    ).sendBroadcast(Intent(Constants.ACTION_NEW_SUGGESTIONS))
                    Utils.setHasNewSuggestion(applicationContext, true)
                }
                NotificationDataAction.NewSurvey.value -> {
                    notificationItem = NotificationItem.NewSurvey
                    LocalBroadcastManager.getInstance(
                        applicationContext
                    ).sendBroadcast(Intent(Constants.ACTION_NEW_SURVEY))
                    Utils.setHasNewSurvey(applicationContext, true)
                }
                NotificationDataAction.NewGoalsSet.value -> {
                    notificationItem = NotificationItem.NewGoalsSet
                    LocalBroadcastManager.getInstance(
                        applicationContext
                    ).sendBroadcast(Intent(Constants.ACTION_NEW_GOALS_SET))
                    Utils.setHasNewGoalsSet(applicationContext, true)
                }
            }
            sendNotification(title, body, notificationItem)
        }
    }

    private fun sendNotification(title: String, body: String, notificationItem: NotificationItem) {
        val pattern = longArrayOf(0, 1000, 500, 1000)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        var notificationChannel = NotificationChannel(
            notificationItem.channelID,
            notificationItem.channelName,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = ""
            enableLights(true)
            enableVibration(true)
            vibrationPattern = pattern
        }

        notificationManager.createNotificationChannel(notificationChannel)
        notificationChannel = notificationManager.getNotificationChannel(notificationItem.channelID)
        notificationChannel.canBypassDnd()

        val notificationIntent = Intent(applicationContext, MainActivity::class.java)
        val notificationPendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            notificationIntent,
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S)
                PendingIntent.FLAG_MUTABLE
            else
                PendingIntent.FLAG_ONE_SHOT
        )

        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, notificationItem.channelID)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(notificationPendingIntent)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_heartbeat)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        applicationContext.resources,
                        R.drawable.ic_townhall
                    )
                )
        notificationManager.notify(notificationItem.id, notificationBuilder.build())
    }
}