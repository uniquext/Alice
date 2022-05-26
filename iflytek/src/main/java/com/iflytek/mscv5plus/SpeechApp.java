package com.iflytek.mscv5plus;

import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.uniquext.ispeak.api.SpeechApi;

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
 * @date 2022/5/25 - 23:34
 */
public class SpeechApp implements SpeechApi {

    /**
     * 讯飞AppID
     */
    public static final String APP_ID = "10789a06";
    /**
     * 讯飞语音播报角色文件
     */
    public static final String TTS_VOICER = "xiaoyan";

    @Override
    public void init(Context context) {
        final StringBuilder param = new StringBuilder();
        param.append(SpeechConstant.APPID).append("=").append(APP_ID);
        //   auto：表示云端优先使用MSC，本地优先使用语记； msc：只使用MSC； plus：只使用语记
        //  MSC:直接通过SDK提供的接口和共享库使用语音服务
        param.append(",");
        param.append(SpeechConstant.ENGINE_MODE).append("=").append(SpeechConstant.MODE_MSC);
        SpeechUtility.createUtility(context, param.toString());
    }
}
