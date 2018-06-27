package com.ngdroidapp;

import android.graphics.Canvas;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;


public class MenuCanvas extends BaseCanvas {
    Nobject arkaplan,playbutton;
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

        arkaplan.setNobject(Utils.loadImage(root, "arkaplan.png"));
        karagoz.setNobject(Utils.loadImage(root, "karagoz.png"));
        hacivat.setNobject(Utils.loadImage(root, "hacivat.png"));
        playbutton.setNobject(Utils.loadImage(root, "playbutton.png"));

    }

    public void update() {



    }

    public void draw(Canvas canvas) {
        arkaplan.setNobjectsource(0,0,3840,2160);
        arkaplan.setNobjectdestination(0,0,getWidth(),getHeight());
        canvas.drawBitmap(arkaplan.getNobject(), arkaplan.getNobjectsource(), arkaplan.getNobjectdestination(), null);

        karagoz.setNobjectsource(0,0,2215,4892);
        karagoz.setNobjectdestination(getWidth() *4/6,getHeight()*1/4,300,660);
        canvas.drawBitmap(karagoz.getNobject(), karagoz.getNobjectsource(), karagoz.getNobjectdestination(), null);

        hacivat.setNobjectsource(0,0,1957,5110);
        hacivat.setNobjectdestination(getWidth()*1/6,getHeight()*1/4,300,660);
        canvas.drawBitmap(hacivat.getNobject(), hacivat.getNobjectsource(), hacivat.getNobjectdestination(), null);

        playbutton.setNobjectsource(0,0,300,122);
        playbutton.setNobjectdestination(getWidth()/2-playbutton.getNobjectdstw()/2,getHeight()/2,300,122);
        canvas.drawBitmap(playbutton.getNobject(), playbutton.getNobjectsource(), playbutton.getNobjectdestination(), null);
    }

    public void keyPressed(int key) {
    }

    public void keyReleased(int key) {
    }

    public boolean backPressed() {
        return false;
    }

    public void touchDown(int x, int y, int id) {
        touchdownx = x;
        touchdowny = y;
        if (x >= playbutton.getNobjectdstx() && x <= playbutton.getNobjectdstx() + playbutton.getNobjectdstw() && y >= playbutton.getNobjectdsty() && y <= playbutton.getNobjectdsty() + playbutton.getNobjectdsth()) {
            GameCanvas mc = new GameCanvas(root);
            root.canvasManager.setCurrentCanvas(mc);
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
