package com.example.mayanktripathi.camerapreview;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.flurgle.camerakit.CameraKit;
import com.flurgle.camerakit.CameraView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener
 {

    ImageView img, img2;
    private SensorManager sManager;
     String var ="2";

     TextToSpeech t1;
     CameraView cameraView;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onSensorC1111hanged: ");

        img = (ImageView) findViewById(R.id.img);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("token");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraView = (CameraView) findViewById(R.id.camera);
        cameraView.setFocus(CameraKit.Constants.FOCUS_OFF);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);

                var = value;

                img = (ImageView) findViewById(R.id.img);


                Log.d(TAG, "onSensorChanged: var " + var);
                switch (value) {
                    case "1":
                        img.setBackgroundResource(R.drawable.arroww2);
                        t1.speak("Move a bit left", TextToSpeech.QUEUE_FLUSH, null);

                        break;
                    case "2":img.setBackgroundResource(R.drawable.arr);
                        //img2.setBackgroundResource(R.drawable.arr);
                        t1.speak("Walk Straight", TextToSpeech.QUEUE_FLUSH, null);

                        break;
                    case "3":
                        img.setBackgroundResource(R.drawable.arrowww2);
                        // img2.setBackgroundResource(R.drawable.arrowww2);
                        t1.speak("Move a bit right", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "4":
                        img.setBackgroundResource(R.drawable.arrowl);
                        t1.speak("turn left", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "5":
                        img.setBackgroundResource(R.drawable.arrowr);
                        t1.speak("turn right", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "6":
                        break;
                    case "7":
                        img.setBackgroundResource(R.drawable.arrowd);
                        t1.speak("Wrong Direction", TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "8":
                        break;
                    case "0":
                        img.setBackgroundResource(R.drawable.redcrossmd);
                        t1.speak("Point the camera down", TextToSpeech.QUEUE_FLUSH, null);
                        break;

                }




                //img.setBackgroundResource();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
        sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_FASTEST);


    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.stop();
        super.onResume();
    }


     @Override
     public void onSensorChanged(SensorEvent event) {

        // Log.d(TAG, "onSensorChanged: ");
         
         if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
         {
             return;
         }

         Log.d("sensor", "Orientation X (Roll) :"+ Float.toString(event.values[2]) +"\n"+
                 "Orientation Y (Pitch) :"+ Float.toString(event.values[1]) +"\n"+
                 "Orientation Z (Yaw) :"+ Float.toString(event.values[0]));

         if((event.values[1] < -90.00)){

         }
         else {

         }

     }

     @Override
     public void onAccuracyChanged(Sensor sensor, int accuracy) {
         Log.d(TAG, "onSensorChanged1: ");
     }
 }
