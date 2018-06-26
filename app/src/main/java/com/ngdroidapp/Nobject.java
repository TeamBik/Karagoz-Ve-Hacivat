package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Rect;

class Nobject {

    private Bitmap Nobject;
    //Source ve destination tanımlaması
    private Rect Nobjectsource, Nobjectdestination;
    //objenin genişlik ve yüksekliği
    private int Nobjectsrcw, Nobjectsrch, Nobjectdstw, Nobjectdsth;
    //Karakterin konumu x/y
    private int Nobjectsrcx, Nobjectsrcy, Nobjectdstx, Nobjectdsty;
    public Nobject(){
        Nobjectsource = new Rect();
        Nobjectdestination = new Rect();

    }
    public Bitmap getNobject() {
        return Nobject;
    }

    public void setNobject(Bitmap nobject) {
        Nobject = nobject;
    }

    //Karakterin source bilgilerini set eder.
    public void setNobjectsource(int Nobjectsrcx, int Nobjectsrcy, int Nobjectsrcw, int Nobjectsrch){
        this.Nobjectsrcx = Nobjectsrcx;
        this.Nobjectsrcy = Nobjectsrcy;
        this.Nobjectsrcw = Nobjectsrcw;
        this.Nobjectsrch = Nobjectsrch;
        Nobjectsource.set(Nobjectsrcx , Nobjectsrcy, Nobjectsrcx + Nobjectsrcw, Nobjectsrcy + Nobjectsrch);

    }
    //Karakterin destination degerlerini set eder
    public void setNobjectdestination(int Nobjectdstx, int Nobjectdsty, int Nobjectdstw, int Nobjectdsth){
        this.Nobjectdstx = Nobjectdstx;
        this.Nobjectdsty = Nobjectdsty;
        this.Nobjectdstw = Nobjectdstw;
        this.Nobjectdsth = Nobjectdsth;
        Nobjectdestination.set(Nobjectdstx , Nobjectdsty, Nobjectdstx + Nobjectdstw, Nobjectdsty + Nobjectdsth);

    }
    //Karakterin y düzlemindeki konumunu set eder.
    public void setNobjectdsty(int Nobjectdsty) {
        this.Nobjectdsty = Nobjectdsty;
    }

    //Diger sınıflarda kullanmak icin source degerının getter i
    public Rect getNobjectsource(){
        return Nobjectsource;
    }

    //    //Diger sınıflarda kullanmak icin source degerının getter i
    public Rect getNobjectdestination() {
        return Nobjectdestination;
    }
    //Karekterin resimdeki genisligi dondurur
    public int getNobjectsrcw() {
        return Nobjectsrcw;
    }
    //Karaterin resimdeki genisligini set etmemisi saglar
    public void setNobjectsrcw(int nobjectsrcw) {
        Nobjectsrcw = nobjectsrcw;
    }
    //Karakterin resimdeki yuksekligini dondurur
    public int getNobjectsrch() {
        return Nobjectsrch;
    }
    //Karakterin resimdeki yuksekligini set etmemizi saglar
    public void setNobjectsrch(int nobjectsrch) {
        Nobjectsrch = nobjectsrch;
    }
    //Karakterin ekranda cizilecegi genisligi dondurur.
    public int getNobjectdstw() {
        return Nobjectdstw;
    }
    //Karakterin ekranda cızılecegı genisligi set etmemizi saglar.
    public void setNobjectdstw(int nobjectdstw) {
        Nobjectdstw = nobjectdstw;
    }
    //Karakterin ekranda cizilecegi yukseklıgı  dondurur
    public int getNobjectdsth() {
        return Nobjectdsth;
    }
    //Karakterin ekranda cizilecegi yukseklıgı  set etmemizi saglar
    public void setNobjectdsth(int nobjectdsth) {
        Nobjectdsth = nobjectdsth;
    }
    //Karakterin ekranda cizilecegi yukseklıgı  set etmemizi saglar
    public int getNobjectsrcx() {
        return Nobjectsrcx;
    }
    //Karakterin ekranda cizilecegi x kordinatını  set etmemizi saglar
    public void setNobjectsrcx(int nobjectsrcx) {
        Nobjectsrcx = nobjectsrcx;
    }
    //Karakterin resimdeki y kordınatını  geri dondurur.
    public int getNobjectsrcy() {
        return Nobjectsrcy;
    }
    //Karakterin resimdeki y kordınatını set etmemizi saglar
    public void setNobjectsrcy(int nobjectsrcy) {
        Nobjectsrcy = nobjectsrcy;
    }
    //Karakterin ekranda cizilecegi x kordınatını  dondurur.
    public int getNobjectdstx() {
        return Nobjectdstx;
    }
    //Karakterin ekranda cizilecegi yukseklıgı  set etmemizi saglar
    public void setNobjectdstx(int nobjectdstx) {
        Nobjectdstx = nobjectdstx;
    }
    //karakterin cızılecegi y  kordınatını dondurur.
    public int getNobjectdsty() {
        return Nobjectdsty;
    }

}


