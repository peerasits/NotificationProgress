package com.example.notificationprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    final int id = 6969;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showNitification();

    }

    private void showNitification() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_baseline_cloud_download_24)
                .setContentTitle("Downloading file...");

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int v = 0;
            @Override
            public void run() {
                if(v<= 100){
                    builder.setProgress(100,v,false);
                    builder.setContentText(v+ " %");
                    handler.postDelayed(this::run,300);
                }else{
                    builder.setContentText("Finished download");
                    handler.removeCallbacks(this::run);
                }
                v++;
                notificationManager.notify(id,builder.build());
            }
        };
        handler.postDelayed(runnable,300);
    }

}