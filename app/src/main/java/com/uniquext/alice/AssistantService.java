package com.uniquext.alice;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.uniquext.alice.pet.PetManager;
import com.uniquext.ispeak.utils.Constants;
import com.uniquext.alice.speech.SpeechManager;
import com.uniquext.ispeak.listener.WakeUpListener;
import com.uniquext.imageloader.annotation.Component;
import com.uniquext.imageloader.annotation.LoaderModule;

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
 * @date 2022/5/5 - 10:33
 */
@LoaderModule(component = Component.Glide)
public class AssistantService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.e("####", event.toString());
    }

    @Override
    public void onInterrupt() {
        Log.e("####", "onInterrupt");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SpeechManager.getInstance().init(this);
        SpeechManager.getInstance().initWakeUp(this);
        SpeechManager.getInstance().initTTS(this);
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.e("####", "onServiceConnected");
        SpeechManager.getInstance().startWakeListener(new WakeUpListener() {
            @Override
            public void onSuccess(String result) {
                Log.e("#### onSuccess", result);
                PetManager.getInstance().show(getContext());
                SpeechManager.getInstance().startSpeaking(Constants.getRandomGreeting());
            }

            @Override
            public void onError(String errorCode, String errorMessage) {
                Log.e("#### onError", errorCode + " # " + errorMessage);
            }
        });
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("####", "onUnbind");
        PetManager.getInstance().hide(this);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("####", "onDestroy");
        SpeechManager.getInstance().closeWakeListener();
        super.onDestroy();
    }

    private Context getContext() {
        return this;
    }
}