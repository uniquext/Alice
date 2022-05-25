package com.uniquext.alice.speech;

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
 * @date 2022/5/25 - 14:28
 */
public class Constants {

    /**
     * 讯飞AppID
     */
    public static final String APP_ID = "10789a06";
    /**
     * 讯飞语音播报角色文件
     */
    public static final String TTS_VOICER = "xiaoyan";

    /**
     * 唤醒欢迎语
     */
    public static final String[] GREETINGS = new String[] {
            "遵循古老的盟约，打破时空的界限，听从汝之指令，Master。"
    };



    public static String getRandomGreeting() {
        return GREETINGS[0];
    }
}
