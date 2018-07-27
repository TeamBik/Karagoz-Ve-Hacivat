package com.ngdroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.gamelab.karagozhacivat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import istanbul.gamelab.ngdroid.base.BaseActivity;
import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;



public class MenuCanvas extends BaseCanvas {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    Nobject arkaplan,playbutton,backbutton, menu;
    Character karagoz,hacivat;
    private MediaPlayer mediatitle = MediaPlayer.create(root.activity, R.raw.title);
    private int gameCoin, gameCount, winCount, loseCount;
    private int touchdownx, touchdowny;


    public MenuCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {
        resetRoom();
        mAuth = FirebaseAuth.getInstance();
        arkaplan = new Nobject();
        karagoz = new Character();
        hacivat = new Character();
        backbutton = new Nobject();
        menu=new Nobject();
        setupFirebase();
        setupKaragoz();
        setupHacivat();
        sharedPreference();
        setupPlayButton();
        setupMenu();
        arkaplan.setNobject(Utils.loadImage(root, "arkaplan.png"));
        karagoz.setNobject(Utils.loadImage(root, "karagoz.png"));
        hacivat.setNobject(Utils.loadImage(root, "hacivat.png"));
        playbutton.setNobject(Utils.loadImage(root, "playbutton.png"));
        backbutton.setNobject(Utils.loadImage(root, "backbutton.png"));
        menu.setNobject(Utils.loadImage(root,"menu.png"));
    }

    public void setupFirebase(){

    }
    public void sharedPreference(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(root.activity);
        if(root.activity.getFirebaseUserStatistic() != null){
            gameCoin = root.activity.getFirebaseUserStatistic().getMyCoin();
            gameCount =root.activity.getFirebaseUserStatistic().getMyGameCount();
            winCount = root.activity.getFirebaseUserStatistic().getMyWinCount();
            loseCount = root.activity.getFirebaseUserStatistic().getMyLoseCount();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("myCoin", root.activity.getFirebaseUserStatistic().getMyCoin());
            editor.putInt("myGameCount", root.activity.getFirebaseUserStatistic().getMyGameCount());
            editor.putInt("myWinCount", root.activity.getFirebaseUserStatistic().getMyWinCount());
            editor.putInt("myLoseCount", root.activity.getFirebaseUserStatistic().getMyLoseCount());
            editor.commit();
        }else if(preferences.contains("myCoin")){
            gameCoin = preferences.getInt("myCoin",0);
            gameCount = preferences.getInt("myGameCount",0);
            winCount = preferences.getInt("myWinCount",0);
            loseCount = preferences.getInt("myLoseCount",0);
        }else {
            gameCoin = 0;
            gameCount = 0;
            winCount = 0;
            loseCount = 0;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("myCoin", gameCoin);
            editor.putInt("myGameCount", gameCount);
            editor.putInt("myWinCount", winCount);
            editor.putInt("myLoseCount", loseCount);
            editor.commit();
        }
        GameStatistic gameStatistic = new GameStatistic();
        gameStatistic.setMyCoin(gameCoin);
        gameStatistic.setMyGameCount(gameCount);
        gameStatistic.setMyWinCount(winCount);
        gameStatistic.setMyLoseCount(loseCount);
        root.activity.setFirebaseUserStatistic(gameStatistic);
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
    public void setupMenu(){
        menu.setNobjectsrcw(353);
        menu.setNobjectsrch(437);
        menu.setNobjectsrcx(0);
        menu.setNobjectsrcy(0);
        menu.setNobjectdstw(353 * 2);
        menu.setNobjectdsth(437 * 2);
        menu.setNobjectdstx(getWidth() / 2 - menu.getNobjectdstw() / 2);
        menu.setNobjectdsty(getHeight() / 2 - menu.getNobjectdsth() / 2);
    }
    public void resetRoom(){
        root.activity.setIsRoomId(null);
        root.activity.setIsRoom(false);
        root.activity.setOtherPlayerData(null);
    }

    public void update() {
        Log.i("Coin:",""+gameCoin);

        titlemusic();
    }
    public void setupPlayButton(){
        playbutton = new Nobject();
        playbutton.setNobjectsrcx(0);
        playbutton.setNobjectsrcy(0);
        playbutton.setNobjectsrcw(300);
        playbutton.setNobjectsrch(122);
        playbutton.setNobjectdstw(600);
        playbutton.setNobjectdsth(244);
        playbutton.setNobjectdstx(getWidth() / 2 - playbutton.getNobjectdstw() / 2);
        playbutton.setNobjectdsty(getHeight() / 2 - playbutton.getNobjectdsth() / 2);

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

       /* playbutton.setNobjectsource(playbutton.getNobjectsrcx(),playbutton.getNobjectsrcy(),playbutton.getNobjectsrcw(),playbutton.getNobjectsrch());
        playbutton.setNobjectdestination(playbutton.getNobjectdstx(),playbutton.getNobjectdsty(),playbutton.getNobjectdstw(),playbutton.getNobjectdsth());
        canvas.drawBitmap(playbutton.getNobject(), playbutton.getNobjectsource(), playbutton.getNobjectdestination(), null);
        */
        menu.setNobjectsource(menu.getNobjectsrcx(), menu.getNobjectsrcy(), menu.getNobjectsrcw(), menu.getNobjectsrch());
        menu.setNobjectdestination(menu.getNobjectdstx(), menu.getNobjectdsty(), menu.getNobjectdstw(), menu.getNobjectdsth());
        canvas.drawBitmap(menu.getNobject(), menu.getNobjectsource(), menu.getNobjectdestination(),null);

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
        /*if (x >= playbutton.getNobjectdstx() && x <= playbutton.getNobjectdstx() + playbutton.getNobjectdstw() && y >= playbutton.getNobjectdsty() && y <= playbutton.getNobjectdsty() + playbutton.getNobjectdsth()) {
           playbutton.setNobjectsrcx(300);
        }*/


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

    }
    public void touchUp(int x, int y, int id) {

        touchdownx = x;
        touchdowny = y;
       /* if (x >= playbutton.getNobjectdstx() && x <= playbutton.getNobjectdstx() + playbutton.getNobjectdstw() && y >= playbutton.getNobjectdsty() && y <= playbutton.getNobjectdsty() + playbutton.getNobjectdsth()) {
                playbutton.setNobjectsrcx(0);
                GameCanvasMultiPlayer mc = new GameCanvasMultiPlayer(root);
                root.canvasManager.setCurrentCanvas(mc);
        }*/

        if(x >= menu.getNobjectdstx() + menu.getNobjectdstw() / 7.06 && x <=  menu.getNobjectdstx() + menu.getNobjectdstw() / 2 && y >= menu.getNobjectdsty() + menu.getNobjectdsth() / 9.71 && y <= menu.getNobjectdsty() + menu.getNobjectdsth() / 4.6 ){
            GameCanvas mc = new GameCanvas(root);
            root.canvasManager.setCurrentCanvas(mc);
        }
        if(x >= menu.getNobjectdstx() + menu.getNobjectdstw() / 2 && x <=  menu.getNobjectdstx() + menu.getNobjectdstw() / 1.11 && y >= menu.getNobjectdsty() + menu.getNobjectdsth() / 9.71 && y <= menu.getNobjectdsty() + menu.getNobjectdsth() / 4.6 ){
            GameCanvasMultiPlayer mc = new GameCanvasMultiPlayer(root);
            root.canvasManager.setCurrentCanvas(mc);
        }
        if(x >= menu.getNobjectdstx() + menu.getNobjectdstw() / 7.06 && x <=  menu.getNobjectdstx() + menu.getNobjectdstw() / 1.11 && y >= menu.getNobjectdsty() + menu.getNobjectdsth() / 1.27 && y <= menu.getNobjectdsty() + menu.getNobjectdsth() / 1.10 ){
            root.activity.finish();
            System.exit(0);
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