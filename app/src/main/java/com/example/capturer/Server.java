package com.example.capturer;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private Callback callback;
    public Server(Callback callback) {
        this.callback = callback;
    }
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(7439);
            Log.i("Server", "Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                Log.i("Server", "Connection accepted");
                callback.onConnection(socket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    interface Callback {
        void onConnection(Socket socket);
    }
}

