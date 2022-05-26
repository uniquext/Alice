package com.uniquext.alice.speech;

import android.content.Context;

import com.iflytek.mscv5plus.SpeechApp;
import com.iflytek.mscv5plus.SpeakHelper;
import com.iflytek.mscv5plus.WakeUpHelper;
import com.uniquext.ispeak.api.SpeakApi;
import com.uniquext.ispeak.api.SpeechApi;
import com.uniquext.ispeak.api.WakeUpApi;
import com.uniquext.ispeak.listener.WakeUpListener;

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
 * @author penghaitao
 * @version 1.0
 * @date 2022/5/15 - 13:46
 */
public class SpeechManager implements SpeechApi, WakeUpApi, SpeakApi {

    private final SpeechApp speechApp;
    private final WakeUpHelper wakeUpHelper;
    private final SpeakHelper speakHelper;

    private SpeechManager() {
        speechApp = new SpeechApp();
        wakeUpHelper = new WakeUpHelper();
        speakHelper = new SpeakHelper();
    }

    public static SpeechManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    @Override
    public void init(Context context) {
        speechApp.init(context);
    }

    @Override
    public void initWakeUp(Context context) {
        wakeUpHelper.initWakeUp(context);
    }

    @Override
    public void startWakeListener(WakeUpListener listener) {
        wakeUpHelper.startWakeListener(listener);
    }

    @Override
    public void closeWakeListener() {
        wakeUpHelper.closeWakeListener();
    }

    @Override
    public void initTTS(Context context) {
        speakHelper.initTTS(context);
    }

    @Override
    public void startSpeaking(String text) {
        speakHelper.startSpeaking(text);
    }

    @Override
    public void stopSpeaking() {
        speakHelper.stopSpeaking();
    }

    private static class SingleHolder {
        private static final SpeechManager INSTANCE = new SpeechManager();
    }

}
