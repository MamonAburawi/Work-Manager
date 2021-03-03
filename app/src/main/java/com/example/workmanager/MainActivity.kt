package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.workmanager.worker.MyWorker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Button_click.setOnClickListener {
            val request = OneTimeWorkRequestBuilder<MyWorker>().build()
            WorkManager.getInstance(this).enqueue(request)
        }


    }


}