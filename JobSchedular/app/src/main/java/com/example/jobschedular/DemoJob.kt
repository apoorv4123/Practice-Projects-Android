package com.example.jobschedular

import android.app.job.JobParameters
import android.app.job.JobService
import android.widget.Toast

class DemoJob : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        // The task that has to be performed shall be performed here.
        Toast.makeText(this, "Hey, I'm a scheduled job!!", Toast.LENGTH_SHORT).show()

        // Async Task: If doing an Async task here, return true
        // If returning true, then have to manually tell the system when the job is complete.
        // This is done using JobFinished() method
//        jobFinished(params, false)
//        return true // This true means that there is work still going on, so don't finish this
                    // Service just yet. Used in case of asynchronous jobs. Say, the async task
                    // is being executed on a different thread. That's why

        return false// Make toast is a synchronous task, so return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }

}