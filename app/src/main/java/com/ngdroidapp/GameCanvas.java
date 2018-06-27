package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;





public class GameCanvas extends BaseCanvas {

<<<<<<< Updated upstream
    Character karagoz, hacivat;
    Animations animKaragoz, animHacivat;
    Nobject arkaplan, obje1;
=======
    Character karagoz,hacivat;
    Nobject arkaplan,obje1;
    Animations karagozanim=new Animations(karagoz);
    Animations hacivatanim=new Animations(hacivat);
>>>>>>> Stashed changes

    public void setup() {
        karagoz = new Character();
        hacivat = new Character();
        arkaplan = new Nobject();
        obje1 = new Nobject();
        animKaragoz = new Animations(karagoz);
        animHacivat = new Animations(hacivat);

        obje1.setNobject(Utils.loadImage(root,"orange.png"));
        arkaplan.setNobject(Utils.loadImage(root, "arkaplan.png"));
        karagoz.setNobject(Utils.loadImage(root,"karagoz.png"));
        hacivat.setNobject(Utils.loadImage(root,"hacivat.png"));

    }

    public GameCanvas(NgApp ngApp) {
        super(ngApp);

    }

    public void update(){
        if(hacivat.isLivecontrol()){
            AiPlayer(hacivat,animHacivat);
        }

    }
    public void draw(Canvas canvas) {
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
        arkaplan.setNobjectsource(0,0,3840,2160);
        arkaplan.setNobjectdestination(0,0,getWidth(),getHeight());
        canvas.drawBitmap(arkaplan.getNobject(), arkaplan.getNobjectsource(), arkaplan.getNobjectdestination(), null);

        karagoz.setNobjectsource(0,0,2215,4892);
        karagoz.setNobjectdestination(getWidth() - karagoz.getNobjectdstw()-400,getHeight()-karagoz.getNobjectdsth(),150,330);
        canvas.drawBitmap(karagoz.getNobject(), karagoz.getNobjectsource(), karagoz.getNobjectdestination(), null);

        hacivat.setNobjectsource(0,0,1957,5110);
        hacivat.setNobjectdestination(300,getHeight() - hacivat.getNobjectdsth(),150,330);
        canvas.drawBitmap(hacivat.getNobject(), hacivat.getNobjectsource(), hacivat.getNobjectdestination(), null);

        obje1.setNobjectsource(0,0,757,720);
<<<<<<< Updated upstream
        obje1.setNobjectdestination(hacivat.getNobjectdstx() + 75,hacivat.getNobjectdsty() - obje1.getNobjectdsth(),15,30);
=======
        obje1.setNobjectdestination(karagoz.getNobjectdstx()+75,karagoz.getNobjectdsty() - obje1.getNobjectdsth(),15,30);
>>>>>>> Stashed changes
        canvas.drawBitmap(obje1.getNobject(), obje1.getNobjectsource(), obje1.getNobjectdestination(), null);

    }
    public void AiPlayerMode(Character character){

    }
    public void AiPlayer(Character character,Animations animCharacter) {
        //if(karakterin canı 80 den büyük ise)
        //if()
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
