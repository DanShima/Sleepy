package com.danshima.sleepy.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.danshima.sleepy.SleepRequestsManager

/**
 * listen to events after the device reboots
 */
class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val sleepRequestManager = SleepRequestsManager(context)

        sleepRequestManager.requestSleepUpdates(requestPermission = {
            Log.d(TAG, "Permission to listen for Sleep Activity has been removed")
        })
    }


    companion object {
        private const val TAG = "SLEEP_BOOT_RECEIVER"
    }

}