package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Rect;

class NobjectCanvas {
    private Bitmap Nobject;
    //Source ve destination tanımlaması
    private Rect Nobjectsource, Nobjectdestination;
    //objenin genişlik ve yüksekliği
    private int Nobjectsrcw, Nobjectsrch, Nobjectdstw, Nobjectdsth;
    //Karakterin konumu x/y
    private int Nobjectsrcx, Nobjectsrcy, Nobjectdstx, Nobjectdsty;

    //Karakterin source bilgilerini set eder.
    public void setNobjectsource(int Nobjectsrcx, int Nobjectsrcy, int Nobjectsrcw, int Nobjectsrch){
        this.Nobjectsrcx = Nobjectsrcx;
        this.Nobjectsrcy = Nobjectsrcy;
        this.Nobjectsrcw = Nobjectsrcw;
        this.Nobjectsrch = Nobjectsrch;

    }
    //Karakterin destination degerlerini set eder
    public void setNobjectdestination(int Nobjectdstx, int Nobjectdsty, int Nobjectdstw, int Nobjectdsth){
        this.Nobjectdstx = Nobjectdstx;
        this.Nobjectdsty = Nobjectdsty;
        this.Nobjectdstw = Nobjectdstw;
        this.Nobjectdsth = Nobjectdsth;
    }
    //Diger sınıflarda kullanmak icin source degerının getter i
    public Rect getNobjectsource(){
        Nobjectsource.set(Nobjectsrcx , Nobjectsrcy, Nobjectsrcx + Nobjectsrcw, Nobjectsrcy + Nobjectsrch);
        return Nobjectsource;
    }

    //    //Diger sınıflarda kullanmak icin source degerının getter i
    public Rect getNobjectdestination() {
        Nobjectdestination.set(Nobjectdstx , Nobjectdsty, Nobjectdstx + Nobjectdstw, Nobjectdsty + Nobjectdsth);
        return Nobjectdestination;
    }
}

