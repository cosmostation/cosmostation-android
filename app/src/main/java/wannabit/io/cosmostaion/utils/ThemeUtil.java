package wannabit.io.cosmostaion.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;

import androidx.appcompat.app.AppCompatDelegate;

import wannabit.io.cosmostaion.base.BaseConstant;

public class ThemeUtil {
    public static final String LIGHT_MODE = "light";
    public static final String DARK_MODE = "dark";
    public static final String DEFAULT_MODE = "default";
    public static String themeColor;

    public static void applyTheme(String themeColor) {
        switch (themeColor) {
            case LIGHT_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;

            case DARK_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;

            case DEFAULT_MODE:
                // 안드로이드 10 이상
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }
                // 안드로이드 10 미만
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                }
                break;
        }
    }

    public static void modSave(Context context, String selectMod) {
        SharedPreferences sp = context.getSharedPreferences(BaseConstant.PRE_THEME_MOD, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(BaseConstant.PRE_THEME_MOD, selectMod);
        editor.apply();
    }

    public static String currentMode(Context context) {
        if (DEFAULT_MODE.equals(modLoad(context)) || modLoad(context).isEmpty()) {
            int nightModeFlags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                return DARK_MODE;
            } else {
                return LIGHT_MODE;
            }
        } else {
            return modLoad(context);
        }

    }

    public static String modLoad(Context context) {
        SharedPreferences sp = context.getSharedPreferences(BaseConstant.PRE_THEME_MOD, MODE_PRIVATE);
        return sp.getString(BaseConstant.PRE_THEME_MOD, "");
    }


}
