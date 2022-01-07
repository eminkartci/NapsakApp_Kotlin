package com.example.napsak_app.services;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.napsak_app.MainActivity;
import com.example.napsak_app.R;

import java.util.concurrent.TimeUnit;

public class EventHandler extends Worker {

    public EventHandler(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        showNotification("Coding");
        return null;
    }

    public static void oneTimeWork(){
        OneTimeWorkRequest otwr = new OneTimeWorkRequest.Builder(EventHandler.class)
                .setInitialDelay(3, TimeUnit.SECONDS)
                //.setConstraints(setConstraints())
                .build();

        WorkManager.getInstance().enqueue(otwr);
    }

    public static void periodicWork(String eventName){

        PeriodicWorkRequest prwr = new PeriodicWorkRequest.Builder(
                EventHandler.class,15,TimeUnit.SECONDS)
                .addTag("Event Remainder!")
                .setConstraints(setConstraints())
                .build();

        WorkManager.getInstance().enqueueUniquePeriodicWork(eventName, ExistingPeriodicWorkPolicy.REPLACE,prwr);

    }

    public void showNotification(String eventTitle){

        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,notificationIntent,PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder ncb = new NotificationCompat.Builder(getApplicationContext(),"14")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(eventTitle)
                .setContentInfo("Have you tried this event?")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat nmc = NotificationManagerCompat.from(getApplicationContext());
        nmc.notify(4,ncb.build());

    }

    public static Constraints setConstraints(){
        return new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .build();
    }


}
