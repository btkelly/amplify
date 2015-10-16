package com.github.stkent.amplify;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;

public final class ActivityStateUtil {

    private ActivityStateUtil() {}

    @SuppressLint("NewApi")
    public static boolean isActivityValid(Activity activity) {
        if (activity == null) {
            return false;
        } else {
            if (Build.VERSION.SDK_INT >= 17) {
                return !activity.isFinishing() && !activity.isDestroyed();
            } else {
                return !activity.isFinishing();
            }
        }
    }

}