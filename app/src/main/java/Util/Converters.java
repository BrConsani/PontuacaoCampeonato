package Util;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

public abstract class Converters {

    public static int convertDpToPx(Context self, int dp){
        return Math.round(dp*(self.getResources().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));
    }
}
