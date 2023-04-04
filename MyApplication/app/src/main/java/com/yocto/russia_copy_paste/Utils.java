package com.yocto.russia_copy_paste;

import android.os.Build;
import android.widget.TextView;

public class Utils {
    private Utils() {
    }

    public static boolean lineSpacingWorkaroundRequired() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return false;
        } else {
            return true;
        }
    }

    public static void setFallbackLineSpacingIfPossible(TextView textView, boolean enabled) {
        // https://issuetracker.google.com/issues/131284662
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            textView.setFallbackLineSpacing(enabled);
        }
    }
}