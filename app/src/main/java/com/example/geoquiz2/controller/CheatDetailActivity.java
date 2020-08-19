package com.example.geoquiz2.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.geoquiz2.R;

public class CheatDetailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat_detail);

        FragmentManager fragmentManager = getSupportFragmentManager();

        //check if fragment exists in container (configuration changes save the fragments)
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_cheat);

        //create an add fragment transaction for CrimeDetailFragment
        if (fragment == null) {
            CheatDetailFragment quizDetailFragment = new CheatDetailFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_cheat, quizDetailFragment)
                    .commit();
        }



    }


}