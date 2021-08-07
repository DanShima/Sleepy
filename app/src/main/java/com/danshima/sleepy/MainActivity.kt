package com.danshima.sleepy

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import android.Manifest.permission.ACTIVITY_RECOGNITION

class MainActivity : AppCompatActivity() {
    private val sleepRequestManager by lazy{
        SleepRequestsManager(this)
    }

    private val permissionRequester: ActivityResultLauncher<String> =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (!isGranted) {
                requestActivityRecognitionPermission()
            } else {
                sleepRequestManager.subscribeToSleepUpdates()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sleepRequestManager.requestSleepUpdates(requestPermission = {
            permissionRequester.launch(ACTIVITY_RECOGNITION)
        })

    }

    private fun requestActivityRecognitionPermission() {
        val intent = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        sleepRequestManager.unsubscribeFromSleepUpdates()
    }


}