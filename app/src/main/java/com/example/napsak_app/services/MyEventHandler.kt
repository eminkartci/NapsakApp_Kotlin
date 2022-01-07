package com.example.napsak_app.services

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyEventHandler(context:Context, workerParameters: WorkerParameters):Worker(context,workerParameters) {

    override fun doWork(): Result {


        Log.i("MY WORKER: ","Successful!")
        return Result.success();
    }

}