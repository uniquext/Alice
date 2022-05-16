package com.uniquext.alice.speech;

import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

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
public class SpeechManager {

    private static final String APP_ID = "10789a06";

    private SpeechManager(){}

    public static SpeechManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    public void init(Context context) {
        final StringBuilder param = new StringBuilder();
        param.append(SpeechConstant.APPID).append("=").append(APP_ID);
        //  设置使用v5+
//        param.append(SpeechConstant.ENGINE_MODE + "=" + SpeechConstant.MODE_MSC);
        SpeechUtility.createUtility(context, param.toString());
    }

    private static class SingleHolder {
        private static final SpeechManager INSTANCE = new SpeechManager();
    }

}
