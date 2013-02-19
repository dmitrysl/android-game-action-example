package com.dmitrys.games.snakes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.dmitrys.games.snakes.manager.BackgroundSoundService;

/**
 * User: Administrator
 * Date: 17.02.13
 * Time: 13:43
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        startService(new Intent(this, BackgroundSoundService.class));
    }
}
