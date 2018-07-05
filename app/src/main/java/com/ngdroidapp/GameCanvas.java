package com.ngdroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.Random;
import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import com.mycompany.myngdroidapp.R;


public class GameCanvas extends BaseCanvas {
    //Değişkenler
    public MediaPlayer mediaback,mediadefeat,mediafruitbam,mediafruitcol,mediajump,mediatitle;

    private boolean ShoutControl=false ,gameControl = true, splashEffectControl = false;
    private Character karagoz, hacivat;
    private Animations animKaragoz, animHacivat,animations;
    private Nobject arkaplan, backbutton, restart, fire, jump,bomb, win, lose;
    private Nobject blackbarHacivat, blackbarKaragoz, greenbarKaragoz, greenbarHacivat, startintimeImage;
    private FruitObject obje1,obje2;
    private int splashrow=0,splashline=0;
    private double velocityend;
    private Paint paintTime, paintStartingTime,paintScoreCount, paintCoinCount;
    private Random randFruitHacivat, randFruitKaragoz;
    private int touchdownx, touchdowny, time, startingtime, sure;
    private final String Hacivat = "Hacivat";
    private final String Karagoz = "Karagoz";
    private final int peachsrcx = 0, peachsrcy = 0, watermelonsrcx = 90, watermelonsrcy = 0, pearsrcx = 182, pearsrcy = 0, plumsrcx = 255, plumsrcy = 0, strawberrysrcx = 340, strawberrysrcy = 0, orangesrcx = 422, orangesrcy = 0, tomatosrcx = 0, tomatosrcy = 107 ;
    private final int peachsrcw = 89, peachsrch = 107, watermelonsrcw = 90, watermelonsrch = 94, pearsrcw = 73, pearsrch = 107, plumsrcw = 84, plumsrch = 107, strawberrysrcw = 82 , strawberrysrch = 107, orangesrcw = 104, orangesrch = 107, tomatosrcw = 110, tomatosrch = 118 ;
    private final int peachv = 17, peachw = 10, watermelonv = 15, watermelonw = 10, pearv = 20, pearw = 10, plumv = 25, plumw = 10, strawberryv = 27 , strawberryw = 10, orangev = 25, orangew = 10, tomatov = 27, tomatow = 10 ;
    private int scorehit, scoredamage, scoremiss,scorecoin;
    public void setup() {

        karagoz = new Character();
        arkaplan = new Nobject();
        backbutton = new Nobject();
        restart = new Nobject();
        fire = new Nobject();
        jump = new Nobject();
        greenbarKaragoz = new Nobject();
        greenbarHacivat = new Nobject();
        gameControl = false;
        velocityend = 0.1;
        randFruitHacivat = new Random();
        randFruitKaragoz = new Random();
        win = new Nobject();
        lose = new Nobject();
        gameControl = false;
        setupHacivat();
        setupKaragoz();
        setupObjectHacivat();
        setupObjectKaragoz();
        setupHealthBarHacivat();
        setupHealthBarKaragoz();
        setupText();
        setupwin();
        setuplose();
        setupBomb();
        setupstartingImage();
        time = 1800;
        startingtime = 120;
        scorehit = 0;
        scoredamage = 0;
        scoremiss = 0;
        scorecoin = 0;
        animKaragoz = new Animations(karagoz, hacivat, obje2);
        animHacivat = new Animations(hacivat, karagoz, obje1);
        pictures();
        sounds();
    }


    //RESİMLERİN TANINMASI
    public void pictures()
    {   startintimeImage.setNobject(Utils.loadImage(root, "alphabetnumb.png"));
        obje1.setNobject(Utils.loadImage(root,"fruits.png"));
        obje2.setNobject(Utils.loadImage(root,"fruits.png"));
        arkaplan.setNobject(Utils.loadImage(root, "arkaplan.png"));
        karagoz.setNobject(Utils.loadImage(root,"karagoz.png"));
        hacivat.setNobject(Utils.loadImage(root,"hacivat.png"));
        backbutton.setNobject(Utils.loadImage(root,"backbutton.png"));
        restart.setNobject(Utils.loadImage(root,"restart.png"));
        jump.setNobject(Utils.loadImage(root,"jump.png"));
        fire.setNobject(Utils.loadImage(root,"fire.png"));
        blackbarHacivat.setNobject(Utils.loadImage(root,"blackbar2.png"));
        blackbarKaragoz.setNobject(Utils.loadImage(root, "blackbar.png"));
        greenbarKaragoz.setNobject(Utils.loadImage(root, "greenbar.png"));
        greenbarHacivat.setNobject(Utils.loadImage(root, "greenbar2.png"));
        win.setNobject(Utils.loadImage(root, "win.png"));
        lose.setNobject(Utils.loadImage(root,"lose.png"));
        bomb.setNobject(Utils.loadImage(root,"bom.png"));
    }
    //SESLERİN TANINMASI
        public void sounds(){
        mediafruitcol = MediaPlayer.create(root.activity, R.raw.fruitcollesion);
        mediaback = MediaPlayer.create(root.activity,R.raw.background);
        mediatitle = MediaPlayer.create(root.activity,R.raw.tittle);
        mediajump = MediaPlayer.create(root.activity,R.raw.jump);
        mediadefeat = MediaPlayer.create(root.activity,R.raw.defeat);
    }



    //YAZILAR
    public void setupText(){
            paintTime = new Paint();
            paintTime.setColor(Color.WHITE);
            paintTime.setStyle(Paint.Style.FILL);
            paintTime.setTextSize(64);

            paintStartingTime = new Paint();
            paintStartingTime.setColor(Color.WHITE);
            paintStartingTime.setStyle(Paint.Style.FILL);
            paintStartingTime.setTextSize(256);

            paintScoreCount = new Paint();
            paintScoreCount.setColor(Color.WHITE);
            paintScoreCount.setTypeface(Typeface.create("ObelixPro",Typeface.BOLD));
            paintScoreCount.setStyle(Paint.Style.FILL);
            paintScoreCount.setTextSize(128);

            paintCoinCount = new Paint();
            paintCoinCount.setColor(Color.WHITE);
            paintCoinCount.setStyle(Paint.Style.FILL);
            paintCoinCount.setTextSize(64);
    }
    //OBJE,KARAKTER SETUPLARI

    public void setupKaragoz() {
        karagoz = new Character();
        karagoz.setNobjectdstw(150);
        karagoz.setNobjectdsth(330);
        karagoz.setNobjectdsty(getHeight() - karagoz.getNobjectdsth());
        karagoz.setNobjectdstx(getWidth() - karagoz.getNobjectdstw() - 400);
        karagoz.setJumpcontrol(false);
        hacivat.setShoutcountrol(false);

    }

    public void setupHacivat() {
        hacivat = new Character();
        hacivat.setNobjectdstw(150);
        hacivat.setNobjectdsth(330);
        hacivat.setNobjectdsty(getHeight() - hacivat.getNobjectdsth());
        hacivat.setNobjectdstx(300);
        hacivat.setJumpcontrol(false);
        hacivat.setShoutcountrol(false);
    }

    public void setupObjectHacivat() {
        obje1 = new FruitObject(10, 10);
        chooseFruitHacivat();
        obje1.setNobjectdstw(50);
        obje1.setNobjectdsth(60);
        obje1.setLivecontrol(true);
        setObje1SetBase();
    }

    public void setupObjectKaragoz() {
        obje2 = new FruitObject(10, 10);
        chooseFruitKaragoz();
        obje2.setNobjectdstw(50);
        obje2.setNobjectdsth(60);
        obje2.setLivecontrol(true);
        setObje2SetBase();
    }

    // Karagöz ve Hacivat sağlık barı Setupı
    public void setupHealthBarHacivat() {
        setupBlackBarHacivat();
        setupGreenBarHacivat();
    }

    public void setupHealthBarKaragoz() {
        setupBlackBarKaragoz();
        setupGreenBarKaragoz();
    }

    //Karagöz ve Hacivat Sağlık Barı Çercevesi
    public void setupBlackBarKaragoz() {
        blackbarKaragoz = new Nobject();
        blackbarKaragoz.setNobjectsrcx(0);
        blackbarKaragoz.setNobjectsrcy(0);
        blackbarKaragoz.setNobjectsrcw(811);
        blackbarKaragoz.setNobjectsrch(266);
        blackbarKaragoz.setNobjectdstw(blackbarKaragoz.getNobjectsrcw() / 4 * 3);
        blackbarKaragoz.setNobjectdsth(blackbarKaragoz.getNobjectsrch() / 3 * 2);
        blackbarKaragoz.setNobjectdstx(getWidth() / 2 + 50);
        blackbarKaragoz.setNobjectdsty(5);
    }

    public void setupBlackBarHacivat() {
        blackbarHacivat = new Nobject();
        blackbarHacivat.setNobjectsrcx(0);
        blackbarHacivat.setNobjectsrcy(0);
        blackbarHacivat.setNobjectsrcw(826);
        blackbarHacivat.setNobjectsrch(266);
        blackbarHacivat.setNobjectdstw(blackbarHacivat.getNobjectsrcw() / 4 * 3);
        blackbarHacivat.setNobjectdsth(blackbarHacivat.getNobjectsrch() / 3 * 2);
        blackbarHacivat.setNobjectdstx(getWidth() / 2 - blackbarHacivat.getNobjectdstw() - 50);
        blackbarHacivat.setNobjectdsty(5);
    }

    //Karagöz ve Hacivat Sağlık Barı Can Göstergesi
    public void setupGreenBarHacivat() {
        greenbarHacivat = new Nobject();
        greenbarHacivat.setNobjectsrcx(0);
        greenbarHacivat.setNobjectsrcy(0);
        greenbarHacivat.setNobjectsrcw(759);
        greenbarHacivat.setNobjectsrch(143);
        greenbarHacivat.setNobjectdstx(getWidth() / 2 - 220 - hacivat.getHealth() * 4);
        greenbarHacivat.setNobjectdstw(getWidth() / 2 - 220 - greenbarHacivat.getNobjectdstx());
        greenbarHacivat.setNobjectdsth(blackbarHacivat.getNobjectdsth() - 45);
        greenbarHacivat.setNobjectdsty(blackbarHacivat.getNobjectdsty() + 35);
    }

    public void setupGreenBarKaragoz() {
        greenbarKaragoz = new Nobject();
        greenbarKaragoz.setNobjectsrcx(0);
        greenbarKaragoz.setNobjectsrcy(0);
        greenbarKaragoz.setNobjectsrcw(759);
        greenbarKaragoz.setNobjectsrch(143);
        greenbarKaragoz.setNobjectdstw(karagoz.getHealth() * 4);
        greenbarKaragoz.setNobjectdsth(blackbarKaragoz.getNobjectdsth() - 38);
        greenbarKaragoz.setNobjectdstx(blackbarKaragoz.getNobjectdstx() + 150);
        greenbarKaragoz.setNobjectdsty(blackbarKaragoz.getNobjectdsty() + 39);
    }

    public void setupwin() {
        win = new Nobject();
        win.setNobjectsrcx(0);
        win.setNobjectsrcy(0);
        win.setNobjectsrcw(480);
        win.setNobjectsrch(480);
        win.setNobjectdstw(getHeight());
        win.setNobjectdsth(getHeight());
        win.setNobjectdstx(getWidth() / 2 - win.getNobjectdstw() / 2);
        win.setNobjectdsty(getHeight());

    }

    public void setuplose() {
        lose = new Nobject();
        lose.setNobjectsrcx(0);
        lose.setNobjectsrcy(0);
        lose.setNobjectsrcw(480);
        lose.setNobjectsrch(480);
        lose.setNobjectdstw(getHeight());
        lose.setNobjectdsth(getHeight());
        lose.setNobjectdstx(getWidth() / 2 - lose.getNobjectdstw() / 2);
        lose.setNobjectdsty(getHeight());
    }

    public void setupstartingImage() {
        startintimeImage = new Nobject();
        startintimeImage.setNobjectsrcw(250);
        startintimeImage.setNobjectsrch(350);
        startintimeImage.setNobjectsrcx(0);
        startintimeImage.setNobjectsrcy(0);
        startintimeImage.setNobjectdstw(250);
        startintimeImage.setNobjectdsth(350);
        startintimeImage.setNobjectdstx(getWidth() / 2 - startintimeImage.getNobjectdstw() / 2);
        startintimeImage.setNobjectdsty(getHeight() / 2 - startintimeImage.getNobjectdsth() / 2);
    }


    ////Karakter karakterin sağlık durumunu günceller
    public void setHacivatHealth(){
        greenbarHacivat.setNobjectdstx(getWidth() / 2 - 220 - hacivat.getHealth() * 4);
        greenbarHacivat.setNobjectdstw(getWidth() / 2 - 220 - greenbarHacivat.getNobjectdstx());

    }

    ////Karakter karakterin sağlık durumunu günceller
    public void setKaragozHealth(){
        greenbarKaragoz.setNobjectdstw(karagoz.getHealth() * 4);
    }

    public  void setupBomb()
    {
        animations=new Animations();
        bomb= new Nobject();
        bomb.setNobjectsrcx(0);
        bomb.setNobjectsrcy(0);
        bomb.setNobjectsrcw(100);
        bomb.setNobjectsrch(100);
        bomb.setNobjectdstw(128);
        bomb.setNobjectdsth(128);
        bomb.setNobjectdsty((obje1.getNobjectdsty() + obje2.getNobjectdsty()) / 2 - 32);
        bomb.setNobjectdstx((obje1.getNobjectdstx() + obje2.getNobjectdstx()) / 2 - 32);
    }

    public void timerControl() {
        if (time == 0) {
            gameControl = false;
        }
    }

    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void update(){

        timerControl();
        startingTimeCountDown();
        winkontrol();
        losekontrol();
        timeScroll();
        if (gameControl) {
            splashEffect();
            time--;
            if (karagoz.isJumpcontrol()) karagozJump();jumpmusic();
            if (karagoz.isShoutControl()) karagozShot();
            else if (!splashEffectControl) setObje2SetBase();
            if (hacivat.isJumpcontrol()) hacivatJump();
            if (hacivat.isShoutControl()) hacivatShot();
            else if (!splashEffectControl) setObje1SetBase();
            aiPlayer(hacivat, animHacivat);
            Log.i("GameCanvas", "Oyun Devam Ediyor");
        }//else if(time == 0)pause();

    }

    public void draw(Canvas canvas) {


        arkaplan.setNobjectsource(0, 0, 3840, 2160);
        arkaplan.setNobjectdestination(0, 0, getWidth(), getHeight());
        canvas.drawBitmap(arkaplan.getNobject(), arkaplan.getNobjectsource(), arkaplan.getNobjectdestination(), null);

        backbutton.setNobjectsource(0, 0, 256, 256);
        backbutton.setNobjectdestination(getWidth() - backbutton.getNobjectdstw(), 0, 128, 128);
        canvas.drawBitmap(backbutton.getNobject(), backbutton.getNobjectsource(), backbutton.getNobjectdestination(), null);

        restart.setNobjectsource(0, 0, 706, 720);
        restart.setNobjectdestination(getWidth() - backbutton.getNobjectdstw() * 2, 0, 128, 128);
        canvas.drawBitmap(restart.getNobject(), restart.getNobjectsource(), restart.getNobjectdestination(), null);

        fire.setNobjectsource(0, 0, 256, 256);
        fire.setNobjectdestination(getWidth() - fire.getNobjectdstw(), getHeight() - fire.getNobjectdsth(), 256, 256);
        canvas.drawBitmap(fire.getNobject(), fire.getNobjectsource(), fire.getNobjectdestination(), null);

        jump.setNobjectsource(0, 0, 256, 256);
        jump.setNobjectdestination(0, getHeight() - jump.getNobjectdsth(), 256, 256);
        canvas.drawBitmap(jump.getNobject(), jump.getNobjectsource(), jump.getNobjectdestination(), null);

        greenbarKaragoz.setNobjectsource(greenbarKaragoz.getNobjectsrcx(), greenbarKaragoz.getNobjectsrcy(), greenbarKaragoz.getNobjectsrcw(), greenbarKaragoz.getNobjectsrch());
        greenbarKaragoz.setNobjectdestination(greenbarKaragoz.getNobjectdstx(), greenbarKaragoz.getNobjectdsty(), greenbarKaragoz.getNobjectdstw(), greenbarKaragoz.getNobjectdsth());
        canvas.drawBitmap(greenbarKaragoz.getNobject(), greenbarKaragoz.getNobjectsource(), greenbarKaragoz.getNobjectdestination(), null);

        greenbarHacivat.setNobjectsource(greenbarHacivat.getNobjectsrcx(), greenbarHacivat.getNobjectsrcy(), greenbarHacivat.getNobjectsrcw(), greenbarHacivat.getNobjectsrch());
        greenbarHacivat.setNobjectdestination(greenbarHacivat.getNobjectdstx(), greenbarHacivat.getNobjectdsty(), greenbarHacivat.getNobjectdstw(), greenbarHacivat.getNobjectdsth());
        canvas.drawBitmap(greenbarHacivat.getNobject(), greenbarHacivat.getNobjectsource(), greenbarHacivat.getNobjectdestination(), null);

        blackbarKaragoz.setNobjectsource(blackbarKaragoz.getNobjectsrcx(), blackbarKaragoz.getNobjectsrcy(), blackbarKaragoz.getNobjectsrcw(), blackbarKaragoz.getNobjectsrch());
        blackbarKaragoz.setNobjectdestination(blackbarKaragoz.getNobjectdstx(), blackbarKaragoz.getNobjectdsty(), blackbarKaragoz.getNobjectdstw(), blackbarKaragoz.getNobjectdsth());
        canvas.drawBitmap(blackbarKaragoz.getNobject(), blackbarKaragoz.getNobjectsource(), blackbarKaragoz.getNobjectdestination(), null);

        blackbarHacivat.setNobjectsource(blackbarHacivat.getNobjectsrcx(), blackbarHacivat.getNobjectsrcy(), blackbarHacivat.getNobjectsrcw(), blackbarHacivat.getNobjectsrch());
        blackbarHacivat.setNobjectdestination(blackbarHacivat.getNobjectdstx(), blackbarHacivat.getNobjectdsty(), blackbarHacivat.getNobjectdstw(), blackbarHacivat.getNobjectdsth());
        canvas.drawBitmap(blackbarHacivat.getNobject(), blackbarHacivat.getNobjectsource(), blackbarHacivat.getNobjectdestination(), null);
        //Oyun başlamadıysa ve zaman 1800 ise başlangıc sğresi ekranda gözükecek
        if (!gameControl && time == 1800) {
            startintimeImage.setNobjectsource(startintimeImage.getNobjectsrcx(), startintimeImage.getNobjectsrcy(), startintimeImage.getNobjectsrcw(), startintimeImage.getNobjectsrch());
            startintimeImage.setNobjectdestination(startintimeImage.getNobjectdstx(), startintimeImage.getNobjectdsty(), startintimeImage.getNobjectdstw(), startintimeImage.getNobjectdsth());
            canvas.drawBitmap(startintimeImage.getNobject(), startintimeImage.getNobjectsource(), startintimeImage.getNobjectdestination(), null);
        }
        canvas.drawText("" + time / 30, getWidth() / 2 - 34, blackbarKaragoz.getNobjectdsth() / 2 + 25, paintTime);
        if (gameControl || time == 1800) {
            if (obje2.isLivecontrol()) {
                obje2.setNobjectsource(obje2.getNobjectsrcx(), obje2.getNobjectsrcy(), obje2.getNobjectsrcw(), obje2.getNobjectsrch());
                obje2.setNobjectdestination(obje2.getNobjectdstx(), obje2.getNobjectdsty(), obje2.getNobjectdstw(), obje2.getNobjectdsth());
                canvas.drawBitmap(obje2.getNobject(), obje2.getNobjectsource(), obje2.getNobjectdestination(), null);
            }

            if (obje1.isLivecontrol()) {
                obje1.setNobjectsource(obje1.getNobjectsrcx(), obje1.getNobjectsrcy(), obje1.getNobjectsrcw(), obje1.getNobjectsrch());
                obje1.setNobjectdestination(obje1.getNobjectdstx(), obje1.getNobjectdsty(), obje1.getNobjectdstw(), obje1.getNobjectdsth());
                canvas.drawBitmap(obje1.getNobject(), obje1.getNobjectsource(), obje1.getNobjectdestination(), null);
            }
            if (hacivat.isLivecontrol()) {
                hacivat.setNobjectsource(0, 0, 1957, 5110);
                hacivat.setNobjectdestination(hacivat.getNobjectdstx(), hacivat.getNobjectdsty(), hacivat.getNobjectdstw(), hacivat.getNobjectdsth());
                canvas.drawBitmap(hacivat.getNobject(), hacivat.getNobjectsource(), hacivat.getNobjectdestination(), null);
            }
            if (karagoz.isLivecontrol()) {
                karagoz.setNobjectsource(0, 0, 2215, 4892);
                karagoz.setNobjectdestination(karagoz.getNobjectdstx(), karagoz.getNobjectdsty(), 150, 330);
                canvas.drawBitmap(karagoz.getNobject(), karagoz.getNobjectsource(), karagoz.getNobjectdestination(), null);
            }

            if (splashEffectControl) {
                Log.i("Meyveler", "Çarpışma var");
                bomb.setNobjectsource(bomb.getNobjectsrcx(), bomb.getNobjectsrcy(), bomb.getNobjectsrcw(), bomb.getNobjectsrch());
                bomb.setNobjectdestination(bomb.getNobjectdstx(), bomb.getNobjectdsty(), bomb.getNobjectdstw(), bomb.getNobjectdsth());
                canvas.drawBitmap(bomb.getNobject(), bomb.getNobjectsource(), bomb.getNobjectdestination(), null);
            }
        }
        if (!hacivat.isLivecontrol()) {
            win.setNobjectsource(win.getNobjectsrcx(), win.getNobjectsrcy(), win.getNobjectsrcw(), win.getNobjectsrch());
            win.setNobjectdestination(win.getNobjectdstx(), win.getNobjectdsty(), win.getNobjectdstw(), win.getNobjectdsth());
            canvas.drawBitmap(win.getNobject(), win .getNobjectsource(), win.getNobjectdestination(), null);
            canvas.drawText("" + scorecoin, win.getNobjectdstx() + (win.getNobjectdstw() / (float) 1.45),win.getNobjectdsty() + (win.getNobjectdsth() / (float)(4)), paintCoinCount);
            canvas.drawText("" + scorehit, win.getNobjectdstx() + (win.getNobjectdstw() / (float)1.6),win.getNobjectdsty() + (win.getNobjectdsth() / (float)(2.52)), paintScoreCount);
            canvas.drawText("" + scoredamage, win.getNobjectdstx() + (win.getNobjectdstw() / (float)1.6),win.getNobjectdsty() + (win.getNobjectdsth() / (float)(1.87)), paintScoreCount);
            canvas.drawText("" + scoremiss, win.getNobjectdstx() + (win.getNobjectdstw() / (float)1.6),win.getNobjectdsty() + (win.getNobjectdsth() / (float)(1.48)), paintScoreCount);
        }
        if (!karagoz.isLivecontrol()) {
            lose.setNobjectsource(lose.getNobjectsrcx(), lose.getNobjectsrcy(), lose.getNobjectsrcw(), lose.getNobjectsrch());
            lose.setNobjectdestination(lose.getNobjectdstx(), lose.getNobjectdsty(), lose.getNobjectdstw(), lose.getNobjectdsth());
            canvas.drawBitmap(lose.getNobject(), lose.getNobjectsource(), lose.getNobjectdestination(), null);
            canvas.drawText("" + scorecoin, lose.getNobjectdstx() + (lose.getNobjectdstw() / (float)1.45),lose.getNobjectdsty()+ (lose.getNobjectdsth() /(float) (4)), paintCoinCount);
            canvas.drawText("" + scorehit, lose.getNobjectdstx() + (lose.getNobjectdstw() / (float)1.6),lose.getNobjectdsty() + (lose.getNobjectdsth() / (float)(2.52)), paintScoreCount);
            canvas.drawText("" + scoredamage, lose.getNobjectdstx() + (lose.getNobjectdstw() / (float)1.6),lose.getNobjectdsty() + (lose.getNobjectdsth() / (float)(1.87)), paintScoreCount);
            canvas.drawText("" + scoremiss, lose.getNobjectdstx() + (lose.getNobjectdstw() / (float)1.6),lose.getNobjectdsty() + (lose.getNobjectdsth() / (float)(1.48)), paintScoreCount);
        }
        //canvas.drawText(""+scorecoin, win.getNobjectdstx() + (win.getNobjectdstw() / 140 * 100),win.getNobjectdsty()+ (win.getNobjectdsth() / 377 *100),paintCoinCount);
    }

    public void setScore(){
        scoredamage = karagoz.getDamagecount();
        scoremiss = karagoz.getHitcount();
        scorehit = karagoz.getHitcount();
        scorecoin = 100 * scoredamage / (scorehit + scoremiss + 1) ;
        if(scorecoin > 100){
            scorecoin = 100;
        }else if(scorecoin < 0){
            scorecoin = scoredamage * 100;
        }

    }

    public void timeScroll() {
        if(!gameControl && time != 1800) {
            velocityend +=5;
            Log.i("Oyun Bitti","BİTTİ");
               lose.setNobjectdsty(lose.getNobjectdsty() - (20 + (int) velocityend));
               win.setNobjectdsty(win.getNobjectdsty() - (20 + (int) velocityend));
               if (lose.getNobjectdsty() <= getHeight() / 2 - lose.getNobjectdsth() / 2) {
                   lose.setNobjectdsty(getHeight() / 2 - lose.getNobjectdsth() / 2);
               }
               if (win.getNobjectdsty() <= getHeight() / 2 - win.getNobjectdsth() / 2) {
                   win.setNobjectdsty(getHeight() / 2 - win.getNobjectdsth() / 2);
                   }
        }
       }
        public void splashEffect () {
            if (animations.FruitCollision(animHacivat.getObject(), animKaragoz.getObject())) {
                fruitcollisionmusic();
                splashEffectControl = true;
                karagoz.setShoutcountrol(false);
                hacivat.setShoutcountrol(false);
                if (splashline > 4) {
                    splashrow++;
                    splashline = 0;
                }
                if (splashrow > 2) {
                    splashline = 0;
                    splashrow = 0;
                    splashEffectControl = false;
                    if (Utils.checkCollision(animKaragoz.getObject().getNobjectdestination(), karagoz.getNobjectdestination()))
                        damageKaragoz();
                    if (Utils.checkCollision(animHacivat.getObject().getNobjectdestination(), hacivat.getNobjectdestination()))
                        damageHacivat();
                    setObje1SetBase();
                    setObje2SetBase();

            }
            bomb.setNobjectdsty((obje1.getNobjectdsty() + obje2.getNobjectdsty()) / 2 - 32);
            bomb.setNobjectdstx((obje1.getNobjectdstx() + obje2.getNobjectdstx()) / 2 - 32);
            bomb.setNobjectsrcx(splashline * bomb.getNobjectsrcw());
            bomb.setNobjectsrcy(splashrow * bomb.getNobjectsrch());
            splashline++;
        }
    }
        ///////////////MÜZİKLER\\\\\\\\\\\\\\\
        //MEYVE ÇARPIŞMA MUZİĞİ
        private void fruitcollisionmusic () {

            if(mediafruitcol.isPlaying()){}
            else{
            mediafruitcol.start();}
           }

        //ARKAPLAN MUZİĞİ
        private void backgroundmusic () {

            if(mediaback.isPlaying()){}
            mediaback.start();

        }
        //GİRİŞ MÜZİĞİ
         private void titlemusic () {

            if(mediatitle.isPlaying()){}
            mediatitle.start();
         }
        //ZIPLAMA MUZİĞİ
        private void jumpmusic () {

            if(mediajump.isPlaying()){}
            mediajump.start();
         }
       //KAYBETME MUZİĞİ
        private void defeatmusic () {
            mediaback.release();
            if(mediadefeat.isPlaying()){}
            mediadefeat.start();
    }

    public void winkontrol() {
        if (hacivat.getHealth() <= 0) {
            setScore();
            hacivat.setLivecontrol(false);
            gameControl = false;
            Log.i(Hacivat, "dead");
        }
    }

    public void losekontrol() {
        if (karagoz.getHealth() <= 0) {
            setScore();
            scorecoin = 0;
            defeatmusic();
            karagoz.setLivecontrol(false);
            gameControl = false;
            Log.i(Karagoz, "dead");
        }

    }

    //Oyun başlamadan önceki geri sayım metodu
    public void startingTimeCountDown() {
        if (!gameControl && time == 1800) {

            startingtime--;
            if (startingtime >= 90) {
                startintimeImage.setNobjectsrcx(2 * startintimeImage.getNobjectsrcw());
            } else if (startingtime >= 60) {
                startintimeImage.setNobjectsrcx(startintimeImage.getNobjectsrcw());
            } else if (startingtime >= 30) {
                startintimeImage.setNobjectsrcx(0);
            } else {

                gameControl = true;
                backgroundmusic();
            }
        }
    }

        public void damageKaragoz () {
            hacivat.decBulletCount();
            animHacivat.getTargetCharacter().decHealth(animHacivat.getObject());
            setKaragozHealth();
            hacivat.setHitcount(hacivat.getHitcount() + 1);
            animHacivat.getTargetCharacter().setDamagecount(animHacivat.getTargetCharacter().getDamagecount() + 1);
            chooseFruitHacivat();
        }
        public void damageHacivat () {
            animKaragoz.getTargetCharacter().decHealth(animKaragoz.getObject());
            setHacivatHealth();
            karagoz.decBulletCount();
            karagoz.setHitcount(hacivat.getHitcount() + 1);
            animKaragoz.getTargetCharacter().setDamagecount(animKaragoz.getTargetCharacter().getDamagecount() + 1);
            chooseFruitKaragoz();

        }
        public void hacivatShot () {
            //Ateş
            if (hacivat.isShoutControl()) {
                animHacivat.ShoutAnımationHacivat();
            }
            if (hacivat.getBulletcount() == 0) {
                hacivat.setShoutcountrol(false);
                Log.i(Hacivat, "Mermi Bitti");
                obje1.setLivecontrol(false);
            } else obje1.setLivecontrol(true);
            if (!animHacivat.ShoutAnımationHacivat()) {
                hacivat.setShoutcountrol(false);
                setObje1SetBase();
                damageKaragoz();
            } else if (obje1.getNobjectdstx() + obje1.getNobjectdstw() > getWidth()) {
                hacivat.setShoutcountrol(false);
                setObje1SetBase();
                chooseFruitHacivat();
                hacivat.decBulletCount();
                hacivat.setMisscount(hacivat.getHitcount() + 1);
            }
        }
        public void karagozShot () {
            if (karagoz.isShoutControl()) {
                animKaragoz.ShoutAnımationKaragoz();
            }
            if (karagoz.getBulletcount() == 0) {
                karagoz.setShoutcountrol(false);
                Log.i(Karagoz, "Mermi Bitti");
                obje2.setLivecontrol(false);
            } else obje2.setLivecontrol(true);
            if (!animKaragoz.ShoutAnımationKaragoz()) {
                karagoz.setShoutcountrol(false);
                damageHacivat();
                Log.i("Ai Player", "" + animKaragoz.getTargetCharacter().getHealth());
                setObje2SetBase();
            } else if (obje2.getNobjectdstx() < 0) {
                karagoz.setShoutcountrol(false);
                setObje2SetBase();
                chooseFruitKaragoz();
                karagoz.decBulletCount();
                hacivat.setMisscount(hacivat.getHitcount() + 1);
            }
        }

        //Obje1 i sahibi olan karakterin eline konumlandırır
        public void setObje1SetBase () {
            obje1.setNobjectdsty(hacivat.getNobjectdsty() + hacivat.getNobjectdsth() / 4 + 10);
            obje1.setNobjectdstx(hacivat.getNobjectdstx() + hacivat.getNobjectdstw() - 20);
        }
        //Obje2 i sahibi olan karakterin eline konumlandırır
        public void setObje2SetBase () {
            obje2.setNobjectdsty(karagoz.getNobjectdsty() + karagoz.getNobjectdsth() / 4);
            obje2.setNobjectdstx(karagoz.getNobjectdstx() - obje2.getNobjectdstw() + 20);
        }
        //HACİVAT ZIPLAMA KONTROLU
        public void hacivatJump () {
            if (!hacivat.isJumpcontrol()) {
                hacivat.setJumpcontrol(true);
            }
            if (hacivat.jump()) {
                hacivat.setJumpcontrol(false);
                hacivat.setNobjectdsty(getHeight() - hacivat.getNobjectdsth());
            }
        }
        //KARAGÖZ ZIPLAMA KONTROLU
        public void karagozJump () {

            if (!karagoz.isJumpcontrol()) {
                karagoz.setJumpcontrol(true);
            }
            if (karagoz.jump()) {
                karagoz.setJumpcontrol(false);
                karagoz.setNobjectdsty(getHeight() - karagoz.getNobjectdsth());
            }
        }
        //AI DEFANS
        public void aiPlayerModeAttack () {
            if (animHacivat.AIAttackCollision(obje2)) {
                if (animHacivat.AIDefenceCollision(obje2)) {
                    if (!karagoz.isJumpcontrol() && !hacivat.isJumpcontrol() && !splashEffectControl)
                        hacivat.setShoutcountrol(true);
                    else if (hacivat.isJumpcontrol() && karagoz.isJumpcontrol() && !splashEffectControl)
                        hacivat.setShoutcountrol(true);
                    hacivat.setJumpcontrol(true);
                }
            } else if (karagoz.getBulletcount() == 0 && !splashEffectControl)
                hacivat.setShoutcountrol(true);
            else if (!karagoz.isJumpcontrol() && !karagoz.isShoutControl() && !splashEffectControl) {
                hacivat.setShoutcountrol(true);
            }
        }
        public void aiPlayerModeTrack () {
            if (animHacivat.AIAttackCollision(obje2)) {
                if (animHacivat.AIDefenceCollision(obje2)) {
                    if (!karagoz.isJumpcontrol() && !hacivat.isJumpcontrol() && !splashEffectControl)
                        hacivat.setShoutcountrol(true);
                    else if (hacivat.isJumpcontrol() && karagoz.isJumpcontrol() && !splashEffectControl)
                        hacivat.setShoutcountrol(true);
                    hacivat.setJumpcontrol(true);
                }
            } else if (karagoz.getBulletcount() == 0) hacivat.setShoutcountrol(true);
            else if (!karagoz.isJumpcontrol() && !karagoz.isShoutControl() && !splashEffectControl) {
                hacivat.setShoutcountrol(true);
            }
        }
        public void aiPlayerModeSafeAttack () {
            if (animHacivat.AIAttackCollision(obje2)) {
                if (!karagoz.isJumpcontrol() && !hacivat.isJumpcontrol() && !splashEffectControl)
                    hacivat.setShoutcountrol(true);
                else if (hacivat.isJumpcontrol() && karagoz.isJumpcontrol() && !splashEffectControl)
                    hacivat.setShoutcountrol(true);
                if (animHacivat.AIDefenceCollision(obje2)) {
                    hacivat.setJumpcontrol(true);
                }
            } else if (karagoz.getBulletcount() == 0) hacivat.setShoutcountrol(true);
            else if (!karagoz.isJumpcontrol() && !karagoz.isShoutControl() && !splashEffectControl) {
                hacivat.setShoutcountrol(true);
            }
        }
        //AI CANA VE MERMIYE GORE SAVAŞ MODUNU SEÇME
        public void aiPlayer (Character character, Animations animCharacter){
            if (hacivat.getHealth() > 70 && hacivat.getBulletcount() > 7) {
                aiPlayerModeSafeAttack();
            } else if (hacivat.getHealth() > 70 && hacivat.getBulletcount() > 4) {
                aiPlayerModeAttack();
            } else if (hacivat.getHealth() > 50 && hacivat.getBulletcount() > 7) {
                aiPlayerModeTrack();
            } else if (hacivat.getHealth() > 50 && hacivat.getBulletcount() > 4) {
                aiPlayerModeAttack();
            } else if (hacivat.getHealth() > 30 && hacivat.getBulletcount() > 7) {
                aiPlayerModeSafeAttack();
            } else if (hacivat.getHealth() > 30 && hacivat.getBulletcount() > 4) {
                aiPlayerModeAttack();
            } else if (hacivat.getHealth() > 0 && hacivat.getBulletcount() > 0) {
                aiPlayerModeSafeAttack();
            } else {
                hacivat.setShoutcountrol(false);
                obje1.setLivecontrol(false);
            }

        }
        //Meyve Secimi
        public void chooseFruitKaragoz () {
            switch (randFruitKaragoz.nextInt(7)) {
                case 0:
                    obje2.setNobjectsrcx(peachsrcx);
                    obje2.setNobjectsrcy(peachsrcy);
                    obje2.setNobjectsrcw(peachsrcw);
                    obje2.setNobjectsrch(peachsrch);
                    obje2.setVelocity(peachv);
                    obje2.setWeight(pearw);
                    break;
                case 1:
                    obje2.setNobjectsrcx(watermelonsrcx);
                    obje2.setNobjectsrcy(watermelonsrcy);
                    obje2.setNobjectsrcw(watermelonsrcw);
                    obje2.setNobjectsrch(watermelonsrch);
                    obje2.setVelocity(watermelonv);
                    obje2.setWeight(watermelonw);
                    break;
                case 2:
                    obje2.setNobjectsrcx(pearsrcx);
                    obje2.setNobjectsrcy(pearsrcy);
                    obje2.setNobjectsrcw(pearsrcw);
                    obje2.setNobjectsrch(pearsrch);
                    obje2.setVelocity(pearv);
                    obje2.setWeight(pearw);
                    break;
                case 3:
                    obje2.setNobjectsrcx(plumsrcx);
                    obje2.setNobjectsrcy(plumsrcy);
                    obje2.setNobjectsrcw(plumsrcw);
                    obje2.setNobjectsrch(plumsrch);
                    obje2.setVelocity(plumv);
                    obje2.setWeight(plumw);
                    break;
                case 4:
                    obje2.setNobjectsrcx(strawberrysrcx);
                    obje2.setNobjectsrcy(strawberrysrcy);
                    obje2.setNobjectsrcw(strawberrysrcw);
                    obje2.setNobjectsrch(strawberrysrch);
                    obje2.setVelocity(strawberryv);
                    obje2.setWeight(strawberryw);
                    break;
                case 5:
                    obje2.setNobjectsrcx(orangesrcx);
                    obje2.setNobjectsrcy(orangesrcy);
                    obje2.setNobjectsrcw(orangesrcw);
                    obje2.setNobjectsrch(orangesrch);
                    obje2.setVelocity(orangev);
                    obje2.setWeight(orangew);
                    break;
                case 6:
                    obje2.setNobjectsrcx(tomatosrcx);
                    obje2.setNobjectsrcy(tomatosrcy);
                    obje2.setNobjectsrcw(tomatosrcw);
                    obje2.setNobjectsrch(tomatosrch);
                    obje2.setVelocity(tomatov);
                    obje2.setWeight(tomatow);
                    break;
            }

    }

    public void chooseFruitHacivat() {
        switch (randFruitHacivat.nextInt(7)) {
            case 0:
                obje1.setNobjectsrcx(peachsrcx);
                obje1.setNobjectsrcy(peachsrcy);
                obje1.setNobjectsrcw(peachsrcw);
                obje1.setNobjectsrch(peachsrch);
                obje1.setVelocity(pearv);
                obje1.setWeight(pearw);
                break;
            case 1:
                obje1.setNobjectsrcx(watermelonsrcx);
                obje1.setNobjectsrcy(watermelonsrcy);
                obje1.setNobjectsrcw(watermelonsrcw);
                obje1.setNobjectsrch(watermelonsrch);
                obje1.setVelocity(watermelonv);
                obje1.setWeight(watermelonw);
                break;
            case 2:
                obje1.setNobjectsrcx(pearsrcx);
                obje1.setNobjectsrcy(pearsrcy);
                obje1.setNobjectsrcw(pearsrcw);
                obje1.setNobjectsrch(pearsrch);
                obje1.setVelocity(pearv);
                obje1.setWeight(pearw);
                break;
            case 3:
                obje1.setNobjectsrcx(plumsrcx);
                obje1.setNobjectsrcy(plumsrcy);
                obje1.setNobjectsrcw(plumsrcw);
                obje1.setNobjectsrch(plumsrch);
                obje1.setVelocity(plumv);
                obje1.setWeight(plumw);
                break;
            case 4:
                obje1.setNobjectsrcx(strawberrysrcx);
                obje1.setNobjectsrcy(strawberrysrcy);
                obje1.setNobjectsrcw(strawberrysrcw);
                obje1.setNobjectsrch(strawberrysrch);
                obje1.setVelocity(strawberryv);
                obje1.setWeight(strawberryw);
                break;
            case 5:
                obje1.setNobjectsrcx(orangesrcx);
                obje1.setNobjectsrcy(orangesrcy);
                obje1.setNobjectsrcw(orangesrcw);
                obje1.setNobjectsrch(orangesrch);
                obje1.setVelocity(orangev);
                obje1.setWeight(orangew);
                break;
            case 6:
                obje1.setNobjectsrcx(tomatosrcx);
                obje1.setNobjectsrcy(tomatosrcy);
                obje1.setNobjectsrcw(tomatosrcw);
                obje1.setNobjectsrch(tomatosrch);
                obje1.setVelocity(tomatov);
                obje1.setWeight(tomatow);
                break;
        }

        }


        public void keyPressed ( int key){

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
        if (x >= jump.getNobjectdstx() && x <= jump.getNobjectdstx() + jump.getNobjectdstw() && y >= jump.getNobjectdsty() && y <= jump.getNobjectdsty() + jump.getNobjectdsth()) {
            karagoz.setJumpcontrol(true);
        }
        if (x >= fire.getNobjectdstx() && x <= fire.getNobjectdstx() + fire.getNobjectdstw() && y >= fire.getNobjectdsty() && y <= fire.getNobjectdsty() + fire.getNobjectdsth()) {
            karagoz.setShoutcountrol(true);
        }
        if(!hacivat.isLivecontrol()){
            if(x >= win.getNobjectdstx() + (win.getNobjectdstw() / 6 * 1.75) && x <= win.getNobjectdstx() + (win.getNobjectdstw() / 2 - win.getNobjectdstw() / 24 )&& y >= win.getNobjectdsty() + (win.getNobjectdsth() * 5 / 6) && y <= getHeight() ){
                Log.i("Restart","Basılı");
                GameCanvas mc2 = new GameCanvas(root);
                root.canvasManager.setCurrentCanvas(mc2);
            }
        }else if(!karagoz.isLivecontrol()){
            if(x >= lose.getNobjectdstx() + (lose.getNobjectdstw() / 6 * 1.75) && x <= lose.getNobjectdstx() + lose.getNobjectdstw() / 2 - lose.getNobjectdstw() / 24 && y >= lose.getNobjectdsty() + (lose.getNobjectdsth() * 5 / 6) && y <= getHeight() ){
                Log.i("Restart","Basılı");
                GameCanvas mc2 = new GameCanvas(root);
                root.canvasManager.setCurrentCanvas(mc2);
            }
        }else {
            if (x >= restart.getNobjectdstx() && x <= restart.getNobjectdstx() + restart.getNobjectdstw() && y >= restart.getNobjectdsty() && y <= restart.getNobjectdsty() + restart.getNobjectdsth()) {
                GameCanvas mc2 = new GameCanvas(root);
                root.canvasManager.setCurrentCanvas(mc2);
            }
            if (x >= backbutton.getNobjectdstx() && x <= backbutton.getNobjectdstx() + backbutton.getNobjectdstw() && y >= backbutton.getNobjectdsty() && y <= backbutton.getNobjectdsty() + backbutton.getNobjectdsth()) {
                MenuCanvas mc1 = new MenuCanvas(root);
                root.canvasManager.setCurrentCanvas(mc1);
            }
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
