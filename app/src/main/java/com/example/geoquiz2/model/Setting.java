package com.example.geoquiz2.model;

public class Setting implements java.io.Serializable {
    private boolean mAnzwerbtn;
    private boolean mScrollbtn;
    private boolean mCheatbtn;
    private int mSize;
    private int mBackcolor;
    private boolean mSave;

    public boolean isAnzwerbtn() {
        return mAnzwerbtn;
    }

    public void setAnzwerbtn(boolean mAnzwerbtn) {
        this.mAnzwerbtn = mAnzwerbtn;
    }

    public boolean isScrollbtn() {
        return mScrollbtn;
    }

    public void setScrollbtn(boolean mScrollbtn) {
        this.mScrollbtn = mScrollbtn;
    }

    public boolean isCheatbtn() {
        return mCheatbtn;
    }

    public void setCheatbtn(boolean mCheatbtn) {
        this.mCheatbtn = mCheatbtn;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int mSize) {
        this.mSize = mSize;
    }

    public int getBackcolor() {
        return mBackcolor;
    }

    public void setBackcolor(int mBackcolor) {
        this.mBackcolor = mBackcolor;
    }

    public boolean isSave() {
        return mSave;
    }

    public void setSave(boolean mSave) {
        this.mSave = mSave;
    }
}
