package com.uniquext.alice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int WINDOW_PERMISSION_REQUEST_CODE = 0b01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        checkAndRequestWindowPermission(this, WINDOW_PERMISSION_REQUEST_CODE);
        /**
         * 1.悬浮框权限
         */
    }


    private void startService() {
        startService(new Intent(this, AssistantService.class));



//        Util.test(this);
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