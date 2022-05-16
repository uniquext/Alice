package com.uniquext.alice.pet;

import android.content.Context;
import android.graphics.PointF;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

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
 * @author UniqueXT
 * @version 1.0
 * @date 2022/5/16 - 14:15
 */
public class PetView extends AppCompatImageView {

    private final int touchSlop;
    private final PointF startPoint = new PointF();
    private final PointF lastPoint = new PointF();

    private boolean isDrag = false;
    private OnDragListener dragListener;
    private OnClickListener clickListener;

    public PetView(@NonNull Context context) {
        super(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.show();
    }

    private void show() {
        ImageLoader.getInstance().with(this)
                .load(Uri.parse("file:///android_asset/sikadi.gif"))
                .convert(ImageType.GIF)
                .centerCrop()
                .into(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrag = false;
                startPoint.x = lastPoint.x = event.getRawX();
                startPoint.y = lastPoint.y = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float currentX = event.getRawX();
                final float currentY = event.getRawY();

                if (isDrag) {
                    drag(currentX - lastPoint.x, currentY - lastPoint.y);
                } else {
                    final float deltaX = currentX - startPoint.x;
                    final float deltaY = currentY - startPoint.y;
                    if (Math.max(Math.abs(deltaX), Math.abs(deltaY)) >= touchSlop) {
                        drag(deltaX, deltaY);
                        isDrag = true;
                    } else {
                        isDrag = false;
                    }
                }

                lastPoint.set(currentX, currentY);
                break;
            case MotionEvent.ACTION_UP:
                if (!isDrag && clickListener != null) {
                    clickListener.onClick(this);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void drag(float dx, float dy) {
        if (dragListener != null) {
            dragListener.onDrag(this, dx, dy);
        }
    }

    public void setOnDragListener(OnDragListener listener) {
        this.dragListener = listener;
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener listener) {
        this.clickListener = listener;
    }

    public interface OnDragListener {
        void onDrag(View view, float dx, float dy);
    }
}
