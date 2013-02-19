package com.dmitrys.games.snakes.manager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.dmitrys.games.snakes.R;

/**
 * User: Administrator
 * Date: 17.02.13
 * Time: 18:52
 */
public class BackgroundSoundService extends Service {

    private static final String TAG = "BackgroundSoundService";

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate");

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        mediaPlayer = MediaPlayer.create(this, R.raw.theme);
        mediaPlayer.setLooping(false); // Set looping

        float streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) - 0.15f;

        mediaPlayer.setVolume(streamVolume, streamVolume);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy");
        mediaPlayer.stop();
        mediaPlayer.release();
    }

}
