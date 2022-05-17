package com.uniquext.alice.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;

import com.uniquext.alice.R;
import com.uniquext.alice.Utils;
import com.uniquext.alice.pet.PetManager;

public class MainActivity extends AppCompatActivity {

    private final AppCompatTextView[] tvLabel = new AppCompatTextView[2];
    private final SwitchCompat[] switchButton = new SwitchCompat[2];
    private SwitchCompat togglePet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLabel[0] = findViewById(R.id.tv_permission);
        tvLabel[1] = findViewById(R.id.tv_service);
        switchButton[0] = findViewById(R.id.switch_permission);
        switchButton[1] = findViewById(R.id.switch_service);
        togglePet = findViewById(R.id.switch_pet);

        tvLabel[0].setOnClickListener(v -> Utils.jumpWindowSettings(this));
        tvLabel[1].setOnClickListener(v -> Utils.jumpToAccessibilitySettings(this));

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
        switchButton[1].setChecked(Utils.checkAccessibilityOpen(this));
    }

}