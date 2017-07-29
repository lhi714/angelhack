package pnugirls.evergreen;

import android.content.Context;
import android.content.SharedPreferences;

// for IntroScreen
class ScreenManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public ScreenManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences("first", 0);
        editor = pref.edit();
    }

    public void setFirst(boolean isFirst) {
        editor.putBoolean("check", isFirst);
        editor.commit();
    }

    public boolean check() {
        return pref.getBoolean("check", true);
    }
}
