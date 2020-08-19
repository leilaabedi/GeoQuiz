package com.example.geoquiz2.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.example.geoquiz2.R;
import com.example.geoquiz2.model.Setting;

public class SettingActivity extends AppCompatActivity {
    public static final String EXTRA_IS_SETTING = "com.example.geoquiz2.isSetting";
    Button mBtn_save;
    private CheckBox ch1, ch2, ch3;
    private RadioButton mSmall, mMedium, mLarge;
    private RadioButton mRed, mBlue, mGreen, mWhite;
    Setting settingObj = new Setting();
    private boolean mfirst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findview();

        mfirst = getIntent().getBooleanExtra("First", false);

        if (mfirst == true) {
            settingObj = (Setting) getIntent().getSerializableExtra(QuizDetailFragment.EXTRA_IS_MAIN_SETTING);
            updateField();
        }

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch1.isChecked() == false)
                    settingObj.setAnzwerbtn(false);

                if (ch1.isChecked() == true)
                    settingObj.setAnzwerbtn(true);

            }
        });

        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch2.isChecked() == false)
                    settingObj.setScrollbtn(false);

                if (ch2.isChecked() == true)
                    settingObj.setScrollbtn(true);

            }
        });


        ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch3.isChecked() == false)
                    settingObj.setCheatbtn(false);

                if (ch3.isChecked() == true)
                    settingObj.setCheatbtn(true);

            }
        });


        mBtn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checked();
                settingObj.setSave(true);
                Intent intent = new Intent(SettingActivity.this, QuizDetailActivity.class);
                intent.putExtra(EXTRA_IS_SETTING, settingObj);
                setResult(RESULT_OK, intent);
                finish();
            }
        });






    }

    private void updateField() {
        if (settingObj.isAnzwerbtn()) {
            ch1.setChecked(true);
        }

        if (settingObj.isScrollbtn()) {
            ch2.setChecked(true);
        }

        if (settingObj.isCheatbtn()) {
            ch3.setChecked(true);
        }

        if (settingObj.getSize()==1 || settingObj.getSize()==2 || settingObj.getSize()==3 || settingObj.getSize()==4) {
            if (settingObj.getSize() == 1) {
                mSmall.setChecked(true);
            }
            if (settingObj.getSize() == 2) {
                mMedium.setChecked(true);
            }
            if (settingObj.getSize() == 3) {
                mLarge.setChecked(true);
            }
        }


        if (settingObj.getBackcolor() == 1 || settingObj.getBackcolor() == 2 || settingObj.getBackcolor() == 3 || settingObj.getBackcolor() == 4) {

            if (settingObj.getBackcolor() == 1) {
                mRed.setChecked(true);
            }

            if (settingObj.getBackcolor() == 2) {
                mBlue.setChecked(true);
            }

            if (settingObj.getBackcolor() == 3) {
                mGreen.setChecked(true);
            }

            if (settingObj.getBackcolor() == 4) {
                mWhite.setChecked(true);
            }

        }

    }


    private void Checked() {
        if (ch1.isChecked()) {
            settingObj.setAnzwerbtn(true);
        }

        if (ch2.isChecked()) {
            settingObj.setScrollbtn(true);
        }

        if (ch3.isChecked()) {
            settingObj.setCheatbtn(true);
        }

        if (mSmall.isChecked()) {
            settingObj.setSize(1);
        }
        if (mMedium.isChecked()) {
            settingObj.setSize(2);
        }
        if (mLarge.isChecked()) {
            settingObj.setSize(3);
        }
        if (mRed.isChecked()) {
            settingObj.setBackcolor(1);
        }
        if (mBlue.isChecked()) {
            settingObj.setBackcolor(2);
        }
        if (mGreen.isChecked()) {
            settingObj.setBackcolor(3);
        }
        if (mWhite.isChecked()) {
            settingObj.setBackcolor(4);
        }


    }

    private void findview() {
        mBtn_save = findViewById(R.id.btn_save);
        ch1 = findViewById(R.id.true_check);
        ch2 = findViewById(R.id.next_check);
        ch3 = findViewById(R.id.cheat_check);
        mSmall = findViewById(R.id.small_radio1);
        mMedium = findViewById(R.id.medium_radio2);
        mLarge = findViewById(R.id.large_radio3);
        mRed = findViewById(R.id.LightRed_radio1);
        mBlue = findViewById(R.id.LightBlue_radio2);
        mGreen = findViewById(R.id.LightGreen_radio3);
        mWhite = findViewById(R.id.White_radio3);
    }
}