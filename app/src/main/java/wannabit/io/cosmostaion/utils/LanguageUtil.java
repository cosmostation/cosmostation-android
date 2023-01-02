package wannabit.io.cosmostaion.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;

import java.util.Locale;

import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;

public class LanguageUtil extends BaseFragment {
    public static final String LANGUAGE_ENGLISH = "en";
    public static final String LANGUAGE_KOREAN = "ko";
    public static final String LANGUAGE_JAPANESE = "ja";
    public static final String SYSTEM_MODE = "system";
    public static String languageSet;

    public static void setLanguageCode(Context context, String language) {
        LocaleList locale;
        if (language.equals(LANGUAGE_ENGLISH) || language.equals(LANGUAGE_KOREAN) || language.equals(LANGUAGE_JAPANESE)) {
            locale = new LocaleList(Locale.forLanguageTag(language));
        } else {
            locale = Resources.getSystem().getConfiguration().getLocales();
        }
        LocaleList.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocales(locale);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    public static void modSave(Context context, String selectMod) {
        SharedPreferences sp = context.getSharedPreferences(BaseConstant.PRE_LANGUAGE, MODE_PRIVATE);
        sp.edit().putString(BaseConstant.PRE_LANGUAGE, selectMod).apply();
    }

    public static String modLoad(Context context) {
        SharedPreferences sp = context.getSharedPreferences(BaseConstant.PRE_LANGUAGE, MODE_PRIVATE);
        return sp.getString(BaseConstant.PRE_LANGUAGE, "");
    }
}
