package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyMusicService extends Service {
    MediaPlayer mp;
    public MyMusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this,R.raw.ordinary);
        mp.start();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        mp.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}