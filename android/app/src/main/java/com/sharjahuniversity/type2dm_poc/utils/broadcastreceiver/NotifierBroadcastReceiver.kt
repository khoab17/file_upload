package com.sharjahuniversity.type2dm_poc.utils.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sharjahuniversity.type2dm_poc.ui.view.hasNewGoalsSet
import com.sharjahuniversity.type2dm_poc.ui.view.hasNewSuggestions
import com.sharjahuniversity.type2dm_poc.ui.view.hasNewSurvey
import com.sharjahuniversity.type2dm_poc.utils.Constants

class NotifierBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Constants.ACTION_NEW_SUGGESTIONS -> {
                hasNewSuggestions = true
            }
            Constants.ACTION_NEW_SURVEY -> {
                hasNewSurvey = true
            }
            Constants.ACTION_NEW_GOALS_SET -> {
                hasNewGoalsSet = true
            }
        }
    }
}