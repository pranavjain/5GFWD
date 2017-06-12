package com.example.mayanktripathi.camerapreview;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by mayanktripathi on 10/06/17.
 */

public class first extends AppCompatActivity {

    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ImageView button = (ImageView) findViewById(R.id.button2);

        mBuilder.setSmallIcon(R.drawable.qq);
        mBuilder.setContentTitle("Notification Alert, Click Me!");
        mBuilder.setContentText("Destination Reached");



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendNotification(v);


                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=65.0582500,25.4672650"));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);




            }
        });
    }

    public void sendNotification(View view) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);

//Create the intent that’ll fire when the user taps the notification//

        Intent intent = new Intent(this, destination.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        mBuilder.setContentIntent(pendingIntent);

        mBuilder.setSmallIcon(R.drawable.qq);
        mBuilder.setContentTitle("Navify");
        mBuilder.setContentText("Destination reached");

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(001, mBuilder.build());
    }
}
