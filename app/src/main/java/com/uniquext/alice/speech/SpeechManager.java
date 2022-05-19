package com.uniquext.alice.speech;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.VoiceWakeuper;
import com.iflytek.cloud.WakeuperListener;
import com.iflytek.cloud.WakeuperResult;
import com.iflytek.cloud.util.ResourceUtil;

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
public class SpeechManager implements SpeechApi{

    private static final String APP_ID = "10789a06";
    private VoiceWakeuper voiceWakeuper;

    private SpeechManager(){}

    public static SpeechManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    @Override
    public void init(Context context) {
        final StringBuilder param = new StringBuilder();
        param.append(SpeechConstant.APPID).append("=").append(APP_ID);
        //  设置使用v5+
//        param.append(SpeechConstant.ENGINE_MODE + "=" + SpeechConstant.MODE_MSC);
        SpeechUtility.createUtility(context, param.toString());
    }

    @Override
    public void initWake(Context context, final WakeUpListener listener) {
        final String resPath = ResourceUtil.generateResourcePath(context, ResourceUtil.RESOURCE_TYPE.assets, "ivw/" + APP_ID + ".jet");
        voiceWakeuper = VoiceWakeuper.createWakeuper(context, null);

        // 设置唤醒模式   唤醒（wakeup），唤醒识别（oneshot）
        voiceWakeuper.setParameter(SpeechConstant.IVW_SST, "wakeup");
        // 唤醒门限值，default=1450  根据资源携带的唤醒词个数按照“id:门限;id:门限”的格式传入
//        voiceWakeuper.setParameter(SpeechConstant.IVW_THRESHOLD, "0:" + curThresh);
        // 设置持续进行唤醒 0：单次唤醒 1：循环唤醒
        voiceWakeuper.setParameter(SpeechConstant.KEEP_ALIVE, "1");
        // 设置唤醒资源路径
        voiceWakeuper.setParameter(SpeechConstant.IVW_RES_PATH, resPath);

        // 设置闭环优化网络模式   0：关闭优化功能    1：开启优化功能
//        voiceWakeuper.setParameter(SpeechConstant.IVW_NET_MODE, ivwNetMode);

        // 设置唤醒录音保存路径，保存最近一分钟的音频
//        voiceWakeuper.setParameter(SpeechConstant.IVW_AUDIO_PATH,  getExternalFilesDir("msc").getAbsolutePath() + "/ivw.wav");
//        voiceWakeuper.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        voiceWakeuper.startListening(new WakeuperListener() {
            @Override
            public void onBeginOfSpeech() {

            }

            @Override
            public void onResult(WakeuperResult wakeuperResult) {
                Log.e("#### onResult", wakeuperResult.getResultString());
                if (listener != null) {
                    listener.onSuccess(wakeuperResult.getResultString());
                }
            }

            @Override
            public void onError(SpeechError speechError) {
                //  会话自动结束，录音也会停止
                Log.e("#### onResult", speechError.getPlainDescription(true));
                if (listener != null) {
                    listener.onError(String.valueOf(speechError.getErrorCode()), speechError.getErrorDescription());
                }
            }

            @Override
            public void onEvent(int eventType, int isLast, int i2, Bundle bundle) {
                //  事件 扩展用接口，唤醒的主要事件是音频事件，以及在唤醒识别时，返回识别结果（在唤醒结果之后返回）。
            }

            @Override
            public void onVolumeChanged(int i) {

            }
        });

    }

    private static class SingleHolder {
        private static final SpeechManager INSTANCE = new SpeechManager();
    }

}
