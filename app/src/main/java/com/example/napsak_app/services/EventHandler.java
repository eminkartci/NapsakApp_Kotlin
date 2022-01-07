package com.example.napsak_app.services;

import android.content.Context;
import android.text.format.Time;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class EventHandler extends Worker {

    public EventHandler(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        return null;
    }

    public static void oneTimeWork(){
        OneTimeWorkRequest otwr = new OneTimeWorkRequest.Builder(EventHandler.class)
                .setInitialDelay(3, TimeUnit.SECONDS)
                .setConstraints(setConstraints()).build();

        WorkManager.getInstance().enqueue(otwr);
    }

    public static Constraints setConstraints(){
        return new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .build();
    }


}
