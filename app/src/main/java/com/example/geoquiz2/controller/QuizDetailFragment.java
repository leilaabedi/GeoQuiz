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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geoquiz2.R;
import com.example.geoquiz2.model.Question;
import com.example.geoquiz2.model.Setting;

public class QuizDetailFragment extends Fragment {
    private Setting mSetting;
    private boolean mFistSetting = false;
    private boolean mIsCheater = false;
    public static final String BUNDLE_KEY_MQUESTION_BANK = "mbank";
    public static final String BUNDLE_KEY_Setting = "mSetting";
    private static final String TAG = "MainActivity";
    private static final String BUNDLE_KEY_CURRENT_INDEX = "currentIndex";
    public static final String BUNDLE_KEY_SCORE = "score";
    public static final int REQUEST_CODE_CHEAT = 0;
    public static final int REQUEST_CODE_ANSWER = 1;
    public static final String EXTRA_QUESTION_ANSWER = "com.example.geoquiz2.questionAnswer";
    public static final String EXTRA_IS_MAIN_SETTING = "com.example.geoquiz2.setting";
    private TextView mTextViewQuestion;
    private TextView mtxtScore;
    private ImageButton mButtonTrue;
    private ImageButton mButtonFalse;
    private ImageButton mButtonNext;
    private ImageButton mButtonPrev;
    private ImageButton mButtonLast;
    private ImageButton mButtonFirst;
    private LinearLayout mFinallay;
    private LinearLayout mMainLayout;
    private Button mButtonSetting;
    private Button mButtonCheat;
    private Button mButtonRst;
    private TextView mScore;
    private int score = 0;
    private LinearLayout layou1;

    private int mCurrentIndex = 0;
    private Question[] mQuestionBank = {
            new Question(R.string.question_australia, false, 0, false),
            new Question(R.string.question_oceans, true, 0, false),
            new Question(R.string.question_mideast, false, 0, false),
            new Question(R.string.question_africa, true, 0, false),
            new Question(R.string.question_americas, false, 0, false),
            new Question(R.string.question_asia, false, 0, false)
    };







    public QuizDetailFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_quiz_detail, container, false);

        View view = inflater.inflate(R.layout.fragment_quiz_detail, container, false);
        findViews(view);
        setListeners();
        updateQuestion();


        return view;

    }




    private void findViews(View view) {
        mTextViewQuestion = view.findViewById(R.id.txtview_question_text);
        mButtonTrue = view.findViewById(R.id.btn_true);
        mButtonFalse = view.findViewById(R.id.btn_false);
        mButtonPrev = view.findViewById(R.id.btn_back1);
        mButtonNext = view.findViewById(R.id.btn_next1);
        mScore = view.findViewById(R.id.score);
        mtxtScore = view.findViewById(R.id.textScore);
        mFinallay = view.findViewById(R.id.finalLayout);
        mButtonLast = view.findViewById(R.id.btn_last);
        mButtonFirst = view.findViewById(R.id.btn_first);
        mMainLayout = view.findViewById(R.id.main1);
        mButtonRst = view.findViewById(R.id.reset_btn);
        mFinallay.setVisibility(LinearLayout.GONE);
        mButtonCheat = view.findViewById(R.id.btn_cheat);
        mButtonSetting = view.findViewById(R.id.btn_setting);
        layou1 = view.findViewById(R.id.root);
    }

    private void setListeners() {
        mButtonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), SettingActivity.class);
                intent1.putExtra("First", mFistSetting);
                if (mFistSetting == true) {
                    intent1.putExtra(EXTRA_IS_MAIN_SETTING, mSetting);
                }
                startActivityForResult(intent1, REQUEST_CODE_ANSWER);
                mFistSetting = true;

            }
        });


        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
                mIsCheater = false;
            }
        });

        mButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length;
                updateQuestion();
                mIsCheater = false;
            }
        });

        mButtonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = 0;
                updateQuestion();


            }
        });

        mButtonLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = mQuestionBank.length - 1;
                updateQuestion();
            }
        });

        mButtonRst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = 0;
                score = 0;
                for (int i = 0; i < mQuestionBank.length; i++) {
                    mQuestionBank[i].setIsAnswered(0);
                }
                mtxtScore.setText("");

                mMainLayout.setVisibility(LinearLayout.VISIBLE);
                mFinallay.setVisibility(LinearLayout.GONE);
                updateQuestion();
            }
        });


        mButtonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheatDetailActivity.class);
                intent.putExtra(EXTRA_QUESTION_ANSWER, mQuestionBank[mCurrentIndex].isAnswerTrue());
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });


    }

    private void updateQuestion() {
        int questionTextResId = mQuestionBank[mCurrentIndex].getQuestionTextResId();
        mTextViewQuestion.setText(questionTextResId);
        mtxtScore.setText("score " + Integer.toString(score));

    }


    private void checkAnswer(boolean userPressed) {
        int i, count = 0;
        if (mQuestionBank[mCurrentIndex].isIsCheated() == true) {
            Toast.makeText(getActivity(), R.string.toast_judgment, Toast.LENGTH_LONG).show();
            mQuestionBank[mCurrentIndex].setIsAnswered(2);
        } else {


            if (mQuestionBank[mCurrentIndex].isIsAnswered() == 0) {
                if (mQuestionBank[mCurrentIndex].isAnswerTrue() == userPressed) {
                    Toast.makeText(getActivity(), R.string.toast_correct, Toast.LENGTH_LONG)
                            .show();
                    mQuestionBank[mCurrentIndex].setIsAnswered(1);
                    score++;
                    mtxtScore.setText("score " + Integer.toString(score));

                } else {
                    Toast.makeText(getActivity(), R.string.toast_incorrect, Toast.LENGTH_SHORT)
                            .show();
                    mQuestionBank[mCurrentIndex].setIsAnswered(2);
                    mtxtScore.setText("score " + Integer.toString(score));
                }

                if (mCurrentIndex == mQuestionBank.length - 1) {

                    for (i = 0; i < mQuestionBank.length; i++) {
                        if (mQuestionBank[i].isIsAnswered() == 1 || mQuestionBank[i].isIsAnswered() == 2) {
                            count++;
                        }
                    }
                    if (count == mQuestionBank.length) {
                        mMainLayout.setVisibility(LinearLayout.GONE);
                        mFinallay.setVisibility(LinearLayout.VISIBLE);
                        mScore.setText("score " + Integer.toString(score));

                    }
                }


            }
        }
    }


    @Override
   public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_CHEAT) {
            mIsCheater = data.getBooleanExtra(CheatDetailFragment.EXTRA_IS_CHEAT, false);
            mQuestionBank[mCurrentIndex].setIsCheated(mIsCheater);
        }
        if (requestCode == REQUEST_CODE_ANSWER) {
            Bundle extra;
            extra = data.getExtras();
            if (extra != null) {
                mSetting = (Setting) data.getSerializableExtra(SettingActivity.EXTRA_IS_SETTING);
            }
            ChangeSetting();
        }

    }


    private void ChangeSetting() {
        if (mSetting.isAnzwerbtn()) {
            mButtonTrue.setVisibility(View.INVISIBLE);
            mButtonFalse.setVisibility(View.INVISIBLE);
        } else {
            mButtonTrue.setVisibility(View.VISIBLE);
            mButtonFalse.setVisibility(View.VISIBLE);
        }

        if (mSetting.isScrollbtn()) {
            mButtonNext.setVisibility(View.INVISIBLE);
            mButtonLast.setVisibility(View.INVISIBLE);
            mButtonFirst.setVisibility(View.INVISIBLE);
            mButtonPrev.setVisibility(View.INVISIBLE);
        } else {

            mButtonNext.setVisibility(View.VISIBLE);
            mButtonLast.setVisibility(View.VISIBLE);
            mButtonFirst.setVisibility(View.VISIBLE);
            mButtonPrev.setVisibility(View.VISIBLE);
        }

        if (mSetting.isCheatbtn()) {
            mButtonCheat.setVisibility(View.INVISIBLE);
        } else {
            mButtonCheat.setVisibility(View.VISIBLE);
        }

        if (mSetting.getBackcolor() == 1 || mSetting.getBackcolor() == 2 || mSetting.getBackcolor() == 3 || mSetting.getBackcolor() == 4) {

            if (mSetting.getBackcolor() == 1) {
                layou1.setBackgroundColor(Color.RED);
            }

            if (mSetting.getBackcolor() == 2) {
                layou1.setBackgroundColor(Color.BLUE);
            }

            if (mSetting.getBackcolor() == 3) {
                layou1.setBackgroundColor(Color.GREEN);
            }

            if (mSetting.getBackcolor() == 4) {
                layou1.setBackgroundColor(Color.WHITE);
            }

        } else if (mFistSetting == true) {
            layou1.setBackgroundColor(Color.WHITE);
        }

        if (mSetting.getSize() == 1) {
            mTextViewQuestion.setTextSize(14);
        }

        if (mSetting.getSize() == 2) {
            mTextViewQuestion.setTextSize(18);
        }

        if (mSetting.getSize() == 3) {
            mTextViewQuestion.setTextSize(26);
        }


    }


    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY_CURRENT_INDEX, mCurrentIndex);
        outState.putSerializable(BUNDLE_KEY_MQUESTION_BANK, mQuestionBank);
        outState.putInt(BUNDLE_KEY_SCORE, score);

        //  outState.putSerializable(BUNDLE_KEY_Setting, mSetting);


    }



}