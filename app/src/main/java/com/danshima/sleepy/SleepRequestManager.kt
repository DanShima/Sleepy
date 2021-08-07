package com.danshima.sleepy

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.danshima.sleepy.receiver.SleepReceiver
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.SleepSegmentRequest

/**
 * manage subscribing and unsubscribing from the sleep data update.
 */
class SleepRequestsManager(private val context: Context) {
    private val sleepReceiverPendingIntent by lazy {
        SleepReceiver.createPendingIntent(context)
    }

    fun requestSleepUpdates(requestPermission: () -> Unit = {}) {
        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACTIVITY_RECOGNITION) ==
            PackageManager.PERMISSION_GRANTED) {
            subscribeToSleepUpdates()
        } else {
            requestPermission()
        }
    }

    fun subscribeToSleepUpdates() {
        ActivityRecognition.getClient(context)
            .requestSleepSegmentUpdates (sleepReceiverPendingIntent,
                SleepSegmentRequest.getDefaultSleepSegmentRequest())

    }

    fun unsubscribeFromSleepUpdates() {
        ActivityRecognition.getClient(context)
            .removeSleepSegmentUpdates(sleepReceiverPendingIntent)
    }


}
