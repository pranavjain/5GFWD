package com.example.mayanktripathi.camerapreview;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by mayanktripathi on 11/06/17.
 */

public class AndroidNotifications extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // Notification Function
    private void Notification(String notificationTitle,
                              String notificationMessage) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        android.app.Notification notification = new android.app.Notification(
                R.drawable.qq, "Destination Reached",
                System.currentTimeMillis());

        Intent notificationIntent = new Intent(this, AndroidNotifications.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

    }
}