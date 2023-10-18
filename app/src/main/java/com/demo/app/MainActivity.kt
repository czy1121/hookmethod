package com.demo.app

import android.app.ActivityManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        PrivacyManager.isUserGranted = { true }


        val brand = Build.BRAND

        val serial = Build.getSerial()

        val tasks = getSystemService(ActivityManager::class.java).getRunningTasks(2)

        val androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

        Log.e("OoO","brand = $brand, serial = $serial, androidId = $androidId, tasks = \n${tasks.joinToString("\n")}")

    }
}