package com.ngdroidapp;

public class PlayerData {
    private int health;
    private boolean jumpcontrol, shoutcontrol, livecontrol;
    private int damagecount;
    private int hitcount;
    private int misscount;
    private int skill;
    private int choosenfruit;
    private String userid;
    private boolean gamecontrol;
    private boolean ready;
    private int time;
    private int whichcharacter;

    public PlayerData(int health, boolean jumpcontrol, boolean shoutcontrol, boolean livecontrol, int damagecount, int hitcount, int misscount, int skill, int choosenfruit, String userid, boolean gamecontrol, boolean ready, int time,int whichcharacter) {
        this.health = health;
        this.jumpcontrol = jumpcontrol;
        this.shoutcontrol = shoutcontrol;
        this.livecontrol = livecontrol;
        this.damagecount = damagecount;
        this.hitcount = hitcount;
        this.misscount = misscount;
        this.skill = skill;
        this.choosenfruit = choosenfruit;
        this.userid = userid;
        this.gamecontrol = gamecontrol;
        this.ready = ready;
        this.time = time;
        this.whichcharacter = whichcharacter;
    }

    public int getWhichcharacter() {
        return whichcharacter;
    }

    public void setWhichcharacter(int whichcharacter) {
        this.whichcharacter = whichcharacter;
    }

    public PlayerData() {
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isJumpcontrol() {
        return jumpcontrol;
    }

    public void setJumpcontrol(boolean jumpcontrol) {
        this.jumpcontrol = jumpcontrol;
    }

    public boolean isShoutcontrol() {
        return shoutcontrol;
    }

    public void setShoutcontrol(boolean shoutcontrol) {
        this.shoutcontrol = shoutcontrol;
    }

    public boolean isLivecontrol() {
        return livecontrol;
    }

    public void setLivecontrol(boolean livecontrol) {
        this.livecontrol = livecontrol;
    }

    public int getDamagecount() {
        return damagecount;
    }

    public void setDamagecount(int damagecount) {
        this.damagecount = damagecount;
    }

    public int getHitcount() {
        return hitcount;
    }

    public void setHitcount(int hitcount) {
        this.hitcount = hitcount;
    }

    public int getMisscount() {
        return misscount;
    }

    public void setMisscount(int misscount) {
        this.misscount = misscount;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getChoosenfruit() {
        return choosenfruit;
    }

    public void setChoosenfruit(int choosenfruit) {
        this.choosenfruit = choosenfruit;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean isGamecontrol() {
        return gamecontrol;
    }

    public void setGamecontrol(boolean gamecontrol) {
        this.gamecontrol = gamecontrol;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
