package com.ngdroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.widget.Toast;

import istanbul.gamelab.ngdroid.base.BaseActivity;
import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;



public class MenuCanvas extends BaseCanvas {
    Nobject arkaplan,playbutton,backbutton;
    Character karagoz,hacivat;
    private int touchdownx, touchdowny;



    public MenuCanvas(NgApp ngApp) {
        super(ngApp);
    }


    public void setup() {
        arkaplan = new Nobject();
        karagoz = new Character();
        hacivat = new Character();
        playbutton = new Nobject();
        backbutton = new Nobject();
        setupKaragoz();
        setupHacivat();

        arkaplan.setNobject(Utils.loadImage(root, "arkaplan.png"));
        karagoz.setNobject(Utils.loadImage(root, "karagoz.png"));
        hacivat.setNobject(Utils.loadImage(root, "hacivat.png"));
        playbutton.setNobject(Utils.loadImage(root, "playbutton.png"));
        backbutton.setNobject(Utils.loadImage(root, "backbutton.png"));

    }

    /*
    public void setupKaragoz(){
        karagoz = new Character();
        karagoz.setNobjectdstw(150);
        karagoz.setNobjectdsth(330);
        karagoz.setNobjectdsty(getHeight()-karagoz.getNobjectdsth());
        karagoz.setNobjectdstx(getWidth() - karagoz.getNobjectdstw()-400);
        karagoz.setJumpcontrol(false);
        hacivat.setShoutcountrol(false);

    }
    public void setupHacivat(){
        hacivat = new Character();
        hacivat.setNobjectdstw(150);
        hacivat.setNobjectdsth(330);
        hacivat.setNobjectdsty(getHeight() - hacivat.getNobjectdsth());
        hacivat.setNobjectdstx(300);
        hacivat.setJumpcontrol(false);
        hacivat.setShoutcountrol(false);
    }

    public void transformKaragoz(){
        if(karagoz.getNobjectdstw() > 150)
        karagoz.setNobjectdstw(karagoz.getNobjectdstw() - karagoz.getNobjectdstw() / 60);
        if(karagoz.getNobjectdsth() > 330)
        karagoz.setNobjectdsth(karagoz.getNobjectdsth() - karagoz.getNobjectdsth() / 60);
        if(karagoz.getNobjectdstx() > getWidth() - 550 )
        karagoz.setNobjectdstx(karagoz.getNobjectdstx() - 5);
        if(karagoz.getNobjectdsty() < getHeight() - 330)
        karagoz.setNobjectdsty(karagoz.getNobjectdsty() + 10);
    } */
    public void setupKaragoz(){
        karagoz = new Character();
        karagoz.setNobjectdstw(300);
        karagoz.setNobjectdsth(660);
        karagoz.setNobjectdsty(getWidth() / 8);
        karagoz.setNobjectdstx(getWidth() * 4 / 6);
        karagoz.setJumpcontrol(false);
        hacivat.setShoutcountrol(false);

    }
    public void setupHacivat(){
        hacivat = new Character();
        hacivat.setNobjectdstw(300);
        hacivat.setNobjectdsth(660);
        hacivat.setNobjectdsty(getWidth() / 8);
        hacivat.setNobjectdstx(getHeight() / 6 + hacivat.getNobjectdstw() / 2);
        hacivat.setJumpcontrol(false);
        hacivat.setShoutcountrol(false);
    }

    public void update() {


    }

    public void draw(Canvas canvas) {
        arkaplan.setNobjectsource(0,0,3840,2160);
        arkaplan.setNobjectdestination(0,0,getWidth(),getHeight());
        canvas.drawBitmap(arkaplan.getNobject(),  arkaplan.getNobjectsource(), arkaplan.getNobjectdestination(), null);

        karagoz.setNobjectsource(0,0,2215,4892);
        karagoz.setNobjectdestination(karagoz.getNobjectdstx(),karagoz.getNobjectdsty(),karagoz.getNobjectdstw(),karagoz.getNobjectdsth());
        canvas.drawBitmap(karagoz.getNobject(), karagoz.getNobjectsource(), karagoz.getNobjectdestination(), null);


        hacivat.setNobjectsource(0,0,1957,5110);
        hacivat.setNobjectdestination(hacivat.getNobjectdstx(),hacivat.getNobjectdsty(),hacivat.getNobjectdstw(),hacivat.getNobjectdsth());
        canvas.drawBitmap(hacivat.getNobject(), hacivat.getNobjectsource(), hacivat.getNobjectdestination(), null);

        playbutton.setNobjectsource(0,0,300,122);
        playbutton.setNobjectdestination(getWidth()/2-playbutton.getNobjectdstw()/2,getHeight()/2-playbutton.getNobjectdsth()/2,600,244);
        canvas.drawBitmap(playbutton.getNobject(), playbutton.getNobjectsource(), playbutton.getNobjectdestination(), null);

        backbutton.setNobjectsource(0,0,256,256);
        backbutton.setNobjectdestination(getWidth()-backbutton.getNobjectdstw(),0,256,256);
        canvas.drawBitmap(backbutton.getNobject(), backbutton.getNobjectsource(), backbutton.getNobjectdestination(), null);
    }

    public void keyPressed(int key) {
    }

    public void keyReleased(int key) {
    }

    public boolean backPressed() {
        System.exit(0);
        return false;
    }

    public void touchDown(int x, int y, int id) {
        touchdownx = x;
        touchdowny = y;
        if (x >= playbutton.getNobjectdstx() && x <= playbutton.getNobjectdstx() + playbutton.getNobjectdstw() && y >= playbutton.getNobjectdsty() && y <= playbutton.getNobjectdsty() + playbutton.getNobjectdsth()) {

            GameCanvas mc = new GameCanvas(root);
            root.canvasManager.setCurrentCanvas(mc);
        }
        if (x >= backbutton.getNobjectdstx() && x <= backbutton.getNobjectdstx() + backbutton.getNobjectdstw() && y >= backbutton.getNobjectdsty() && y <= backbutton.getNobjectdsty() + backbutton.getNobjectdsth()) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(root.activity);
        builder1.setTitle("Programdan Çıkılsın Mı?").setCancelable(false).setPositiveButton("Evet", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) { //Eğer evet butonuna basılırsa
                System.exit(0);


            }
        }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
//Eğer hayır butonuna basılırsa

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder1.show();
    }
}





                public void touchMove(int x, int y, int id) {
                }

                public void touchUp(int x, int y, int id) {
                }

                public void surfaceChanged(int width, int height) {
                }

                public void surfaceCreated() {
                }

                public void surfaceDestroyed() {
                }

                public void pause() {
                }

                public void resume() {
                }

                public void reloadTextures() {
                }

                public void showNotify() {
                }

                public void hideNotify() {
                }


}