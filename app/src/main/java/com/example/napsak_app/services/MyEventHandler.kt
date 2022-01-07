package com.example.napsak_app.services

import android.app.LauncherActivity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.provider.Settings.System.getString
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.napsak_app.EventListFragment
import com.example.napsak_app.MainActivity
import com.example.napsak_app.R

class MyEventHandler(context:Context, workerParameters: WorkerParameters):Worker(context,workerParameters) {


    override fun doWork(): Result {


        Log.i("MY WORKER: ","Successful!")
        return Result.success();
    }

    private fun showNotification() {

        // Create an explicit intent for an Activity in your app
        val intent = Intent(applicationContext, EventListFragment::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent, 0)

        var builder = NotificationCompat.Builder(applicationContext, "4")
            .setSmallIcon(R.drawable.amean_logo)
            .setContentTitle("!! Coding !!")
            .setContentText("Have you tried this event?")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel"
            val descriptionText = "My Notification Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("4", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager = applicationContext.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }



}