package com.uniquext.alice.speech;

import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.uniquext.alice.speech.speak.SpeakHelper;
import com.uniquext.alice.speech.wakeup.WakeUpHelper;
import com.uniquext.alice.speech.wakeup.WakeUpListener;

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
public class SpeechManager implements SpeechApi {

    private final WakeUpHelper wakeUpHelper;
    private final SpeakHelper speakHelper;

    private SpeechManager() {
        wakeUpHelper = new WakeUpHelper();
        speakHelper = new SpeakHelper();
    }

    public static SpeechManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    @Override
    public void init(Context context) {
        final StringBuilder param = new StringBuilder();
        param.append(SpeechConstant.APPID).append("=").append(Constants.APP_ID);
        //   auto：表示云端优先使用MSC，本地优先使用语记； msc：只使用MSC； plus：只使用语记
        //  MSC:直接通过SDK提供的接口和共享库使用语音服务
        param.append(SpeechConstant.ENGINE_MODE).append("=").append(SpeechConstant.MODE_MSC);
        SpeechUtility.createUtility(context, param.toString());
    }

    @Override
    public void initWakeUp(Context context) {
        wakeUpHelper.initWakeUp(context);
    }

    @Override
    public void startWakeListener(Context context, WakeUpListener listener) {
        wakeUpHelper.startWakeListener(context, listener);
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
