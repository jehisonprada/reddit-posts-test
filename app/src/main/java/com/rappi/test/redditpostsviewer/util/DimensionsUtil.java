package com.rappi.test.redditpostsviewer.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.rappi.test.redditpostsviewer.R;

/**
 * Created by Jehison on 26/12/2016.
 */
public class DimensionsUtil {

    public static boolean isTablet(Context context){
        return context.getResources().getBoolean(R.bool.isTabletDevice);
    }

    public static DisplayMetrics getScreenSize(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

}

