package com.example.geoquiz2.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geoquiz2.R;
import com.example.geoquiz2.model.Setting;

import static android.app.Activity.RESULT_OK;

public class CheatDetailFragment extends Fragment {
    public static final String EXTRA_IS_CHEAT = "com.example.geoquiz.isCheat";
    private TextView mTextViewAnswer;
    private Button mButtonShowAnswer;
    private boolean mIsAnswerTrue;

    public CheatDetailFragment() {
        // Required empty public constructor
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cheat_detail, container, false);

        mIsAnswerTrue =getActivity().getIntent().getBooleanExtra(QuizDetailFragment.EXTRA_QUESTION_ANSWER, false);
        findViews(view);



        setListeners();


        return view;



    }

    private void findViews(View view) {
        mTextViewAnswer = view.findViewById(R.id.txtview_answer);
        mButtonShowAnswer = view.findViewById(R.id.btn_show_answer);
    }

    private void setListeners() {
        mButtonShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAnswerTrue)
                    mTextViewAnswer.setText(R.string.button_true);
                else
                    mTextViewAnswer.setText(R.string.button_false);

                setShownAnswerResult(true);


            }
        });
    }




    private void setShownAnswerResult(boolean isCheat) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IS_CHEAT, isCheat);


        getActivity().setResult(RESULT_OK, intent);
    }











}