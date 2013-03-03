package com.dmitrys.games.snakes.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.dmitrys.games.snakes.R;

/**
 * User: Administrator
 * Date: 03.03.13
 * Time: 15:07
 */
public class Settings extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Button backButton = (Button) findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
