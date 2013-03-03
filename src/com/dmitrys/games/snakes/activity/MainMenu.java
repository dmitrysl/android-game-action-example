package com.dmitrys.games.snakes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.dmitrys.games.snakes.R;
import com.dmitrys.games.snakes.manager.BackgroundSoundService;

/**
 * User: Administrator
 * Date: 02.03.13
 * Time: 12:19
 */
public class MainMenu extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        initializeButtons();
    }

    private void initializeButtons() {
        Button startNewGameButton = (Button) findViewById(R.id.button_start_new_game);
        startNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });

        Button settingsButton = (Button) findViewById(R.id.button_settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSettings();
            }
        });

        Button aboutButton = (Button) findViewById(R.id.button_about);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAbout();
            }
        });

        Button exitButton = (Button) findViewById(R.id.button_exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitGame();
            }
        });
    }

    private void startNewGame() {
        startActivity(new Intent(MainMenu.this, SnakesMain.class));
    }

    private void startSettings() {
        startActivity(new Intent(MainMenu.this, Settings.class));
    }

    private void startAbout() {
        startActivity(new Intent(MainMenu.this, About.class));
    }

    private void exitGame() {
        stopService(new Intent(this, BackgroundSoundService.class));
        finish();
    }
}
