package com.example.assignment;
import static android.app.PendingIntent.getActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job. JobService;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;


public class JopScheduler extends JobService {
    private static final String TAG = "ExampleJobService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        doBackgroundWork(params);
//        PendingIntent contentPending,Intent;PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
//        Notification Managermanager ;- (Notification Manager) getSystemService (NOTIFICATION_SERVICE);
//        NotificationCompat. Builder builder = new NotificationCompat. Builder (this)
//                .setContentTitle("Job Service")
//                .setContentText ("Your Job is running!")
//                .setContentIntent (content PendingIntent)
//                .setSmallIcon (R.drawable.ic_job_running)
//                .setPriority (NotificationCompat.PRIORITY_HIGH)
//                .setDefaults (NotificationCompat.DEFAULT_ALL)
//                .setAutoCancel(true);
//        manager.notify(0, builder.build());
//        return false;
        return true;
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "run: " + i);
                    if (jobCancelled) {
                        return;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.d(TAG, "Job finished");
                jobFinished(params, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }
}

