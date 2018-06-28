package com.ngdroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.nfc.Tag;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;





public class GameCanvas extends BaseCanvas {
    //Değişkenler
    private boolean ShoutControl=false;
    Character karagoz, hacivat;
    Animations animKaragoz, animHacivat;
    Nobject arkaplan, backbutton, restart, fire, jump;
    FruitObject obje1,obje2;
    private int touchdownx, touchdowny;
    private final String Hacivat = "Hacivat";
    private final String Karagoz = "Karagoz";

    public void setup() {
        karagoz = new Character();
        arkaplan = new Nobject();
        obje1 = new FruitObject(10,10);
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
    public void setupObjectHacivat() {
        obje1 = new FruitObject(10,10);
        obje1.setNobjectdstw(50);
        obje1.setNobjectdsth(60);
        setObje1SetBase();
    }

    public void setupObjectKaragoz() {
        obje2 = new FruitObject(10,10);
        obje2.setNobjectdstw(50);
        obje2.setNobjectdsth(60);
        obje2.setLivecontrol(true);
        setObje2SetBase();
    }
    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void update(){
        if(karagoz.isJumpcontrol()) karagozJump();

        if(hacivat.isJumpcontrol()) hacivatJump();

        if(hacivat.isShoutControl()) hacivatShot();
        else setObje1SetBase();

        if(karagoz.isShoutControl()) karagozShot();
        else setObje2SetBase();
        aiPlayer(hacivat,animHacivat);

     

    }
    public void draw(Canvas canvas) {
        arkaplan.setNobjectsource(0,0,3840,2160);
        arkaplan.setNobjectdestination(0,0,getWidth(),getHeight());
        canvas.drawBitmap(arkaplan.getNobject(), arkaplan.getNobjectsource(), arkaplan.getNobjectdestination(), null);

        if(obje2.isLivecontrol()) {
            obje2.setNobjectsource(0, 0, 757, 720);
            obje2.setNobjectdestination(obje2.getNobjectdstx(), obje2.getNobjectdsty(), obje2.getNobjectdstw(), obje2.getNobjectdsth());
            canvas.drawBitmap(obje2.getNobject(), obje2.getNobjectsource(), obje2.getNobjectdestination(), null);
        }
        karagoz.setNobjectsource(0,0,2215,4892);
        karagoz.setNobjectdestination(karagoz.getNobjectdstx(),karagoz.getNobjectdsty(),150,330);
        canvas.drawBitmap(karagoz.getNobject(), karagoz.getNobjectsource(), karagoz.getNobjectdestination(), null);

        hacivat.setNobjectsource(0,0,1957,5110);
        hacivat.setNobjectdestination(hacivat.getNobjectdstx(),hacivat.getNobjectdsty(),hacivat.getNobjectdstw(),hacivat.getNobjectdsth());
        canvas.drawBitmap(hacivat.getNobject(), hacivat.getNobjectsource(), hacivat.getNobjectdestination(), null);

        obje1.setNobjectsource(0,0,757,720);
        obje1.setNobjectdestination(obje1.getNobjectdstx() ,obje1.getNobjectdsty(),obje1.getNobjectdstw(),obje1.getNobjectdsth());
        canvas.drawBitmap(obje1.getNobject(),obje1.getNobjectsource(),obje1.getNobjectdestination(),null);


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
    public void hacivatShot(){
        if(hacivat.isShoutControl()){
            animHacivat.ShoutAnımationHacivat();
        }
        if(hacivat.getBulletcount()==0){
            hacivat.setShoutcountrol(false);
            Log.i(Hacivat,"Mermi Bitti");
        }
        if(!animHacivat.ShoutAnımationHacivat()){
            hacivat.setShoutcountrol(false);
            setObje1SetBase();
            hacivat.decBulletCount();
        }
        else if(obje1.getNobjectdstx()+obje1.getNobjectdstw() > getWidth()){
            hacivat.setShoutcountrol(false);
            setObje1SetBase();
            hacivat.decBulletCount();
        }
    }
    public void karagozShot(){
        if(karagoz.isShoutControl()) {
            animKaragoz.ShoutAnımationKaragoz();
        }
        if(karagoz.getBulletcount() == 0){
            karagoz.setShoutcountrol(false);
            Log.i(Karagoz,"Mermi Bitti");
            obje2.setLivecontrol(false);
        }else obje2.setLivecontrol(true);
        if(!animKaragoz.ShoutAnımationKaragoz()){
            karagoz.setShoutcountrol(false);
            animKaragoz.getTargetCharacter().decHealth(animKaragoz.getObject());
            Log.i("Ai Player",""+animKaragoz.getTargetCharacter().getHealth());
            setObje2SetBase();
            karagoz.decBulletCount();
        }
        else if (obje2.getNobjectdstx() < 0){
            karagoz.setShoutcountrol(false);
            setObje2SetBase();
            karagoz.decBulletCount();
        }
    }
        //Obje1 i sahibi olan karakterin eline konumlandırır
        public void setObje1SetBase(){
            obje1.setNobjectdsty(hacivat.getNobjectdsty() + hacivat.getNobjectdsth() / 4);
            obje1.setNobjectdstx(hacivat.getNobjectdstx() + hacivat.getNobjectdstw() - 20);
        }
        //Obje2 i sahibi olan karakterin eline konumlandırır
        public void setObje2SetBase(){
            obje2.setNobjectdsty(karagoz.getNobjectdsty() + karagoz.getNobjectdsth() / 4);
            obje2.setNobjectdstx(karagoz.getNobjectdstx() - obje2.getNobjectdstw() + 20);
        }
//HACİVAT ZIPLAMA KONTROLU
    public void hacivatJump(){
        if(!hacivat.isJumpcontrol()){
            hacivat.setJumpcontrol(true);
        }
        if(hacivat.jump()){
            hacivat.setJumpcontrol(false);
            hacivat.setNobjectdsty(getHeight() - hacivat.getNobjectdsth());
        }
    }
//KARAGÖZ ZIPLAMA KONTROLU
    public void karagozJump(){
        if(!karagoz.isJumpcontrol()){
            karagoz.setJumpcontrol(true);
        }
        if(karagoz.jump()){
            karagoz.setJumpcontrol(false);
            karagoz.setNobjectdsty(getHeight() - karagoz.getNobjectdsth());
        }
    }

 //AI DEFANS
    public void aiPlayerModeAttack(){
            if(animHacivat.AIAttackCollision(obje2)){
                if(animHacivat.AIDefenceCollision(obje2)){
                    hacivat.setShoutcountrol(true);
                    hacivat.setJumpcontrol(true);
                }
            }else if (karagoz.getBulletcount() == 0) hacivat.setShoutcountrol(true);

    }
    public void aiPlayerModeTrack(){
        if(animHacivat.AIAttackCollision(obje2)){
            if(animHacivat.AIDefenceCollision(obje2)){
                hacivat.setShoutcountrol(true);
                hacivat.setJumpcontrol(true);
            }
        }else if (karagoz.getBulletcount() == 0) hacivat.setShoutcountrol(true);

    }
    public void aiPlayerModeSafeAttack(){
        if(animHacivat.AIAttackCollision(obje2)){
            hacivat.setShoutcountrol(true);
            if(animHacivat.AIDefenceCollision(obje2)){
                hacivat.setJumpcontrol(true);
            }
        }else if (karagoz.getBulletcount() == 0) hacivat.setShoutcountrol(true);

    }
    public void aiPlayer(Character character,Animations animCharacter) {
        if(hacivat.getHealth() > 70 ){
            aiPlayerModeTrack();
        }else if(hacivat.getHealth() > 50){
            aiPlayerModeSafeAttack();
        }else if(hacivat.getHealth() > 30){
            aiPlayerModeAttack();
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

    }

    public void touchMove(int x, int y, int id) {
    }

    public void touchUp(int x, int y, int id) {
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
        if (x >= fire.getNobjectdstx() && x <= fire.getNobjectdstx() + fire.getNobjectdstw() && y >= fire.getNobjectdsty() && y <= fire.getNobjectdsty() + fire  .getNobjectdsth()) {
            karagoz.setShoutcountrol(true);
            Log.i("Ateş Butonu","Ateş edildi");
            Log.i("Ateş Butonu",""+ hacivat.isShoutControl());

        }
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
