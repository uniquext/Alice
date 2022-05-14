package com.uniquext.alice.pet;

import android.content.Context;
import android.graphics.PointF;
import android.net.Uri;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;

import com.uniquext.imageloader.ImageLoader;
import com.uniquext.imageloader.type.ImageType;

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
 * @date 2022/5/14 - 11:56
 */
public class PetView {



    private View pet = null;

    public PetView(){}

    public View getView(Context context) {
        if (pet == null) {
           createPet(context);
        }
        return pet;
    }

    public void createPet(Context context) {
        Log.e("####", "createPet");
        pet = new ImageView(context);
        ImageLoader.getInstance().with(context)
                .load(Uri.parse("file:///android_asset/sikadi.gif"))
                .convert(ImageType.GIF)
                .centerCrop()
                .into((ImageView)pet);
    }

    public boolean isShown() {
        return pet != null && pet.isAttachedToWindow();
    }

    public interface OnTouchListener {
        void onDrag(float dx, float dy);
        void onClick(View view);
    }

    public void setOnDragListener(@NonNull final OnTouchListener listener) {
        if (pet == null) return;
        pet.setOnTouchListener(new View.OnTouchListener() {
            private final PointF startPoint = new PointF();
            private final PointF lastPoint = new PointF();
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startPoint.x = lastPoint.x = event.getRawX();
                        startPoint.y = lastPoint.y = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        final float currentX = event.getRawX();
                        final float currentY = event.getRawY();
                        listener.onDrag(currentX - lastPoint.x, currentY - lastPoint.y);
                        lastPoint.set(currentX, currentY);
                        break;
                    case MotionEvent.ACTION_UP:
//                        final float dx = event.getRawX() - startPoint.x;
//                        final float dy = event.getRawY() - startPoint.y;
//                        Log.e("#### move", dx + " # " + dy);
                        //  判断delta距离
                        break;
                }
                return pet.onTouchEvent(event);
            }
        });

    }


    private final int MIN_DELTA = 100;
    private void move() {

    }

}
