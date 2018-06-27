package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Character extends Nobject{
    //Karakterin Sağlık durumunu tutar
    private int health;
    //Karakterin kaç kez hasar aldığını tutar
    private int damagecount;
    //Karakterin kaç kez hasar verdiğini tutar
    private int hitcount;
    //Karakterin kaç kez ıskaladığını tutar
    private int misscount;

    private boolean jumpcontrol;
    //Karakterin yaşayıp yaşamadığını kontrol etmek için oluşturuldu
    private boolean livecontrol;
    //Karakterin eğilme durumunu kontrol eder
    private boolean downcontrol;
    //Karakterin dikey doğrultudaki ivmesi
    private double charAcceleration;
    //Sinüs dalgası için kullanılacak derece charAcceleration yönünü belirleyecek
    private int derece;

    public Character() {
        health = 100;
        damagecount = 0;
        hitcount = 0;
        misscount = 0;
        jumpcontrol = false;
        livecontrol = true;
        downcontrol = false;
        charAcceleration = 0;
        derece=0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public boolean isJumpcontrol() {
        return jumpcontrol;
    }

    public void setJumpcontrol(boolean jumpcontrol) {
        this.jumpcontrol = jumpcontrol;
    }

    public boolean isLivecontrol() {
        return livecontrol;
    }

    public void setLivecontrol(boolean livecontrol) {
        this.livecontrol = livecontrol;
    }

    public boolean isDowncontrol() {
        return downcontrol;
    }

    public void setDowncontrol(boolean downcontrol) {
        this.downcontrol = downcontrol;
    }

    public double getCharAcceleration() {
        return charAcceleration;
    }

    public void setCharAcceleration(double charAcceleration) {
        this.charAcceleration = charAcceleration;
    }

    public int getDerece() {
        return derece;
    }

    public void setDerece(int derece) {
        this.derece = derece;
    }

    public void jump(){
        if(livecontrol&&!downcontrol&&jumpcontrol){
            derece+=6;
            if (derece <= 90) {
                charAcceleration -= Math.sin(Math.toRadians(derece)/180) * 500;
            }
            else if (derece <= 180) {
                charAcceleration += Math.sin(Math.toRadians(derece)/180) * 500;
            }else{
                jumpcontrol =false;
                derece = 0;
                charAcceleration=10;
            }
           setNobjectdsty(getNobjectdsty()+(int)charAcceleration);

        }
    }
    public void  power(){}
}
