package com.ngdroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.mycompany.myngdroidapp.R;

import istanbul.gamelab.ngdroid.base.BaseActivity;
import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;



public class MenuCanvas extends BaseCanvas {
    Nobject arkaplan,playbutton,backbutton;
    Character karagoz,hacivat;
    private MediaPlayer mediatitle = MediaPlayer.create(root.activity, R.raw.title);

    private int touchdownx, touchdowny;





    public MenuCanvas(NgApp ngApp) {
        super(ngApp);
    }


    public void setup() {
        arkaplan = new Nobject();
        karagoz = new Character();
        hacivat = new Character();
        backbutton = new Nobject();

        setupKaragoz();
        setupHacivat();


        setupPlayButton();
        arkaplan.setNobject(Utils.loadImage(root, "arkaplan.png"));
        karagoz.setNobject(Utils.loadImage(root, "karagoz.png"));
        hacivat.setNobject(Utils.loadImage(root, "hacivat.png"));
        playbutton.setNobject(Utils.loadImage(root, "playbutton.png"));
        backbutton.setNobject(Utils.loadImage(root, "backbutton.png"));
    }



    public void setupKaragoz(){
        karagoz = new Character();
        karagoz.setNobjectsrcx(20);
        karagoz.setNobjectsrcy(0);
        karagoz.setNobjectsrcw(480);
        karagoz.setNobjectsrch(718);
        karagoz.setNobjectdstw(423);
        karagoz.setNobjectdsth(660);
        karagoz.setNobjectdsty(getWidth() / 8);
        karagoz.setNobjectdstx(getWidth() * 4 / 6);
        karagoz.setJumpcontrol(false);
        hacivat.setShoutcountrol(false);

    }
    public void setupHacivat(){
        hacivat = new Character();
        hacivat.setNobjectsrcx(0);
        hacivat.setNobjectsrcy(0);
        hacivat.setNobjectsrcw(390);
        hacivat.setNobjectsrch(718);
        hacivat.setNobjectdstw(358);
        hacivat.setNobjectdsth(660);
        hacivat.setNobjectdsty(getWidth() / 8);
        hacivat.setNobjectdstx(getHeight() / 6 + hacivat.getNobjectdstw() / 3);
        hacivat.setJumpcontrol(false);
        hacivat.setShoutcountrol(false);
    }

    public void update() {titlemusic();

    }
    public void setupPlayButton(){
        playbutton = new Nobject();
        playbutton.setNobjectsrcx(0);
        playbutton.setNobjectsrcy(0);
        playbutton.setNobjectsrcw(300);
        playbutton.setNobjectsrch(122);
        playbutton.setNobjectdstw(600);
        playbutton.setNobjectdsth(244);
        playbutton.setNobjectdstx(getWidth()/2-playbutton.getNobjectdstw()/2);
        playbutton.setNobjectdsty(getHeight()/2-playbutton.getNobjectdsth()/2);

    }
    public void draw(Canvas canvas) {
        arkaplan.setNobjectsource(0,0,3840,2160);
        arkaplan.setNobjectdestination(0,0,getWidth(),getHeight());
        canvas.drawBitmap(arkaplan.getNobject(),  arkaplan.getNobjectsource(), arkaplan.getNobjectdestination(), null);

        karagoz.setNobjectsource(karagoz.getNobjectsrcx(),karagoz.getNobjectsrcy(),karagoz.getNobjectsrcw(),karagoz.getNobjectsrch());
        karagoz.setNobjectdestination(karagoz.getNobjectdstx(),karagoz.getNobjectdsty(),karagoz.getNobjectdstw(),karagoz.getNobjectdsth());
        canvas.drawBitmap(karagoz.getNobject(), karagoz.getNobjectsource(), karagoz.getNobjectdestination(), null);


        hacivat.setNobjectsource(hacivat.getNobjectsrcx(),hacivat.getNobjectsrcy(),hacivat.getNobjectsrcw(),hacivat.getNobjectsrch());
        hacivat.setNobjectdestination(hacivat.getNobjectdstx(),hacivat.getNobjectdsty(),hacivat.getNobjectdstw(),hacivat.getNobjectdsth());
        canvas.drawBitmap(hacivat.getNobject(), hacivat.getNobjectsource(), hacivat.getNobjectdestination(), null);

        playbutton.setNobjectsource(playbutton.getNobjectsrcx(),playbutton.getNobjectsrcy(),playbutton.getNobjectsrcw(),playbutton.getNobjectsrch());
        playbutton.setNobjectdestination(playbutton.getNobjectdstx(),playbutton.getNobjectdsty(),playbutton.getNobjectdstw(),playbutton.getNobjectdsth());
        canvas.drawBitmap(playbutton.getNobject(), playbutton.getNobjectsource(), playbutton.getNobjectdestination(), null);

        backbutton.setNobjectsource(0,0,256,256);
        backbutton.setNobjectdestination(getWidth()-backbutton.getNobjectdstw(),0,256,256);
        canvas.drawBitmap(backbutton.getNobject(), backbutton.getNobjectsource(), backbutton.getNobjectdestination(), null);
    }


    //GİRİŞ MUZİĞİ
    public void titlemusic () {

        if(mediatitle.isPlaying()){}
        else{mediatitle.start();}
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
           playbutton.setNobjectsrcx(300);
        }
        if (x >= backbutton.getNobjectdstx() && x <= backbutton.getNobjectdstx() + backbutton.getNobjectdstw() && y >= backbutton.getNobjectdsty() && y <= backbutton.getNobjectdsty() + backbutton.getNobjectdsth()) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(root.activity);
        builder1.setTitle("Oyundan çıkılsın mı?").setCancelable(false).setPositiveButton("Evet", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) { //Eğer evet butonuna basılırsa
                root.activity.finish();
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
        if (x >= playbutton.getNobjectdstx() && x <= playbutton.getNobjectdstx() + playbutton.getNobjectdstw() && y >= playbutton.getNobjectdsty() && y <= playbutton.getNobjectdsty() + playbutton.getNobjectdsth()) {
            playbutton.setNobjectsrcx(300);
        }else playbutton.setNobjectsrcx(0);

    }public void touchUp(int x, int y, int id) {

        touchdownx = x;
        touchdowny = y;
        if (x >= playbutton.getNobjectdstx() && x <= playbutton.getNobjectdstx() + playbutton.getNobjectdstw() && y >= playbutton.getNobjectdsty() && y <= playbutton.getNobjectdsty() + playbutton.getNobjectdsth()) {
                playbutton.setNobjectsrcx(0);
                GameCanvas mc = new GameCanvas(root);
                root.canvasManager.setCurrentCanvas(mc);
        }
        if (x >= backbutton.getNobjectdstx() && x <= backbutton.getNobjectdstx() + backbutton.getNobjectdstw() && y >= backbutton.getNobjectdsty() && y <= backbutton.getNobjectdsty() + backbutton.getNobjectdsth()) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(root.activity);

            builder1.setTitle("Oyundan çıkılsın mı?").setCancelable(false).setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int id) { //Eğer evet butonuna basılırsa
                                root.activity.finish();
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