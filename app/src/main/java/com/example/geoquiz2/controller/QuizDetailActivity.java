package com.example.geoquiz2.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geoquiz2.R;
import com.example.geoquiz2.model.Question;
import com.example.geoquiz2.model.Setting;

public class QuizDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_detail);
     /*   if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState: " + savedInstanceState);
            mCurrentIndex = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_INDEX, 0);
            mQuestionBank = (Question[]) savedInstanceState.getSerializable(BUNDLE_KEY_MQUESTION_BANK);
            score = savedInstanceState.getInt(BUNDLE_KEY_SCORE);
          //  mSetting=(Setting) savedInstanceState.getSerializable(BUNDLE_KEY_Setting );
        //    if (mFistSetting==true) ChangeSetting();

        } else
            Log.d(TAG, "savedInstanceState is NULL!!");

        findViews();
        setListeners();
        updateQuestion();


      */

        FragmentManager fragmentManager = getSupportFragmentManager();

        //check if fragment exists in container (configuration changes save the fragments)
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        //create an add fragment transaction for CrimeDetailFragment
        if (fragment == null) {
            QuizDetailFragment quizDetailFragment = new QuizDetailFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, quizDetailFragment)
                    .commit();
        }



    }

}



