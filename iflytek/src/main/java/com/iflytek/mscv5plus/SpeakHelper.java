package com.iflytek.mscv5plus;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.util.ResourceUtil;
import com.uniquext.ispeak.api.SpeakApi;
import com.uniquext.ispeak.utils.SharedPreferencesHelper;

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
 * @date 2022/5/25 - 13:59
 */
public class SpeakHelper implements SpeakApi {

    private SpeechSynthesizer speechSynthesizer;
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    public void initTTS(Context context) {
        sharedPreferencesHelper = new SharedPreferencesHelper(context);
        speechSynthesizer = SpeechSynthesizer.createSynthesizer(context, new InitListener() {
            @Override
            public void onInit(int code) {
                Log.e("####", "InitListener init() code = " + code);
                if (code == ErrorCode.SUCCESS) {
                    setParam(context);
                }
            }
        });
    }

    @Override
    public void startSpeaking(String text) {
        Log.e("####", "startSpeaking：" + System.currentTimeMillis());
        speechSynthesizer.startSpeaking(text, null);
    }

    @Override
    public void stopSpeaking() {
        if (speechSynthesizer == null || !speechSynthesizer.isSpeaking()) return;

        int speed = sharedPreferencesHelper.get(SharedPreferencesHelper.SPKeys.SPEECH_SPEED, SpeakApi.defaultSpeed);
        int pitch = sharedPreferencesHelper.get(SharedPreferencesHelper.SPKeys.SOUND_PITCH, SpeakApi.defaultPitch);
        int volume = sharedPreferencesHelper.get(SharedPreferencesHelper.SPKeys.SOUND_VOLUME, SpeakApi.defaultVolume);

        speechSynthesizer.setParameter(SpeechConstant.SPEED, String.valueOf(speed));
        speechSynthesizer.setParameter(SpeechConstant.PITCH, String.valueOf(pitch));
        speechSynthesizer.setParameter(SpeechConstant.VOLUME, String.valueOf(volume));
        speechSynthesizer.stopSpeaking();
    }

    private void setParam(Context context) {
        int speed = sharedPreferencesHelper.get(SharedPreferencesHelper.SPKeys.SPEECH_SPEED, SpeakApi.defaultSpeed);
        int pitch = sharedPreferencesHelper.get(SharedPreferencesHelper.SPKeys.SOUND_PITCH, SpeakApi.defaultPitch);
        int volume = sharedPreferencesHelper.get(SharedPreferencesHelper.SPKeys.SOUND_VOLUME, SpeakApi.defaultVolume);
        // 清空参数
        speechSynthesizer.setParameter(SpeechConstant.PARAMS, null);
        //设置使用本地增强引擎
        speechSynthesizer.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_XTTS);
        //设置发音人资源路径
        speechSynthesizer.setParameter(ResourceUtil.TTS_RES_PATH, getResourcePath(context));
        //设置发音人
        speechSynthesizer.setParameter(SpeechConstant.VOICE_NAME, SpeechApp.TTS_VOICER);
        //设置合成语速
        speechSynthesizer.setParameter(SpeechConstant.SPEED, String.valueOf(speed));
        //设置合成音调
        speechSynthesizer.setParameter(SpeechConstant.PITCH, String.valueOf(pitch));
        //设置合成音量
        speechSynthesizer.setParameter(SpeechConstant.VOLUME, String.valueOf(volume));
        //设置播放器音频流类型    音乐、铃声等类型的声音
        speechSynthesizer.setParameter(SpeechConstant.STREAM_TYPE, String.valueOf(AudioManager.STREAM_MUSIC));
        // 设置播放合成音频打断音乐播放，默认为true
        speechSynthesizer.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
    }

    private String getResourcePath(Context context) {
        String type = "xtts";
        String common = ResourceUtil.generateResourcePath(context, ResourceUtil.RESOURCE_TYPE.assets, type + "/common.jet");
        String voicer = ResourceUtil.generateResourcePath(context, ResourceUtil.RESOURCE_TYPE.assets, type + "/" + SpeechApp.TTS_VOICER + ".jet");
        return common + ";" + voicer;
    }
}
