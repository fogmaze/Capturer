package com.example.capturer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Size;
import android.view.SurfaceView;

import com.example.capturer.CameraHolder;

public class MainActivity extends AppCompatActivity {
    private CameraHolder cameraHolder;
    private SurfaceView surfaceView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = findViewById(R.id.sur);
        cameraHolder = new CameraHolder(this, new Size(640, 480), new Analyser(this), surfaceView.getHolder());
        cameraHolder.startCapture();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraHolder.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraHolder.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //cameraHolder.startCapture();
    }
}