package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;





public class GameCanvas extends BaseCanvas {
    Nobject nobject;

    double artıs = 10;
    public void setup() {
    nobject = new Nobject();
      nobject.setNobject(Utils.loadImage(root, "arkaplan.png"));
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

    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
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
