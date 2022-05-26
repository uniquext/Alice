package com.uniquext.ispeak.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 　 　　   へ　　　 　／|
 * 　　    /＼7　　　 ∠＿/
 * 　     /　│　　 ／　／
 * 　    │　Z ＿,＜　／　　   /`ヽ
 * 　    │　　　 　　ヽ　    /　　〉
 * 　     Y　　　　　   `　  /　　/
 * 　    ｲ●　､　●　　⊂⊃〈　　/
 * 　    ()　 へ　　　　|　＼〈
 * 　　    >ｰ ､_　 ィ　 │ ／／      去吧！
 * 　     / へ　　 /　ﾉ＜| ＼＼        比卡丘~
 * 　     ヽ_ﾉ　　(_／　 │／／           消灭代码BUG
 * 　　    7　　　　　　　|／
 * 　　    ＞―r￣￣`ｰ―＿
 * ━━━━━━━━━━感觉萌萌哒━━━━━━━━━━
 *
 * @author UniqueXT
 * @version 1.0
 * @date 2022/5/26 - 14:58
 */
public class SharedPreferencesHelper {



    private final SharedPreferences mSharedPreferences;

    public SharedPreferencesHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(SPKeys.SP_NAME, Context.MODE_PRIVATE);
    }

    public int get(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public void set(String key, int defaultValue) {
        mSharedPreferences.edit().putInt(key, defaultValue).apply();
    }

    public interface SPKeys {
        String SP_NAME = "alice_sp";
        /**
         * 语速
         */
        String SPEECH_SPEED = "speech_speed";
        /**
         * 音调
         */
        String SOUND_PITCH = "sound_pitch";
        /**
         * 音量
         */
        String SOUND_VOLUME = "sound_volume";


    }
}
