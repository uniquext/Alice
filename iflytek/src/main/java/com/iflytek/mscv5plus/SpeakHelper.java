package com.iflytek.mscv5plus;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.util.ResourceUtil;
import com.iflytek.mscv5plus.SpeechApp;
import com.uniquext.ispeak.Constants;
import com.uniquext.ispeak.SpeakApi;

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

    @Override
    public void initTTS(Context context) {
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
        speechSynthesizer.stopSpeaking();
    }

    private void setParam(Context context) {
        // 清空参数
        speechSynthesizer.setParameter(SpeechConstant.PARAMS, null);
        //设置使用本地增强引擎
        speechSynthesizer.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_XTTS);
        //设置发音人资源路径
        speechSynthesizer.setParameter(ResourceUtil.TTS_RES_PATH, getResourcePath(context));
        //设置发音人
        speechSynthesizer.setParameter(SpeechConstant.VOICE_NAME, SpeechApp.TTS_VOICER);

        // TODO: 2022/5/25 设置页调整【语速|音调|音量|音频流类型】
        //设置合成语速    speed_preference
        speechSynthesizer.setParameter(SpeechConstant.SPEED, "70");
        //设置合成音调    pitch_preference
        speechSynthesizer.setParameter(SpeechConstant.PITCH, "50");
        //设置合成音量    volume_preference
        speechSynthesizer.setParameter(SpeechConstant.VOLUME, "50");
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