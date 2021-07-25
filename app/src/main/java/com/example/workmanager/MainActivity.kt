package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.workmanager.workmanager.UploadManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var workManager: WorkManager
    private var uploadWork : OneTimeWorkRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workManager = WorkManager.getInstance(applicationContext)



        // Button Start
        Button_Start.setOnClickListener {
            onStartWork()
        }

        // Button End
        Button_Cancel.setOnClickListener {
            workManager.cancelWorkById(uploadWork!!.id)// for cancel the work
        }




    }


    private fun onStartWork(){
        progressBar.visibility = View.VISIBLE
        Button_Cancel.visibility = View.VISIBLE
        textView_Progress.visibility = View.VISIBLE

        uploadWork = OneTimeWorkRequest.Builder(UploadManager::class.java).build()

        workManager.beginWith(uploadWork!!).enqueue()// you can chain the works

        workManager.getWorkInfoByIdLiveData(uploadWork!!.id).observe(this, Observer { workInfo->

            textView.text = workInfo.state.name
            val progress = workInfo.progress.getInt(UploadManager.PROGRESS,0)
            textView_Progress.text = "$progress%"
            progressBar.progress = progress

            if (workInfo.state.isFinished){
                onFinishWork()
            }
        })



    }


    private fun onFinishWork(){
        progressBar.visibility = View.GONE
        Button_Cancel.visibility = View.GONE
        textView_Progress.visibility = View.GONE
        progressBar.progress = 0

    }


}