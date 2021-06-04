package com.example.aula9dispositivosmoveis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView a, b, c, d, e, f, g, h, i;
    SensorManager sensorManager;
    Sensor sensor;
    LinearLayout box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = findViewById(R.id.textAccelX);
        b = findViewById(R.id.textAccelY);
        c = findViewById(R.id.textAccelZ);
        d = findViewById(R.id.textProximity);
        e = findViewById(R.id.textLight);
        f = findViewById(R.id.textPressure);
        g = findViewById(R.id.textOrientX);
        h = findViewById(R.id.textOrientY);
        i = findViewById(R.id.textOrientZ);
        box = findViewById(R.id.box);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            a.setText("Accel X = " + sensorEvent.values[0]);
            b.setText("Accel Y = " + sensorEvent.values[1]);
            c.setText("Accel Z = " + sensorEvent.values[2]);
        }

        if(sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY){
            d.setText("Proximity = " + sensorEvent.values[0]);
        }

        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            e.setText("Light = " + sensorEvent.values[0]);
        }

        if(sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE){
            f.setText("Pressure = " + sensorEvent.values[0]);
        }

        if(sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION){
            g.setText("Orientation X = " + sensorEvent.values[0]);
            h.setText("Orientation Y = " + sensorEvent.values[1]);
            i.setText("Orientation Z = " + sensorEvent.values[2]);

            float x = sensorEvent.values[0];

            float z = sensorEvent.values[2];

            ////////X
            if(x > 1.27 && x < 1.87){
                finish();
            }
            else
            if(x < -1.27 && x > -1.87){
                box.setBackgroundColor(Color.RED);
            }

            ////////Z
            if((z > 1.2 && z < 1.8) || (z < -1.2 && z > -1.8)){
                box.setBackgroundColor(Color.GREEN); //landspace
            }
            else
            if((z > -0.8 && z < 0.2) || (z < -2.84 && z > -3.44)){
                box.setBackgroundColor(Color.BLUE); //portait
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    public void clickGps(View view) {
        startActivity(new Intent(this, Aula9GPS.class));
    }
}