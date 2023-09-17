package com.example.capturer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private CameraHolder cameraHolder;
    private TextView textView;
    Analyser analyser;
    private Server server;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.ip);

        analyser = new Analyser(this);
        server = new Server(new Server.Callback() {
            @Override
            public void onConnection(Socket socket) {
                Log.i(TAG, "onConnection");
                analyser.setConnection(socket);
                textView.setText("Connected");
            }
        });
        new Thread(server).start();

        textView.setText("IP: " + Utils.getIPAddress(true));
        cameraHolder = new CameraHolder(this, new Size(1920, 1080), analyser);
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
        cameraHolder.startCapture();
    }
}