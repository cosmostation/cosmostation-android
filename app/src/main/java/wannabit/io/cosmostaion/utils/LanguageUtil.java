package wannabit.io.cosmostaion.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.LocaleList;

import java.util.Locale;

import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;

public class LanguageUtil extends BaseFragment {
    public static final String LANGUAGE_ENGLISH = "en";
    public static final String LANGUAGE_KOREAN = "ko";
    public static final String DEFAULT_MODE = "default";
    public static String languageSet;

    public static void setLanguageCode(Context context, String language) {
        LocaleList locale;
        if (language.equals(DEFAULT_MODE)) {
            locale = new LocaleList(Locale.forLanguageTag(Locale.getDefault().getLanguage()));
        } else {
            locale = new LocaleList(Locale.forLanguageTag(language));
        }
        LocaleList.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocales(locale);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    public static void modSave(Context context, String selectMod) {
        SharedPreferences sp = context.getSharedPreferences(BaseConstant.PRE_LANGUAGE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(BaseConstant.PRE_LANGUAGE, selectMod);
        editor.apply();
    }

    public static String modLoad(Context context) {
        SharedPreferences sp = context.getSharedPreferences(BaseConstant.PRE_LANGUAGE, MODE_PRIVATE);
        return sp.getString(BaseConstant.PRE_LANGUAGE, "");
    }
}
