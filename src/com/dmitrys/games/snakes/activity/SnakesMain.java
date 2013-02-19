package com.dmitrys.games.snakes.activity;

import android.content.Intent;
import android.os.Bundle;
import com.dmitrys.games.snakes.BaseActivity;
import com.dmitrys.games.snakes.R;
import com.dmitrys.games.snakes.manager.BackgroundSoundService;
import com.dmitrys.games.snakes.manager.SoundManager;
import com.dmitrys.games.snakes.view.GameView;

public class SnakesMain extends BaseActivity {

    private SoundManager soundManager;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        soundManager = new SoundManager(this);
        soundManager.addSound(GameView.EXPLOSION_SOUND, R.raw.explosion);
        soundManager.addSound(GameView.DEATH_SOUND, R.raw.death);

        GameView gameView = new GameView(this);
        gameView.setSoundManager(soundManager);

//        setContentView(new GameView(this));

        setContentView(gameView);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, BackgroundSoundService.class));
        super.onDestroy();
    }
}
