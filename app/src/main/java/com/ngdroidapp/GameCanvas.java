package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;





public class GameCanvas extends BaseCanvas {

    Character karagoz,hacivat;


    Nobject nobject;

    public void setup() {
        karagoz = new Character();
        hacivat = new Character();
        nobject = new Nobject();

        nobject.setNobject(Utils.loadImage(root, "arkaplan.png"));
        karagoz.setNobject(Utils.loadImage(root,"karagoz.png"));
        hacivat.setNobject(Utils.loadImage(root,"hacivat.png"));

    }
    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void update(){




    }

    public void draw(Canvas canvas) {

        nobject.setNobjectsource(0,0,3840,2160);
        nobject.setNobjectdestination(0,0,getWidth(),getHeight());
        canvas.drawBitmap(nobject.getNobject(), nobject.getNobjectsource(), nobject.getNobjectdestination(), null);

        karagoz.setNobjectsource(0,0,2215,4892);
        karagoz.setNobjectdestination(getWidth() / 2 +200,getHeight()-karagoz.getNobjectdsth(),150,330);
        canvas.drawBitmap(karagoz.getNobject(), karagoz.getNobjectsource(), karagoz.getNobjectdestination(), null);

        hacivat.setNobjectsource(0,0,1957,5110);
        hacivat.setNobjectdestination(getWidth()/2 - 400,getHeight() - hacivat.getNobjectdsth(),150,330);
        canvas.drawBitmap(hacivat.getNobject(), hacivat.getNobjectsource(), hacivat.getNobjectdestination(), null);
    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {

        System.exit(0);
        return true;
    }

    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    public void touchDown(int x, int y, int id) {
    }

    public void touchMove(int x, int y, int id) {
    }

    public void touchUp(int x, int y, int id) {
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
