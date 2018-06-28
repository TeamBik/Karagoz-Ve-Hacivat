package com.ngdroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;





public class GameCanvas extends BaseCanvas {
    //Değişkenler
    private boolean ShoutControl=false;
    Character karagoz, hacivat;
    Animations animKaragoz, animHacivat;
    Nobject arkaplan, obje1,obje2, backbutton, restart, fire, jump;
    private int touchdownx, touchdowny;


    public void setup() {
        karagoz = new Character();
        arkaplan = new Nobject();
        obje1 = new Nobject();
        backbutton = new Nobject();
        restart = new Nobject();
        fire = new Nobject();
        jump = new Nobject();

        setupHacivat();
        setupKaragoz();
        setupObjectHacivat();
        setupObjectKaragoz();
        animKaragoz = new Animations(karagoz,hacivat,obje2);
        animHacivat = new Animations(hacivat,karagoz,obje1);
        //RESİMLERİN TANINMASI

        obje1.setNobject(Utils.loadImage(root,"orange.png"));
        obje2.setNobject(Utils.loadImage(root,"orange.png"));
        arkaplan.setNobject(Utils.loadImage(root, "arkaplan.png"));
        karagoz.setNobject(Utils.loadImage(root,"karagoz.png"));
        hacivat.setNobject(Utils.loadImage(root,"hacivat.png"));
        backbutton.setNobject(Utils.loadImage(root,"backbutton.png"));
        restart.setNobject(Utils.loadImage(root,"restart.png"));
        jump.setNobject(Utils.loadImage(root,"jump.png"));
        fire.setNobject(Utils.loadImage(root,"fire.png"));

    }
    //OBJE,KARAKTER SETUPLARI
    public void setupKaragoz(){
        karagoz = new Character();
        karagoz.setNobjectdstw(150);
        karagoz.setNobjectdsth(330);
        karagoz.setNobjectdsty(getHeight()-karagoz.getNobjectdsth());
        karagoz.setNobjectdstx(getWidth() - karagoz.getNobjectdstw()-400);

        karagoz.setJumpcontrol(false);
    }
    public void setupHacivat(){
        hacivat = new Character();
        hacivat.setNobjectdsty(getHeight() - 330);
        hacivat.setNobjectdstx(300);
        hacivat.setNobjectdstw(150);
        hacivat.setNobjectdsth(330);
        hacivat.setJumpcontrol(false);
    }
    public void setupObjectHacivat() {
        obje1 = new Nobject();
        obje1.setNobjectdsty(hacivat.getNobjectdsty()+hacivat.getNobjectdsth()/2);
        obje1.setNobjectdstx(hacivat.getNobjectdstx()+hacivat.getNobjectdstw());
        obje1.setNobjectdstw(25);
        obje1.setNobjectdsth(30);
    }

    public void setupObjectKaragoz() {
        obje2 = new Nobject();
        obje2.setNobjectdstw(150);
        obje2.setNobjectdsth(330);
        obje2.setNobjectdsty(getHeight() - karagoz.getNobjectdsth());
        obje2.setNobjectdstx(getWidth() - karagoz.getNobjectdstw() - 25);

        obje2.setNobjectdstw(25);
        obje2.setNobjectdsth(30);
        obje2.setNobjectdsty(karagoz.getNobjectdsty()+karagoz.getNobjectdsth()/2);
        obje2.setNobjectdstx(karagoz.getNobjectdstx()-karagoz.getNobjectdstw());
    }
    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void update(){
        if(karagoz.isJumpcontrol())KaragozJump();
        if (hacivat.isJumpcontrol()) HacivatJump();

        }
        //ZIPLAMA KOMUT

        hacivat.setNobjectdsty(getHeight() - 300);
        //ATIŞ KONTROL TRUE
         karagoz.setShoutControl(true);

        //ATEŞ ETTİ Mİ?
        if(hacivat.isShoutControl())
        {
            animHacivat.ShoutAnımationHacivat();

        }
        if(karagoz.isShoutControl())
        {
            animKaragoz.ShoutAnımationKaragoz();

        }
        //ATEŞ ANİMASYON DURUM
        if(animHacivat.ShoutAnımationHacivat()) {
            hacivat.setShoutControl(false);

        }
        if(animKaragoz.ShoutAnımationKaragoz())
        {
            karagoz.setShoutControl(false);
        }

    }
    public void draw(Canvas canvas) {
        arkaplan.setNobjectsource(0,0,3840,2160);
        arkaplan.setNobjectdestination(0,0,getWidth(),getHeight());
        canvas.drawBitmap(arkaplan.getNobject(), arkaplan.getNobjectsource(), arkaplan.getNobjectdestination(), null);

        karagoz.setNobjectsource(0,0,2215,4892);
        karagoz.setNobjectdestination(getWidth() - karagoz.getNobjectdstw()-400,karagoz.getNobjectdsty(),150,330);
        canvas.drawBitmap(karagoz.getNobject(), karagoz.getNobjectsource(), karagoz.getNobjectdestination(), null);

        hacivat.setNobjectsource(0,0,1957,5110);
        hacivat.setNobjectdestination(300,hacivat.getNobjectdsty(),150,330);
        canvas.drawBitmap(hacivat.getNobject(), hacivat.getNobjectsource(), hacivat.getNobjectdestination(), null);

        obje1.setNobjectsource(0,0,757,720);
        obje1.setNobjectdestination(obje1.getNobjectdstx() ,obje1.getNobjectdsty(),25,30);
        canvas.drawBitmap(obje1.getNobject(),obje1.getNobjectsource(),obje1.getNobjectdestination(),null);

        obje2.setNobjectsource(0,0,757,720);
        obje2.setNobjectdestination(obje2.getNobjectdstx() ,obje2.getNobjectdsty(),25,30);
        canvas.drawBitmap(obje2.getNobject(),obje2.getNobjectsource(),obje2.getNobjectdestination(),null);

        backbutton.setNobjectsource(0,0,256,256);
        backbutton.setNobjectdestination(getWidth()-backbutton.getNobjectdstw(),0,128,128);
        canvas.drawBitmap(backbutton.getNobject(), backbutton.getNobjectsource(), backbutton.getNobjectdestination(), null);

        restart.setNobjectsource(0,0,706,720);
        restart.setNobjectdestination(getWidth()-backbutton.getNobjectdstw()*2,0,128,128);
        canvas.drawBitmap( restart.getNobject(),  restart.getNobjectsource(), restart.getNobjectdestination(), null);

        fire.setNobjectsource(0,0,256,256);
        fire.setNobjectdestination(getWidth()-fire.getNobjectdstw(),getHeight()-fire.getNobjectdsth(),256,256);
        canvas.drawBitmap(fire.getNobject(),  fire.getNobjectsource(), fire.getNobjectdestination(), null);

        jump.setNobjectsource(0,0,256,256);
        jump.setNobjectdestination(0,getHeight()-jump.getNobjectdsth(),256,256);
        canvas.drawBitmap( jump.getNobject(),  jump.getNobjectsource(), jump.getNobjectdestination(), null);
    }
//HACİVAT ZIPLAMA KONTROLU
    public void HacivatJump(){
        if(!hacivat.isJumpcontrol()){
            hacivat.setJumpcontrol(true);
        }
        if(hacivat.jump()){
            hacivat.setJumpcontrol(false);
            hacivat.setNobjectdsty(getHeight() - hacivat.getNobjectdsth());
        }
    }
//KARAGÖZ ZIPLAMA KONTROLU
    public void KaragozJump(){
        if(!karagoz.isJumpcontrol()){
            karagoz.setJumpcontrol(true);
        }
        if(karagoz.jump()){
            karagoz.setJumpcontrol(false);
            karagoz.setNobjectdsty(getHeight() - karagoz.getNobjectdsth());
        }
    }

 //AI DEFANS
    public void AiPlayerModeDefence(Character character){
            if(animHacivat.AIAttackCollision()){
                animHacivat.ShoutAnımationHacivat();
               if(!hacivat.isJumpcontrol()){
                   hacivat.setJumpcontrol(true);
               }
               if(hacivat.jump()){
                   hacivat.setJumpcontrol(false);
                   hacivat.setNobjectdsty(getHeight() - 300);
               }
            }else{
                hacivat.setJumpcontrol(false);

            }

    }
    public void AiPlayer(Character character,Animations animCharacter) {
        //if(karakterin canı 80 den büyük ise)
        //if()
        if(hacivat.getHealth() == 100){
            AiPlayerModeDefence(character);
        }
    }
    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
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
    return true;
    }







    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    public void touchDown(int x, int y, int id) {
        touchdownx = x;
        touchdowny = y;
        if (x >= backbutton.getNobjectdstx() && x <= backbutton.getNobjectdstx() + backbutton.getNobjectdstw() && y >= backbutton.getNobjectdsty() && y <= backbutton.getNobjectdsty() + backbutton.getNobjectdsth()) {

            MenuCanvas mc1= new MenuCanvas(root);
            root.canvasManager.setCurrentCanvas(mc1);
        }
        if (x >= restart.getNobjectdstx() && x <= restart.getNobjectdstx() + restart.getNobjectdstw() && y >= restart.getNobjectdsty() && y <= restart.getNobjectdsty() + restart.getNobjectdsth()) {

            GameCanvas mc2= new GameCanvas(root);
            root.canvasManager.setCurrentCanvas(mc2);
        }
        if (x >= jump.getNobjectdstx() && x <= jump.getNobjectdstx() + jump.getNobjectdstw() && y >= jump.getNobjectdsty() && y <= jump.getNobjectdsty() + jump.getNobjectdsth()) {
            karagoz.setJumpcontrol(true);

        }

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
