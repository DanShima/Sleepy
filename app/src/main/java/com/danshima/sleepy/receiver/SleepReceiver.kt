package com.danshima.sleepy.receiver

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.SleepClassifyEvent
import com.google.android.gms.location.SleepSegmentEvent

class SleepReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (SleepSegmentEvent.hasEvents(intent)) {
            val events = SleepSegmentEvent.extractEvents(intent)
            Log.d(TAG, "Logging SleepSegmentEvents")
            for (event in events) {
                Log.d(TAG, "${event.startTimeMillis} to ${event.endTimeMillis} with status ${event.status}")
            }

        } else if (SleepClassifyEvent.hasEvents(intent)) {
            val events = SleepClassifyEvent.extractEvents(intent)
            Log.d(TAG, "Logging SleepClassifyEvents")
            for (event in events) {
                Log.d(TAG, "Confidence: ${event.confidence} - Light: ${event.light} - Motion: ${event.motion}")
            }

        }

    }

    companion object {
        private const val TAG = "SLEEP_RECEIVER"
        fun createPendingIntent(context: Context): PendingIntent {
            val intent = Intent(context, SleepReceiver::class.java)
            return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        }

    }

}