package com.dmitrys.games.snakes.manager;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Administrator
 * Date: 17.02.13
 * Time: 12:16
 */
public class SoundManager {

    private static final String TAG = "SoundManager";

    private SoundPool soundPool;
    private Map<Integer, Integer> soundPoolMap;
    private AudioManager audioManager;
    private Context context;
//    private MediaPlayer mediaPlayer;
    public boolean isLoaded = false;

    public SoundManager(Context context) {
        this.context = context;
        this.soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                isLoaded = true;
            }
        });
        this.soundPoolMap = new HashMap<Integer, Integer>();
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void addSound(int index, int soundResourceId) {
        soundPoolMap.put(index, soundPool.load(context, soundResourceId, 1));
    }

    public void playSound(int index) {
        float streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        streamVolume = streamVolume / audioManager.getStreamMaxVolume(AudioManager.STREAM_RING) + 0.2f;
        soundPool.play(soundPoolMap.get(index), streamVolume, streamVolume, 1, 0, 1f);
    }

    public void playLoopedSound(int index) {
        float streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        soundPool.play(soundPoolMap.get(index), streamVolume, streamVolume, 1, -1, 1f);
    }

    public static final int MUSIC_PREVIOUS = -1;
    public static final int MUSIC_MENU = 0;
    public static final int MUSIC_GAME = 1;
    public static final int MUSIC_END_GAME = 2;
    private static Map<Integer, MediaPlayer> players = new HashMap<Integer, MediaPlayer>();
    private static int currentMusic = -1;
    private static int previousMusic = -1;
//
////    public static float getMusicVolume(Context context, int resourceId) {
////        String[] volumes = context.getResources().getStringArray(resourceId);
////        String volumeString = PreferenceManager.getDefaultSharedPreferences(context).getString(
////                context.getString(R.string.key_pref_music_volume), volumes[PREF_DEFAULT_MUSIC_VOLUME_ITEM]);
////        return new Float(volumeString).floatValue();
////    }
//
//    public static void start(Context context, int music) {
//        start(context, music, false);
//    }
//
//    public static void start(Context context, int music, boolean force) {
//        if (!force && currentMusic > -1) {
//// already playing some music and not forced to change
//            return;
//        }
//        if (music == MUSIC_PREVIOUS) {
//            Log.d(TAG, "Using previous music [" + previousMusic + "]");
//            music = previousMusic;
//        }
//        if (currentMusic == music) {
//// already playing this music
//            return;
//        }
//        if (currentMusic != -1) {
//            previousMusic = currentMusic;
//            Log.d(TAG, "Previous music was [" + previousMusic + "]");
//// playing some other music, pause it and change
//            pause();
//        }
//        currentMusic = music;
//        Log.d(TAG, "Current music is now [" + currentMusic + "]");
//        MediaPlayer mp = (MediaPlayer) players.get(music);
//        if (mp != null) {
//            if (!mp.isPlaying()) {
//                mp.start();
//            }
//        } else {
//            if (music == MUSIC_MENU) {
//                mp = MediaPlayer.create(context, R.raw.theme);
//            } else if (music == MUSIC_GAME) {
//                mp = MediaPlayer.create(context, R.raw.theme);
//            } else if (music == MUSIC_END_GAME) {
//                mp = MediaPlayer.create(context, R.raw.theme);
//            } else {
//                Log.e(TAG, "unsupported music number - " + music);
//                return;
//            }
//            players.put(music, mp);
//            float volume = 0.22f; // getMusicVolume(context);
//            Log.d(TAG, "Setting music volume to " + volume);
//            mp.setVolume(volume, volume);
//            if (mp == null) {
//                Log.e(TAG, "player was not created successfully");
//            } else {
//                try {
//                    mp.setLooping(true);
//                    mp.start();
//                } catch (Exception e) {
//                    Log.e(TAG, e.getMessage(), e);
//                }
//            }
//        }
//    }
//
//    public static void pause() {
//        Collection<MediaPlayer> mps = players.values();
//        for (MediaPlayer p : mps) {
//            if (p.isPlaying()) {
//                p.pause();
//            }
//        }
//// previousMusic should always be something valid
//        if (currentMusic != -1) {
//            previousMusic = currentMusic;
//            Log.d(TAG, "Previous music was [" + previousMusic + "]");
//        }
//        currentMusic = -1;
//        Log.d(TAG, "Current music is now [" + currentMusic + "]");
//    }
//
//    public static void updateVolumeFromPrefs(Context context) {
//        try {
//            float volume = 2.22f; //getMusicVolume(context);
//            Log.d(TAG, "Setting music volume to " + volume);
//            Collection<MediaPlayer> mps = players.values();
//            for (MediaPlayer p : mps) {
//                p.setVolume(volume, volume);
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage(), e);
//        }
//    }
//
//    public static void release() {
//        Log.d(TAG, "Releasing media players");
//        Collection<MediaPlayer> mps = players.values();
//        for (MediaPlayer mp : mps) {
//            try {
//                if (mp != null) {
//                    if (mp.isPlaying()) {
//                        mp.stop();
//                    }
//                    mp.release();
//                }
//            } catch (Exception e) {
//                Log.e(TAG, e.getMessage(), e);
//            }
//        }
//        mps.clear();
//        if (currentMusic != -1) {
//            previousMusic = currentMusic;
//            Log.d(TAG, "Previous music was [" + previousMusic + "]");
//        }
//        currentMusic = -1;
//        Log.d(TAG, "Current music is now [" + currentMusic + "]");
//    }
}
