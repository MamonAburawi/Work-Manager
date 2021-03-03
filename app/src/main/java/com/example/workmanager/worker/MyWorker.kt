package com.example.workmanager.worker

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MyWorker(private val appContext : Context , paramsWorker: WorkerParameters) : CoroutineWorker(appContext,paramsWorker) {
    override suspend fun doWork(): Result {

        toast()

        return Result.success()
    }


    private suspend fun toast(){
        withContext(Dispatchers.Main){
            delay(5000)
            Toast.makeText(appContext,"Working !",Toast.LENGTH_SHORT).show()
        }

    }

}