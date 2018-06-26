package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Character extends Nobject{

    private boolean jumpcontrol;
    //Karakterin yaşayıp yaşamadığını kontrol etmek için oluşturuldu
    private boolean livecontrol;
    //Karakterin eğilme durumunu kontrol eder
    private boolean downcontrol;
    private int startedlocation;
    private double artıs;
    private int derece;


    //Yapıcı Fonksiyon
    public Character() {
        jumpcontrol = false;
        livecontrol = true;
        downcontrol = false;
        startedlocation=0;
        artıs=10;
        derece=0;
    }



    public void  jump(){

        derece+=3;
            if (derece <= 90) {
                artıs += Math.sin(Math.toRadians(derece)/180) * 500;

            }
            else if (derece <= 180) {
                artıs -= Math.sin(Math.toRadians(derece)/180) * 500;
            }

  }
    public void  power(){}

}
