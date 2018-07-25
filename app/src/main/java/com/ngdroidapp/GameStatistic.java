package com.ngdroidapp;

public class GameStatistic {
    private int myCoin;
    private int myGameCount;
    private int myWinCount;
    private int myLoseCount;
    public GameStatistic() {

    }

    public GameStatistic(int myCoin, int myGameCount, int myWinCount, int myLoseCount) {
        this.myCoin = myCoin;
        this.myGameCount = myGameCount;
        this.myWinCount = myWinCount;
        this.myLoseCount = myLoseCount;
    }

    public int getMyCoin() {
        return myCoin;
    }

    public void setMyCoin(int myCoin) {
        this.myCoin = myCoin;
    }

    public int getMyGameCount() {
        return myGameCount;
    }

    public void setMyGameCount(int myGameCount) {
        this.myGameCount = myGameCount;
    }

    public int getMyWinCount() {
        return myWinCount;
    }

    public void setMyWinCount(int myWinCount) {
        this.myWinCount = myWinCount;
    }

    public int getMyLoseCount() {
        return myLoseCount;
    }

    public void setMyLoseCount(int myLoseCount) {
        this.myLoseCount = myLoseCount;
    }
}
