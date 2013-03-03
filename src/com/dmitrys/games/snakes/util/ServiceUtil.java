package com.dmitrys.games.snakes.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * User: Administrator
 * Date: 03.03.13
 * Time: 17:42
 */
public class ServiceUtil {
    private static String LOG_TAG = ServiceUtil.class.getName();

    public static boolean isServiceRunning(String serviceClassName){
        final ActivityManager activityManager = (ActivityManager) ApplicationContextProvider.getСontext().getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);

        for (ActivityManager.RunningServiceInfo runningServiceInfo : services) {
            if (runningServiceInfo.service.getClassName().equals(serviceClassName)){
                return true;
            }
        }
        return false;
    }
}
