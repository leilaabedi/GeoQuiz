package com.example.geoquiz2.model;



public class Question implements java.io.Serializable {
    private int mQuestionTextResId;
    private boolean mIsAnswerTrue;
    private int mIsAnswered;
    private boolean mIsCheated;

    public int getQuestionTextResId() {
        return mQuestionTextResId;
    }

    public void setQuestionTextResId(int questionTextResId) {
        mQuestionTextResId = questionTextResId;
    }

    public boolean isAnswerTrue() {
        return mIsAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mIsAnswerTrue = answerTrue;
    }

    public Question(int questionTextResId, boolean isAnswerTrue,int isAnswered,boolean IsCheated) {
        mQuestionTextResId = questionTextResId;
        mIsAnswerTrue = isAnswerTrue;
        mIsAnswered=isAnswered;
        mIsCheated=IsCheated;
    }

    public int isIsAnswered() {
        return mIsAnswered;
    }

    public void setIsAnswered(int IsAnswered) {
        this.mIsAnswered = IsAnswered;
    }

    public Question() {
    }

    public boolean isIsCheated() {
        return mIsCheated;
    }

    public void setIsCheated(boolean mIsCheated) {
        this.mIsCheated = mIsCheated;
    }
}
