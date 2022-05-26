package com.uniquext.alice.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.uniquext.alice.R;
import com.uniquext.ispeak.api.SpeakApi;
import com.uniquext.ispeak.utils.SharedPreferencesHelper;

import java.util.Locale;

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
 * @date 2022/5/16 - 14:03
 */
public class SettingActivity extends AppCompatActivity {

    private final AppCompatTextView[] tvSpeeches = new AppCompatTextView[3];
    private final SeekBar[] seekSpeeches = new SeekBar[3];
    private final AppCompatButton[] btnSpeeches = new AppCompatButton[2];
    private final int[] sourceSpeechValues = new int[3];

    private AppCompatImageView ivBack;

    private SharedPreferencesHelper sharedPreferencesHelper;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferencesHelper = new SharedPreferencesHelper(this);

        initView();
        initEvent();
        speechConfig();
    }

    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvSpeeches[0] = findViewById(R.id.tv_speech_speed);
        seekSpeeches[0] = findViewById(R.id.seek_speech_speed);
        tvSpeeches[1] = findViewById(R.id.tv_sound_pitch);
        seekSpeeches[1] = findViewById(R.id.seek_sound_pitch);
        tvSpeeches[2] = findViewById(R.id.tv_sound_volume);
        seekSpeeches[2] = findViewById(R.id.seek_sound_volume);
        btnSpeeches[0] = findViewById(R.id.tv_revert);
        btnSpeeches[1] = findViewById(R.id.tv_save);
    }

    private void initEvent() {
        ivBack.setOnClickListener(v -> onBackPressed());
        for (int i = 0; i < seekSpeeches.length; ++ i) {
            final int index = i;
            seekSpeeches[index].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    tvSpeeches[index].setText(String.format(Locale.CHINA, ": %d", progress));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
        btnSpeeches[0].setOnClickListener(v -> revertSpeechConfig());
        btnSpeeches[1].setOnClickListener(v -> {
            sharedPreferencesHelper.set(SharedPreferencesHelper.SPKeys.SPEECH_SPEED, seekSpeeches[0].getProgress());
            sharedPreferencesHelper.set(SharedPreferencesHelper.SPKeys.SOUND_PITCH, seekSpeeches[1].getProgress());
            sharedPreferencesHelper.set(SharedPreferencesHelper.SPKeys.SOUND_VOLUME, seekSpeeches[2].getProgress());
        });
    }

    private void speechConfig() {
        sourceSpeechValues[0] = sharedPreferencesHelper.get(SharedPreferencesHelper.SPKeys.SPEECH_SPEED, SpeakApi.defaultSpeed);
        sourceSpeechValues[1] = sharedPreferencesHelper.get(SharedPreferencesHelper.SPKeys.SOUND_PITCH, SpeakApi.defaultPitch);
        sourceSpeechValues[2] = sharedPreferencesHelper.get(SharedPreferencesHelper.SPKeys.SOUND_VOLUME, SpeakApi.defaultVolume);
        revertSpeechConfig();
    }

    private void revertSpeechConfig() {
        for (int i = 0; i < seekSpeeches.length; ++ i) {
            seekSpeeches[i].setProgress(sourceSpeechValues[i]);
            tvSpeeches[i].setText(String.format(Locale.CHINA, ": %d", sourceSpeechValues[i]));
        }
    }
}
