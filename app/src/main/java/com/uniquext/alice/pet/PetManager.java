package com.uniquext.alice.pet;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.uniquext.alice.Utils;
import com.uniquext.alice.activity.SettingActivity;

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

    private final int WIDTH = 113;
    private final int HEIGHT = 120;

    private PetView mPetView;

    private PetManager() {
    }

    public static PetManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    public void show(Context context) {
        checkAndCreatePetView(context);
        if (mPetView.isAttachedToWindow()) return;
        if (!Utils.checkWindowPermission(context)) {
            Utils.jumpWindowSettings(context);
        } else {
            showPetWindow();
        }
    }

    private void showPetWindow() {
        final WindowManager windowManager = (WindowManager) mPetView.getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(mPetView, mPetView.getLayoutParams());
    }

    public void hide(Context context) {
        if (mPetView == null || !mPetView.isAttachedToWindow()) return;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.removeView(mPetView);
    }

    public boolean isShow() {
        return mPetView != null && mPetView.isAttachedToWindow();
    }

    private void checkAndCreatePetView(Context context) {
        if (mPetView == null) {
            mPetView = new PetView(context.getApplicationContext());
            initLayoutParams();
            initListener();
        }
    }

    private void initLayoutParams() {
        final WindowManager windowManager = (WindowManager) mPetView.getContext().getSystemService(Context.WINDOW_SERVICE);
        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.gravity = Gravity.START | Gravity.TOP;

        final DisplayMetrics metrics = Utils.getWindowsMetrics(windowManager);
        layoutParams.width = (int) (WIDTH * metrics.density);
        layoutParams.height = (int) (HEIGHT * metrics.density);
        layoutParams.x = metrics.widthPixels - layoutParams.width;
        layoutParams.y = metrics.heightPixels - layoutParams.height;
        mPetView.setLayoutParams(layoutParams);
    }

    private void initListener() {
        mPetView.setOnDragListener(new PetView.OnDragListener() {

            final WindowManager windowManager = (WindowManager) mPetView.getContext().getSystemService(Context.WINDOW_SERVICE);
            final WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) mPetView.getLayoutParams();
            final DisplayMetrics metrics = Utils.getWindowsMetrics(windowManager);
            final int maxDeltaWidth = metrics.widthPixels - layoutParams.width;
            final int maxDeltaHeight = metrics.heightPixels - layoutParams.height;

            @Override
            public void onDrag(View view, float dx, float dy) {
                final int x = (int) (layoutParams.x + dx);
                final int y = (int) (layoutParams.y + dy);
                layoutParams.x = Math.max(0, Math.min(x, maxDeltaWidth));
                layoutParams.y = Math.max(0, Math.min(y, maxDeltaHeight));
                windowManager.updateViewLayout(mPetView, layoutParams);
            }
        });

        mPetView.setOnClickListener(v -> SettingActivity.startActivity(v.getContext()));
    }

    private static class SingleHolder {
        private static final PetManager INSTANCE = new PetManager();
    }

}
