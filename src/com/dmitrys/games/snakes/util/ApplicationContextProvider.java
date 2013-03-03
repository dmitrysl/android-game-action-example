package com.dmitrys.games.snakes.util;

import android.app.Application;
import android.content.Context;

/**
 * User: Administrator
 * Date: 03.03.13
 * Time: 17:54
 */
public class ApplicationContextProvider extends Application {
    private static Context сontext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.сontext = getApplicationContext();
    }

    public static Context getСontext() {
        return сontext;
    }
}
