package com.iflytek.mscv5plus;

import android.content.Context;
import android.os.Bundle;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.VoiceWakeuper;
import com.iflytek.cloud.WakeuperListener;
import com.iflytek.cloud.WakeuperResult;
import com.iflytek.cloud.util.ResourceUtil;
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
 * @author UniqueXT
 * @version 1.0
 * @date 2022/5/25 - 13:48
 */
public class WakeUpHelper implements WakeUpApi {

    private VoiceWakeuper voiceWakeuper;

    @Override
    public void initWakeUp(Context context) {
        final String resPath = ResourceUtil.generateResourcePath(context, ResourceUtil.RESOURCE_TYPE.assets, "ivw/" + SpeechApp.APP_ID + ".jet");
        voiceWakeuper = VoiceWakeuper.createWakeuper(context, null);
        // 设置唤醒模式   唤醒（wakeup），唤醒识别（oneshot）
        voiceWakeuper.setParameter(SpeechConstant.IVW_SST, "wakeup");
        // 唤醒门限值，default=1450  根据资源携带的唤醒词个数按照“id:门限;id:门限”的格式传入
//        voiceWakeuper.setParameter(SpeechConstant.IVW_THRESHOLD, "0:" + curThresh);
        // 设置持续进行唤醒 0：单次唤醒 1：循环唤醒
        voiceWakeuper.setParameter(SpeechConstant.KEEP_ALIVE, "1");
        // 设置唤醒资源路径
        voiceWakeuper.setParameter(SpeechConstant.IVW_RES_PATH, resPath);
    }

    @Override
    public void startWakeListener(final WakeUpListener wakeUpListener) {
        voiceWakeuper.startListening(new WakeuperListener() {
            @Override
            public void onBeginOfSpeech() {

            }

            @Override
            public void onResult(WakeuperResult wakeuperResult) {
                if (wakeUpListener != null) {
                    wakeUpListener.onSuccess(wakeuperResult.getResultString());
                }
            }

            @Override
            public void onError(SpeechError speechError) {
                //  会话自动结束，录音也会停止
                if (wakeUpListener != null) {
                    wakeUpListener.onError(String.valueOf(speechError.getErrorCode()), speechError.getErrorDescription());
                }
            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {
                //  事件 扩展用接口，唤醒的主要事件是音频事件，以及在唤醒识别时，返回识别结果（在唤醒结果之后返回）。
            }

            @Override
            public void onVolumeChanged(int i) {

            }
        });
    }

    @Override
    public void closeWakeListener() {
        if (voiceWakeuper == null || !voiceWakeuper.isListening()) return;
        voiceWakeuper.cancel();
    }
}
