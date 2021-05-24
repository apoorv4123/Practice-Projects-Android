package com.example.jobschedular

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    //    https://medium.com/google-developers/scheduling-jobs-like-a-pro-with-jobscheduler-286ef8510129
    private val JOB_ID = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        findViewById<Button>(R.id.btnJobSchedular)
        
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun scheduleJob(view: View) {

        val jobScheduler: JobScheduler =
            this.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler// get access to the system service

        val jobInfo = JobInfo.Builder(
            JOB_ID,//ID of the job
            ComponentName(
                this,
                DemoJob::class.java
            )// Component is similar to intent. Which android component, this job will be operating on.
            // The component(service) that has to be started
        )

        jobScheduler.schedule(
            jobInfo
                .setPeriodic(1200000,600000)// There are many different types of setters
                .setPersisted(true)// If set true, then this task will persist even after a reboot. App will automatically start at phone's reboot. requires permission
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)// Unmetered network is by default wifi network
                .setRequiresCharging(true)// specifies whether the task requires charging or not
                .build()
        )
    }
}