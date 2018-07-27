package com.ngdroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

import com.gamelab.karagozhacivat.R;

import java.util.Random;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;


public class GameCanvas extends BaseCanvas {
    //Değişkenler
    public Skills skil=new Skills();
    public MediaPlayer mediaback, mediadefeat,mediafire,mediaapplause, mediafruitcol, mediajump,mediatime;
    public static int thundereffectline = 0, thundereffectRow = 0;
    public static int fruiteffectLine = 0, fruiteffectRow = 0;
    public static int iceeffectLine = 0, iceeffectRow = 0;
    private boolean gameControl = true, splashEffectControl = false, animControlHacivat, animControlKaragoz;
    private Character karagoz, hacivat;
    private Animations animKaragoz, animHacivat, animations;
    private Nobject arkaplan, backbutton, restart, fire, jump, bomb, win, lose,iceeffect,fruiteffect,thundereffect;
    private Nobject blackbarHacivat, blackbarKaragoz, greenbarKaragoz, greenbarHacivat, startintimeImage;
    private FruitObject obje1, obje2;
    private int splashrow = 0, splashline = 0;
    private double velocityend;
    private Paint paintTime, paintStartingTime, paintScoreCount, paintCoinCount;
    private Random randFruitHacivat, randFruitKaragoz;
    private int touchdownx, touchdowny, time, startingtime;
    private final String Hacivat = "Hacivat";
    private final String Karagoz = "Karagoz";
    private final int peachsrcx = 0, peachsrcy = 0, watermelonsrcx = 90, watermelonsrcy = 0, pearsrcx = 182, pearsrcy = 0, plumsrcx = 255, plumsrcy = 0, strawberrysrcx = 340, strawberrysrcy = 0, orangesrcx = 422, orangesrcy = 0, tomatosrcx = 0, tomatosrcy = 107;
    private final int peachsrcw = 89, peachsrch = 107, watermelonsrcw = 90, watermelonsrch = 94, pearsrcw = 73, pearsrch = 107, plumsrcw = 84, plumsrch = 107, strawberrysrcw = 82, strawberrysrch = 107, orangesrcw = 104, orangesrch = 107, tomatosrcw = 110, tomatosrch = 118;
    private final int peachv = 17, peachw = 10, watermelonv = 15, watermelonw = 10, pearv = 20, pearw = 10, plumv = 25, plumw = 10, strawberryv = 27, strawberryw = 10, orangev = 25, orangew = 10, tomatov = 27, tomatow = 10;
    private int scorehit, scoredamage, scoremiss, scorecoin;
    private int karagozAnimRow, karagozAnimLine, hacivatAnimLine, hacivatAnimRow;
    private int gamecoin;
    private  SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private boolean multiplayermode;
    private int timerkaragoz=90,timerhacivat=90;

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
        sharedPreference();
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
        setupFeffect();
        setupThunder();
        setupIceeffect();
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
    public void sharedPreference(){
       preferences = PreferenceManager.getDefaultSharedPreferences(root.activity);
       editor = preferences.edit();
        if(preferences.contains("myCoin")){
            gamecoin = preferences.getInt("myCoin",0);
        }else {
            gamecoin = 0;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("myCoin", gamecoin);
            editor.commit();
        }

    }


    //RESİMLERİN TANINMASI
    public void pictures()
    {
        iceeffect.setNobject(Utils.loadImage(root, "ice.png"));
        thundereffect.setNobject(Utils.loadImage(root, "light.png"));
        fruiteffect.setNobject(Utils.loadImage(root, "fruiteffect.png"));
        startintimeImage.setNobject(Utils.loadImage(root, "alphabetnumb.png"));
        obje1.setNobject(Utils.loadImage(root, "fruits.png"));
        obje2.setNobject(Utils.loadImage(root, "fruits.png"));
        arkaplan.setNobject(Utils.loadImage(root, "arkaplan.png"));
        karagoz.setNobject(Utils.loadImage(root, "karagoz.png"));
        hacivat.setNobject(Utils.loadImage(root, "hacivat.png"));
        backbutton.setNobject(Utils.loadImage(root, "backbutton.png"));
        restart.setNobject(Utils.loadImage(root, "restart.png"));
        jump.setNobject(Utils.loadImage(root, "jump.png"));
        fire.setNobject(Utils.loadImage(root, "fire.png"));
        blackbarHacivat.setNobject(Utils.loadImage(root, "blackbar2.png"));
        blackbarKaragoz.setNobject(Utils.loadImage(root, "blackbar.png"));
        greenbarKaragoz.setNobject(Utils.loadImage(root, "greenbar.png"));
        greenbarHacivat.setNobject(Utils.loadImage(root, "greenbar2.png"));
        win.setNobject(Utils.loadImage(root, "win.png"));
        lose.setNobject(Utils.loadImage(root, "lose.png"));
        bomb.setNobject(Utils.loadImage(root, "bom.png"));
    }

    //SESLERİN TANINMASI
    public void sounds() {
       // mediakaragozdamage = MediaPlayer.create(root.activity,R.raw.karagozdamage);
       // mediahacivatdamage = MediaPlayer.create(root.activity,R.raw.hacivatdamage);
        mediafire = MediaPlayer.create(root.activity,R.raw.fire);
        mediafruitcol = MediaPlayer.create(root.activity, R.raw.fruitcollesion);
        mediaback = MediaPlayer.create(root.activity, R.raw.background);
        mediajump = MediaPlayer.create(root.activity, R.raw.jump);
        mediadefeat = MediaPlayer.create(root.activity, R.raw.defeat);
        mediatime=MediaPlayer.create(root.activity,R.raw.time);
        mediaapplause=MediaPlayer.create(root.activity,R.raw.applause);}

    //YAZILAR
    public void setupText() {
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
        paintScoreCount.setTypeface(Typeface.create("ObelixPro", Typeface.BOLD));
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
        karagoz.setNobjectsrcx(20);
        karagoz.setNobjectsrcy(0);
        karagoz.setNobjectsrcw(480);
        karagoz.setNobjectsrch(718);
        karagoz.setNobjectdstw(211);
        karagoz.setNobjectdsth(330);
        karagoz.setNobjectdsty(getHeight() - karagoz.getNobjectdsth());
        karagoz.setNobjectdstx(getWidth() - karagoz.getNobjectdstw() - 300);
        karagoz.setJumpcontrol(false);
        hacivat.setShoutcountrol(false);
        karagozAnimRow = 0;
        karagozAnimLine = 0;
        animControlKaragoz = true;
    }

    public void setupHacivat() {
        hacivat = new Character();
        hacivat.setNobjectsrcx(0);
        hacivat.setNobjectsrcy(0);
        hacivat.setNobjectsrcw(390);
        hacivat.setNobjectsrch(718);
        hacivat.setNobjectdstw(179);
        hacivat.setNobjectdsth(330);
        hacivat.setNobjectdsty(getHeight() - hacivat.getNobjectdsth());
        hacivat.setNobjectdstx(400);
        hacivat.setJumpcontrol(false);
        hacivat.setShoutcountrol(false);
        hacivatAnimRow = 0;
        hacivatAnimLine = 0;
        animControlHacivat = true;
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
    public void setHacivatHealth() {
        greenbarHacivat.setNobjectdstx(getWidth() / 2 - 220 - hacivat.getHealth() * 4);
        greenbarHacivat.setNobjectdstw(getWidth() / 2 - 220 - greenbarHacivat.getNobjectdstx());

    }


    ////Karakter karakterin sağlık durumunu günceller
    public void setKaragozHealth(){
        greenbarKaragoz.setNobjectdstw(karagoz.getHealth() * 4);
    }

    //Meyve animasyonu setup
    public void setupFeffect()
    {   fruiteffect=new Nobject();
        fruiteffect.setNobjectsrcx(0);
        fruiteffect.setNobjectsrcy(0);
        fruiteffect.setNobjectsrcw(192);
        fruiteffect.setNobjectsrch(192);
        fruiteffect.setNobjectdstw(128);
        fruiteffect.setNobjectdsth(128);
        fruiteffect.setNobjectdsty((obje1.getNobjectdsty() + obje2.getNobjectdsty()) / 2 - 32);
        fruiteffect.setNobjectdstx((obje1.getNobjectdstx() + obje2.getNobjectdstx()) / 2 - 32);

    }
    //Çarpışma efekti setup
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
    //Şimşek efekti setup
    public void setupThunder()
    {
        thundereffect=new Nobject();
        thundereffect.setNobjectsrcx(0);
        thundereffect.setNobjectsrcy(0);
        thundereffect.setNobjectsrcw(192);
        thundereffect.setNobjectsrch(192);
        thundereffect.setNobjectdstw(128);
        thundereffect.setNobjectdsth(128);
        thundereffect.setNobjectdsty((obje1.getNobjectdsty() + obje2.getNobjectdsty()) / 2 - 32);
        thundereffect.setNobjectdstx((obje1.getNobjectdstx() + obje2.getNobjectdstx()) / 2 - 32);
    }
   public void setupIceeffect()
   {
       iceeffect=new Nobject();
       iceeffect.setNobjectsrcx(0);
       iceeffect.setNobjectsrcy(0);
       iceeffect.setNobjectsrcw(192);
       iceeffect.setNobjectsrch(192);
       iceeffect.setNobjectdstw(128);
       iceeffect.setNobjectdsth(128);
       iceeffect.setNobjectdsty((obje2.getNobjectdsty()));
       iceeffect.setNobjectdstx((obje2.getNobjectdstx()));
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
        thunderEffect();
        winkontrol();
        losekontrol();
        timeScroll();
        iceEffect();
        Skil();
        if (gameControl) {
            if(!multiplayermode){
            splashEffect();
            time--;
            if (karagoz.isJumpcontrol()) karagozJump();
            if (karagoz.isShoutControl()) karagozShot();
            else if (!splashEffectControl) setObje2SetBase();
            if (hacivat.isJumpcontrol()) hacivatJump();
            if (hacivat.isShoutControl()) hacivatShot();
            else if (!splashEffectControl) setObje1SetBase();
            aiPlayer(hacivat, animHacivat);
            }






            Log.i("GameCanvas", "Oyun Devam Ediyor");
        }

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


        if (Utils.checkCollision(obje2.getNobjectdestination(), hacivat.getNobjectdestination()))
        {
            iceeffect.setNobjectsource(iceeffect.getNobjectsrcx(), iceeffect.getNobjectsrcy(), iceeffect.getNobjectsrcw(), iceeffect.getNobjectsrch());
            iceeffect.setNobjectdestination(iceeffect.getNobjectdstx(), iceeffect.getNobjectdsty(), iceeffect.getNobjectdstw(), iceeffect.getNobjectdsth());
            canvas.drawBitmap(iceeffect.getNobject(), iceeffect.getNobjectsource(), iceeffect.getNobjectdestination(), null);
        }
        //Oyun başlamadıysa ve zaman 1800 ise başlangıc sğresi ekranda gözükecek
        if (!gameControl && time == 1800) {

            startintimeImage.setNobjectsource(startintimeImage.getNobjectsrcx(), startintimeImage.getNobjectsrcy(), startintimeImage.getNobjectsrcw(), startintimeImage.getNobjectsrch());
            startintimeImage.setNobjectdestination(startintimeImage.getNobjectdstx(), startintimeImage.getNobjectdsty(), startintimeImage.getNobjectdstw(), startintimeImage.getNobjectdsth());
            canvas.drawBitmap(startintimeImage.getNobject(), startintimeImage.getNobjectsource(), startintimeImage.getNobjectdestination(), null);
        }
        canvas.drawText("" + time / 30, getWidth() / 2 - 34, blackbarKaragoz.getNobjectdsth() / 2 + 25, paintTime);
        if (gameControl || time == 1800) {


            if(gameControl) {
                thundereffect.setNobjectsource(thundereffect.getNobjectsrcx(), thundereffect.getNobjectsrcy(), thundereffect.getNobjectsrcw(), thundereffect.getNobjectsrch());
                thundereffect.setNobjectdestination(thundereffect.getNobjectdstx(), thundereffect.getNobjectdsty(), thundereffect.getNobjectdstw(), thundereffect.getNobjectdsth());
                canvas.drawBitmap(thundereffect.getNobject(), thundereffect.getNobjectsource(), thundereffect.getNobjectdestination(), null);
            }
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
                hacivat.setNobjectsource(hacivat.getNobjectsrcx(), hacivat.getNobjectsrcy(), hacivat.getNobjectsrcw(), hacivat.getNobjectsrch());
                hacivat.setNobjectdestination(hacivat.getNobjectdstx(), hacivat.getNobjectdsty(), hacivat.getNobjectdstw(), hacivat.getNobjectdsth());
                canvas.drawBitmap(hacivat.getNobject(), hacivat.getNobjectsource(), hacivat.getNobjectdestination(), null);
            }
            if (karagoz.isLivecontrol()) {
                karagoz.setNobjectsource(karagoz.getNobjectsrcx(), karagoz.getNobjectsrcy(), karagoz.getNobjectsrcw(), karagoz.getNobjectsrch());
                karagoz.setNobjectdestination(karagoz.getNobjectdstx(), karagoz.getNobjectdsty(), karagoz.getNobjectdstw(), karagoz.getNobjectdsth());
                canvas.drawBitmap(karagoz.getNobject(), karagoz.getNobjectsource(), karagoz.getNobjectdestination(), null);
            }
            if(karagoz.isShoutControl()){
                fruiteffect.setNobjectsource(fruiteffect.getNobjectsrcx(),fruiteffect.getNobjectsrcy(),fruiteffect.getNobjectsrcw(),fruiteffect.getNobjectsrch());
                fruiteffect.setNobjectdestination(fruiteffect.getNobjectdstx(), fruiteffect.getNobjectdsty(),fruiteffect.getNobjectdstw(),fruiteffect.getNobjectdsth());
                canvas.drawBitmap(fruiteffect.getNobject(),fruiteffect.getNobjectsource(),fruiteffect.getNobjectdestination(), null);
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
        root.gui.drawText(canvas, "FPS: " + root.appManager.getFrameRate() + " / " + root.appManager.getFrameRateTarget(), getWidth()/10, getHeight()/15, 0);
        //canvas.drawText(""+scorecoin, win.getNobjectdstx() + (win.getNobjectdstw() / 140 * 100),win.getNobjectdsty()+ (win.getNobjectdsth() / 377 *100),paintCoinCount);
    }



    //SKİLLER
    public void Skil() {
        /////////////
        if (skil.Poison(karagoz, true)) {
            if (((timerkaragoz == 30) || (timerkaragoz == 60) || (timerkaragoz == 90))) {
                skil.Poison(karagoz, true);
            }
            else if (timerkaragoz == 0) {
                skil.Poison(karagoz, false);
                timerkaragoz = 90;
            }
            timerkaragoz--;

        }
        if (skil.Poison(hacivat, true)) {
            if (((timerhacivat == 30) || (timerhacivat == 60) || (timerhacivat == 90))) {
                skil.Poison(hacivat, true);
            }
            else if (timerhacivat == 0) {
                skil.Poison(hacivat, false);
                timerhacivat = 90;
            }
            timerhacivat--;

        }
        //////////////
        else if (skil.Ice(karagoz, true)) {

            if (timerkaragoz != 0) {
                skil.Ice(karagoz, true);
                timerkaragoz--;
            }
            else if (timerkaragoz == 0) {
                skil.Ice(karagoz, false);
                timerkaragoz = 90;
            }

        }

        else if (skil.Ice(hacivat, true)) {

            if (timerhacivat != 0) {
                skil.Ice(hacivat, true);
                timerkaragoz--;
            }
            else if (timerhacivat == 0) {
                skil.Ice(hacivat, false);
                timerhacivat = 90;
            }
            timerhacivat--;
        }

        ///////////////
        else if (skil.BigAttack(karagoz, true)) {
            if (!karagoz.isShoutControl()) {
                skil.BigAttack(karagoz, true);
                skil.BigAttack(karagoz, false);
            }
        } else if (skil.BigAttack(hacivat, true)) {
            if (!hacivat.isShoutControl()) {
                skil.BigAttack(hacivat, true);
                skil.BigAttack(hacivat, false);
            }
        }
        /////////////////
    }



    public void setScore() {
        scoredamage = karagoz.getDamagecount();
        scoremiss = karagoz.getHitcount();
        scorehit = karagoz.getHitcount();
        scorecoin = 100 * scoredamage / (scorehit + scoremiss + 1);
        if (scorecoin > 100) {
            scorecoin = 100;
        } else if (scorecoin < 0) {
            scorecoin = scoredamage * 100;
        }
    }
    //OYUN BİTİŞ SAYIM
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
    //ÇARPIŞMA EFEKTİ
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
    //MEYVE GİDİŞ EFEKTİ
        public void fruitEffect()
        {
                if (fruiteffectLine > 4) {
                    fruiteffectRow++;
                    fruiteffectLine = 0;
                }
                if (fruiteffectRow > 6) {
                    fruiteffectRow = 0;
                    fruiteffectLine = 0;
                }
                fruiteffect.setNobjectdsty(obje2.getNobjectdsty()-30);
                fruiteffect.setNobjectdstx(obje2.getNobjectdstx()-60);
                fruiteffect.setNobjectsrcx(fruiteffect.getNobjectsrcw() * fruiteffectLine);
                fruiteffect.setNobjectsrcy(fruiteffect.getNobjectsrch() * fruiteffectRow);
                fruiteffectLine++;
        }
    //YANSIMA EFEKTİ
        public void thunderEffect()
        {
            Random random=new Random();
            int randomx=random.nextInt(250);
            int randomy=random.nextInt(50);
            int randomleftright=random.nextInt(4);

            if (thundereffectline > 4) {
               thundereffectRow++;
                thundereffectline = 0;
            }
            if (thundereffectRow > 4) {
                thundereffectRow = 0;
                thundereffectline= 0;
            }
            //0 Sol 1 Sağ
            if(randomleftright==0){
                thundereffect.setNobjectdstx((getWidth()/2)-randomx);
                thundereffect.setNobjectdsty((getHeight()/3)+randomy);
            }
            if(randomleftright==1){
                thundereffect.setNobjectdstx((getWidth()/2)+randomx);
                thundereffect.setNobjectdsty((getHeight()/3)+randomy);
            }


            thundereffect.setNobjectsrcx(thundereffect.getNobjectsrcw() * thundereffectline);
            thundereffect.setNobjectsrcy(thundereffect.getNobjectsrch() * thundereffectRow);
            thundereffectline++;
        }
    //BUZ EFEKTİ
        public void iceEffect()
        {
            if (iceeffectLine > 4) {
                iceeffectRow++;
                iceeffectLine = 0;
            }
            if (iceeffectRow > 3) {
                iceeffectRow = 0;
                iceeffectLine= 0;
            }
            iceeffect.setNobjectdsty(hacivat.getNobjectdsty());
            iceeffect.setNobjectdstx(hacivat.getNobjectdstx());
            iceeffect.setNobjectsrcx(iceeffect.getNobjectsrcw() * iceeffectLine);
            iceeffect.setNobjectsrcy(iceeffect.getNobjectsrch() * iceeffectRow);
            iceeffectLine++;

        }
    ///////////////MÜZİKLER\\\\\\\\\\\\\\\
        //KAZANMA MÜZİĞİ
        private void applausemusic()
        {
        if(mediaapplause.isPlaying()){}
        else{mediaapplause.start();}

        }

        //ATEŞ MÜZİĞİ
        private void firemusic(){

            if(mediafire.isPlaying()){}
            else{
                mediafire.start();}
        }
        //GERİ SAYIM MÜZİĞİ
        private void timemusic(){

            if(mediatime.isPlaying()){}
            else{
                mediatime.start();}
        }
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

        //ZIPLAMA MUZİĞİ
        private void jumpmusic () {
            mediajump.start();
            if(mediajump.isPlaying()){}
         }
       //KAYBETME MUZİĞİ
        private void defeatmusic () {
            mediaback.release();
            if(mediadefeat.isPlaying()){}
            mediadefeat.start();
    }

        //KAZANMA KONTROL
        public void winkontrol() {
        if (hacivat.getHealth() <= 0) {
            setScore();
            mediaback.release();
            applausemusic();
            hacivat.setLivecontrol(false);
            gameControl = false;
            Log.i(Hacivat, "dead");
        }
    }
        //KAYBETME KONTROL
        public void losekontrol() {
        if (karagoz.getHealth() <= 0) {
            setScore();
            mediaback.release();
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
            timemusic();
            startingtime--;
            if (startingtime >= 90) {
                startintimeImage.setNobjectsrcx(2 * startintimeImage.getNobjectsrcw());
            } else if (startingtime >= 60) {
                startintimeImage.setNobjectsrcx(startintimeImage.getNobjectsrcw());
            } else if (startingtime >= 30) {
                startintimeImage.setNobjectsrcx(0);
            } else {

                gameControl = true;
                if(mediatime.isPlaying()){mediatime.release();}
                backgroundmusic();
            }
        }
    }
        //KARAGOZ DAMAGE
        public void damageKaragoz () {

            hacivat.decBulletCount();
            animHacivat.getTargetCharacter().decHealth(animHacivat.getObject());
            setKaragozHealth();
            hacivat.setHitcount(hacivat.getHitcount() + 1);
            animHacivat.getTargetCharacter().setDamagecount(animHacivat.getTargetCharacter().getDamagecount() + 1);
            chooseFruitHacivat();
            iceEffect();
    }
        //HACİVAT DAMAGE
        public void damageHacivat () {

            animKaragoz.getTargetCharacter().decHealth(animKaragoz.getObject());
            setHacivatHealth();
            karagoz.decBulletCount();
            karagoz.setHitcount(hacivat.getHitcount() + 1);
            animKaragoz.getTargetCharacter().setDamagecount(animKaragoz.getTargetCharacter().getDamagecount() + 1);
            chooseFruitKaragoz();
  }
        public void hacivatShot () {
            //Ateş Ediyormu
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
                fruitEffect();

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
        public void jumpAnimHacivat(){
            hacivatAnimRow++;
            if(hacivatAnimRow > 3){
                hacivatAnimRow = 0;
                hacivatAnimLine++;
                if(hacivatAnimLine > 3){
                    hacivatAnimLine = 0;
                    hacivatAnimRow = 0;
                    animControlHacivat = false;
                }
            }
            hacivat.setNobjectsrcx(hacivatAnimRow * (hacivat.getNobjectsrcw() + 90));
            hacivat.setNobjectsrcy(hacivatAnimLine * hacivat.getNobjectsrch());

        }
        public void jumpAnimKaragoz(){
            karagozAnimRow++;
            if(karagozAnimRow > 3){
            karagozAnimRow = 0;
            karagozAnimLine++;
            if(karagozAnimLine > 3){
                karagozAnimLine = 0;
                karagozAnimRow = 0;
                animControlKaragoz = false;
            }
        }
        karagoz.setNobjectsrcx(karagozAnimRow * karagoz.getNobjectsrcw() + 20);
        karagoz.setNobjectsrcy(karagozAnimLine * karagoz.getNobjectsrch());
        }
        public void hacivatJump () {
            if(animControlHacivat)jumpAnimHacivat();
            if (!hacivat.isJumpcontrol()) {
                hacivat.setJumpcontrol(true);
            }
            if (hacivat.jump()) {
                hacivat.setJumpcontrol(false);
                hacivat.setNobjectdsty(getHeight() - hacivat.getNobjectdsth());
                hacivat.setNobjectsrcx(0);
                hacivat.setNobjectsrcy(0);
            }
        }

        //KARAGÖZ ZIPLAMA KONTROLU
        public void karagozJump () {
            if(animControlKaragoz)jumpAnimKaragoz();
            if (!karagoz.isJumpcontrol()) {
                karagoz.setJumpcontrol(true);
            }
            if (karagoz.jump()) {
                karagoz.setJumpcontrol(false);
                karagoz.setNobjectdsty(getHeight() - karagoz.getNobjectdsth());
                karagoz.setNobjectsrcx(20);
                karagoz.setNobjectsrcy(0);
            }
        }
        //AI DEFANS MODLARI


        public void aiPlayerModeAttack () {
            if (animHacivat.AIAttackCollision(obje2)) {
                if (animHacivat.AIDefenceCollision(obje2)) {
                    if (!karagoz.isJumpcontrol() && !hacivat.isJumpcontrol() && !splashEffectControl)
                        hacivat.setShoutcountrol(true);
                    else if (hacivat.isJumpcontrol() && karagoz.isJumpcontrol() && !splashEffectControl)
                        hacivat.setShoutcountrol(true);
                    else if (!hacivat.isJumpcontrol()){
                        hacivat.setJumpcontrol(true);
                        animControlHacivat = true;
                    }
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
                    else if (!hacivat.isJumpcontrol()){
                        hacivat.setJumpcontrol(true);
                        animControlHacivat = true;
                    }
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
                    if (!hacivat.isJumpcontrol()){
                        hacivat.setJumpcontrol(true);
                        animControlHacivat = true;
                    }
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




        //Meyve Secimi(Karagöz)
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
        //Meyve Secimi(Hacivat)
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
                if(!gameControl){
                    if(!hacivat.isLivecontrol()){
                        editor.putInt("myWinCount",preferences.getInt("myWinCount",0) + 1);
                    }
                    else if(!karagoz.isLivecontrol()){
                        editor.putInt("myLoseCount",preferences.getInt("myLoseCount",0) + 1);
                    }
                    editor.putInt("myGameCount", preferences.getInt("myGameCount", 0) + 1);
                    editor.putInt("myCoin",gamecoin + scorecoin);
                    editor.commit();
                }System.exit(0);


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
            MenuCanvas mc1 = new MenuCanvas(root);
            root.canvasManager.setCurrentCanvas(mc1);
        }
        if (x >= restart.getNobjectdstx() && x <= restart.getNobjectdstx() + restart.getNobjectdstw() && y >= restart.getNobjectdsty() && y <= restart.getNobjectdsty() + restart.getNobjectdsth()) {
            GameCanvas mc2 = new GameCanvas(root);
            root.canvasManager.setCurrentCanvas(mc2);
        }
        if (x >= jump.getNobjectdstx() && x <= jump.getNobjectdstx() + jump.getNobjectdstw() && y >= jump.getNobjectdsty() && y <= jump.getNobjectdsty() + jump.getNobjectdsth()) {
           if(!karagoz.isJumpcontrol()){
               karagoz.setJumpcontrol(true);
               jumpmusic();
               animControlKaragoz = true;
           }
        }
        if (x >= fire.getNobjectdstx() && x <= fire.getNobjectdstx() + fire.getNobjectdstw() && y >= fire.getNobjectdsty() && y <= fire.getNobjectdsty() + fire.getNobjectdsth()) {
            if(!karagoz.isShoutControl()){
                karagoz.setShoutcountrol(true);
                firemusic();
            }
        }
        if(!hacivat.isLivecontrol()){
            if(x >= win.getNobjectdstx() + (win.getNobjectdstw() / 6 * 1.75) && x <= win.getNobjectdstx() + (win.getNobjectdstw() / 2 - win.getNobjectdstw() / 24 )&& y >= win.getNobjectdsty() + (win.getNobjectdsth() * 5 / 6) && y <= getHeight() ){
                editor.putInt("myCoin",gamecoin + scorecoin);
                editor.commit();
                editor.putInt("myGameCount", preferences.getInt("myGameCount", 0) + 1);
                editor.putInt("myWinCount",preferences.getInt("myWinCount",0) + 1);

                GameStatistic gameStatistic = new GameStatistic();
                gameStatistic.setMyCoin(preferences.getInt("myGameCount", 0) + 1);
                gameStatistic.setMyLoseCount(preferences.getInt("myLoseCount",0) + 1);
                gameStatistic.setMyCoin(gamecoin + scorecoin);
                root.activity.setFirebaseUserStatistic(gameStatistic);
                Log.i("Restart","Basılı");
                GameCanvas mc2 = new GameCanvas(root);
                root.canvasManager.setCurrentCanvas(mc2);
            }
        }else if(!karagoz.isLivecontrol()){
            if(x >= lose.getNobjectdstx() + (lose.getNobjectdstw() / 6 * 1.75) && x <= lose.getNobjectdstx() + lose.getNobjectdstw() / 2 - lose.getNobjectdstw() / 24 && y >= lose.getNobjectdsty() + (lose.getNobjectdsth() * 5 / 6) && y <= getHeight() ){
                editor.putInt("myGameCount", preferences.getInt("myGameCount", 0) + 1);
                editor.putInt("myLoseCount",preferences.getInt("myLoseCount",0) + 1);
                editor.putInt("myCoin",gamecoin + scorecoin);
                editor.commit();

                GameStatistic gameStatistic = new GameStatistic();
                gameStatistic.setMyCoin(preferences.getInt("myGameCount", 0) + 1);
                gameStatistic.setMyLoseCount(preferences.getInt("myLoseCount",0) + 1);
                gameStatistic.setMyCoin(gamecoin + scorecoin);
                root.activity.setFirebaseUserStatistic(gameStatistic);

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
                editor.putInt("myGameCount", preferences.getInt("myGameCount", 0) + 1);
                MenuCanvas mc1 = new MenuCanvas(root);
                root.canvasManager.setCurrentCanvas(mc1);
            }
        }
    }


    public void pause() {  }


    public void resume() { }


        public void reloadTextures() {   }


        public void showNotify() {}
        public void hideNotify() {}


}

