package com.example.workmanager.workmanager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.Exception

class UploadManager(context : Context, paramsWorker: WorkerParameters) : CoroutineWorker(context,paramsWorker) {

    companion object{
        const val PROGRESS = "Progress"
    }

    override suspend fun doWork(): Result {

        try {
            for(x in 1..100){
                Log.i("WorkManager","Progress: $x")
                val progressData = workDataOf(PROGRESS to x)
                setProgress(progressData)

                delay(50)
            }
        }catch (ex: Exception){

        }



        return Result.success(workDataOf(PROGRESS to 100))
    }




}