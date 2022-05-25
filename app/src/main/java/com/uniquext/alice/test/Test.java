package com.uniquext.alice.test;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.uniquext.ispeak.Constants;

import java.util.Locale;

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
 * @date 2022/5/25 - 11:11
 */
public class Test {

    private static TextToSpeech textToSpeech;

    public static void init(Context context) {
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (TextToSpeech.SUCCESS == status) {
                    // setLanguage设置语言
                    int result = textToSpeech.setLanguage(Locale.CHINA);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("####", "数据丢失或不支持");
                    } else if (result == TextToSpeech.LANG_AVAILABLE) {
                        Log.e("####", "成功");
                    }
                } else {
                    Log.e("####", "onInit 失败 " + status);
                }
            }
        });
        // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
//        textToSpeech.setPitch(1.0f);
        // 设置语速
//        textToSpeech.setSpeechRate(0.5f);
    }

    public static void cancel() {
        // 不管是否正在朗读TTS都被打断
        textToSpeech.stop();
        // 关闭，释放资源
        textToSpeech.shutdown();
    }

    public static void speak() {
        textToSpeech.speak(Constants.getRandomGreeting(), TextToSpeech.QUEUE_ADD,null, null);
    }

}
