package com.uniquext.alice.pet;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

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
 * @date 2022/5/14 - 00:36
 */
public class PetManager {

    private final PetView mPetView;

    /**
     * 1.展示view 斯卡蒂
     * 2.touch事件
     */

    private PetManager(){
        mPetView = new PetView();
    }

    public static PetManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    private static class SingleHolder {
        private static final PetManager INSTANCE = new PetManager();
    }

    public void show(Context context) {
        if (mPetView.isShown()) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {
            requestWindowPermission(context);
        } else {
            showPetWindow(context);
        }
    }

    public void hide(Context context) {
        if (!mPetView.isShown()) return;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.removeView(mPetView.getView(context.getApplicationContext()));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestWindowPermission(@NonNull final Context context) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

    private void showPetWindow(Context context) {
        Log.e("####", "showPetWindow");
        final View view = mPetView.getView(context.getApplicationContext());
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.width = 600;
        layoutParams.height = 800;
        layoutParams.x = 0;
        layoutParams.y = 0;
        windowManager.addView(view, layoutParams);

        mPetView.setOnDragListener(new PetView.OnTouchListener() {
            @Override
            public void onDrag(float dx, float dy) {
                // TODO: 2022/5/14 边界值处理
                layoutParams.x += dx;
                layoutParams.y += dy;
                windowManager.updateViewLayout(view, layoutParams);
            }

            @Override
            public void onClick(View view) {
                // TODO: 2022/5/14 点击事件处理
            }
        });
    }

}
