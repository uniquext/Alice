package com.uniquext.alice.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.uniquext.alice.R;
import com.uniquext.alice.pet.PetManager;
import com.uniquext.imageloader.ImageLoader;
import com.uniquext.imageloader.type.ImageType;

public class MainActivity extends AppCompatActivity {

    private static final int WINDOW_PERMISSION_REQUEST_CODE = 0b01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAndRequestWindowPermission(this, WINDOW_PERMISSION_REQUEST_CODE);
        /**
         * 1。是否已经展示
         * 1.悬浮框权限
         * 2.无障碍服务
         */

//        ImageLoader.getInstance().with(this)
//                .load(Uri.parse("file:///android_asset/sikadi.gif"))
//                .convert(ImageType.GIF)
//                .centerCrop()
//                .into(findViewById(R.id.iv_image));
//        findViewById(R.id.iv_image).setBackgroundColor(Color.RED);
    }


    private void startService() {
        PetManager.getInstance().show(this);
    }

    private boolean checkWindowPermission(Context context) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context);
    }

    private void checkAndRequestWindowPermission(@NonNull final Context context, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {
            requestWindowPermissionWithRequestCode(context, requestCode);
        } else {
            startService();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestWindowPermissionWithRequestCode(@NonNull final Context context, int requestCode) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, requestCode);
        } else {
            context.startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WINDOW_PERMISSION_REQUEST_CODE && checkWindowPermission(this)) {
            startService();
        } else {
            Toast.makeText(this, "未获取悬浮框选项", Toast.LENGTH_SHORT).show();
        }
    }
}