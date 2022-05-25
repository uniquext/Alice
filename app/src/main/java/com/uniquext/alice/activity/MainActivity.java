package com.uniquext.alice.activity;

import android.Manifest;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;

import com.uniquext.alice.R;
import com.uniquext.alice.Utils;
import com.uniquext.alice.pet.PetManager;
import com.uniquext.ispeak.Constants;
import com.uniquext.alice.speech.SpeechManager;
import com.uniquext.android.lightpermission.LightPermission;
import com.uniquext.android.lightpermission.settings.AppSettingsDialog;

public class MainActivity extends AppCompatActivity {

    private final AppCompatTextView[] tvLabel = new AppCompatTextView[3];
    private final SwitchCompat[] switchButton = new SwitchCompat[3];
    private SwitchCompat togglePet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLabel[0] = findViewById(R.id.tv_overlays);
        tvLabel[1] = findViewById(R.id.tv_audio);
        tvLabel[2] = findViewById(R.id.tv_service);
        switchButton[0] = findViewById(R.id.switch_overlays);
        switchButton[1] = findViewById(R.id.switch_audio);
        switchButton[2] = findViewById(R.id.switch_service);
        togglePet = findViewById(R.id.switch_pet);

        tvLabel[0].setOnClickListener(v -> Utils.jumpWindowSettings(this));
        tvLabel[1].setOnClickListener(v -> requestPermissions());
        tvLabel[2].setOnClickListener(v -> Utils.jumpToAccessibilitySettings(this));

        togglePet.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                PetManager.getInstance().show(this);
            } else {
                PetManager.getInstance().hide(this);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        switchButton[0].setChecked(Utils.checkWindowPermission(this));
        switchButton[1].setChecked(LightPermission.hasPermission(this, Manifest.permission.RECORD_AUDIO));
        switchButton[2].setChecked(Utils.checkAccessibilityOpen(this));
    }

    private void requestPermissions() {
        LightPermission.with(this)
                .permissions(Manifest.permission.RECORD_AUDIO)
                .grant(() -> switchButton[1].setChecked(true))
                .deny(strings -> switchButton[1].setChecked(false))
                .prohibit(strings ->
                        new AppSettingsDialog.Builder(this)
                                .title(getString(R.string.audio_permission_tips))
                                .show()
                )
                .request();
    }

    private void test1() {

    }

    private void test2() {

    }

}