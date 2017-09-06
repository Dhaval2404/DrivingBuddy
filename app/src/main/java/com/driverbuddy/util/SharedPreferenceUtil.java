package com.driverbuddy.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Dhaval Patel
 * @version 1.0, Sep 6, 2017
 * @since 1.0
 *
 */
public class SharedPreferenceUtil {
    private static final String SHARED_PREFERENCE_TAG = "pref_main.xml";

    private SharedPreferenceUtil() {
        throw new AssertionError();
    }

    /**
     * Get SharedPreferences Object.
     * <br>
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * <br>
     * @return Returns the SharedPreferences object
     */
    public static SharedPreferences getSharedPreferences(Context mContext) {
        return mContext.getSharedPreferences(SHARED_PREFERENCE_TAG, Activity.MODE_PRIVATE);
    }

    /**
     * Retrieve a String value from the preferences.
     * <br>
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * @param key      The name of the preference.
     * <br>
     * @return Returns the preference value if it exists, or null
     */
    public static String getString(Context mContext, String key) {
        return getSharedPreferences(mContext).getString(key, null);
    }

    /**
     * Retrieve a String value from the preferences.
     * <br>
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * @param key          The name of the preference.
     * @param defaultValue Value to return if this preference does not exist.
     * <br>
     * @return Returns the preference value if it exists, or defaultValue
     */
    public static String getString(Context mContext, String key, String defaultValue) {
        return getSharedPreferences(mContext).getString(key, defaultValue);
    }

    /**
     * Set a String value in the preferences
     *
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * @param key      The name of the preference.
     * @param value    The value for the preference.
     * <br><br>
     * @return Returns true if the new values were successfully written
     * to persistent storage.
     */
    public static boolean putString(Context mContext, String key, String value) {
        SharedPreferences pref = getSharedPreferences(mContext);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * Retrieve a int value from the preferences.
     *
     * @param mContext A Context of the application.
     * @param key      The name of the preference.
     * <br><br>
     * @return Returns the preference value if it exists, or -1
     */
    public static int getInt(Context mContext, String key) {
        return getSharedPreferences(mContext).getInt(key, -1);
    }

    /**
     * Retrieve a int value from the preferences.
     *
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * @param key          The name of the preference.
     * @param defaultValue Value to return if this preference does not exist.
     * <br><br>
     * @return Returns the preference value if it exists, or defaultValue
     */
    public static int getInt(Context mContext, String key, int defaultValue) {
        return getSharedPreferences(mContext).getInt(key, defaultValue);
    }

    /**
     * Set a int value in the preferences
     *
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * @param key      The name of the preference.
     * @param value    The value for the preference.
     * <br><br>
     * @return Returns true if the new values were successfully written
     * to persistent storage.
     */
    public static boolean putInt(Context mContext, String key, int value) {
        SharedPreferences pref = getSharedPreferences(mContext);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * Retrieve a long value from the preferences.
     *
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * @param key      The name of the preference.
     * <br><br>
     * @return Returns the preference value if it exists, or -1l
     */
    public static long getLong(Context mContext, String key) {
        return getSharedPreferences(mContext).getLong(key, -1l);
    }

    /**
     * Retrieve a long value from the preferences.
     *
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * @param key          The name of the preference.
     * @param defaultValue Value to return if this preference does not exist.
     * <br><br>
     * @return Returns the preference value if it exists, or defaultValue
     */
    public static long getLong(Context mContext, String key, long defaultValue) {
        return getSharedPreferences(mContext).getLong(key, defaultValue);
    }

    /**
     * Set a long value in the preferences
     *
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * @param key      The name of the preference.
     * @param value    The value for the preference.
     * <br><br>
     * @return Returns true if the new values were successfully written
     * to persistent storage.
     */
    public static boolean putLong(Context mContext, String key, long value) {
        SharedPreferences pref = getSharedPreferences(mContext);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * Retrieve a boolean value from the preferences.
     *
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * @param key      The name of the preference.
     * <br><br>
     * @return Returns the preference value if it exists, or false
     */
    public static boolean getBoolean(Context mContext, String key) {
        return getSharedPreferences(mContext).getBoolean(key, false);
    }

    /**
     * Retrieve a boolean value from the preferences.
     *
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * @param key          The name of the preference.
     * @param defaultValue Value to return if this preference does not exist.
     * <br><br>
     * @return Returns the preference value if it exists, or defaultValue
     */
    public static boolean getBoolean(Context mContext, String key, boolean defaultValue) {
        return getSharedPreferences(mContext).getBoolean(key, defaultValue);
    }

    /**
     * Set a boolean value in the preferences
     *
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * @param key      The name of the preference.
     * @param value    The value for the preference.
     * <br><br>
     * @return Returns true if the new values were successfully written
     * to persistent storage.
     */
    public static boolean putBoolean(Context mContext, String key, boolean value) {
        SharedPreferences pref = getSharedPreferences(mContext);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * Remove preference value.
     *
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * @param key      The name of the preference to remove.
     * <br><br>
     * @return Returns true if preference value were successfully removed from
     * persistent storage.
     */
    public static boolean remove(Context mContext, String key) {
        SharedPreferences pref = getSharedPreferences(mContext);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        return editor.commit();
    }

    /**
     * clear all preference value.
     *
     * @param mContext The context to use.  Usually your {@link android.app.Application}
     *                 or {@link Activity} object.
     * <br><br>
     * @return Returns true if all preference value successfully removed from
     * persistent storage.
     */
    public static boolean clear(Context mContext) {
        SharedPreferences pref = getSharedPreferences(mContext);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        return editor.commit();
    }

}