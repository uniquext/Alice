package com.uniquext.alice;

import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.annotation.Size;

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
 * @date 2022/5/16 - 11:40
 */
public class Utils {


    /**
     * 获取屏幕大小
     *
     * @param manager 窗口管理器
     * @param screen  存储数组
     */
    public static void getScreenSize(WindowManager manager, @Size(2) int[] screen) {
        Point point = new Point();
        manager.getDefaultDisplay().getRealSize(point);
        screen[0] = point.x;
        screen[1] = point.y;
    }

    /**
     * 获取窗口大小
     * 可用区域
     *
     * @param manager 窗口管理器
     * @return DisplayMetrics
     */
    public static DisplayMetrics getWindowsMetrics(WindowManager manager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

}
