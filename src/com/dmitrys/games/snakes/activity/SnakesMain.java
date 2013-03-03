package com.dmitrys.games.snakes.activity;

import android.os.Bundle;
import com.dmitrys.games.snakes.view.GameView;

public class SnakesMain extends BaseActivity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameView gameView = new GameView(this);
        gameView.setSoundManager(soundManager);

        setContentView(gameView);
    }
}
