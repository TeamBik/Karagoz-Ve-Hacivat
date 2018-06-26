package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Character {
    private Bitmap character;
    private Rect charactersource, characterdestination;
    //Karakterin genişlik ve yüksekliği
    private int charactersrcw, charactersrch, characterdstw, characterdsth;
    //Karakterin konumu x/y
    private int charactersrcx, charactersrcy, characterdstx, characterdsty;
    //Karakterin zıplama durumunu kontrol eder
    private boolean jumpcontrol;
    //Karakterin yaşayıp yaşamadığını kontrol etmek için oluşturuldu
    private boolean livecontrol;
    //Karakterin eğilme durumunu kontrol eder
    private boolean downcontrol;
    private int startedlocation;
    private int artıs;

    //Yapıcı Fonksiyon
    public Character() {
        jumpcontrol = false;
        livecontrol = true;
        downcontrol = false;
        startedlocation=0;
        artıs=0;
    }
    //Karakterin y düzlemindeki konumunu set eder.
    public void setCharacterdsty(int characterdsthy){
        this.characterdsty = characterdsthy;
    }
    //Karakterin y düzlemindeki konumunu get eder.
    public int getCharacterdsty(){
        return characterdsty;
    }
    //Karakterin x düzlemindeki konumunu set eder.
    public void setCharacterdstx(int characterdsthx){
        this.characterdstx = characterdsthx;
    }
    //Karakterin x düzlemindeki konumunu get eder.
    public int getCharacterdstx(){
        return characterdstx;
    }
    //Karakterin Source bilgilerini set eder.
    public void setCharactersource(int charactersrcx, int charactersrcy, int charactersrcw, int charactersrch){
        this.charactersrcx = charactersrcx;
        this.charactersrcy = charactersrcy;
        this.charactersrcw = charactersrcw;
        this.charactersrch = charactersrch;

    }
    //Karakterin Destination bilgilerini set eder.
    public void setCharacterdestination(int characterdstx, int characterdsty, int characterdstw, int characterdsth){
        this.characterdstx = characterdstx;
        this.characterdsty = characterdsty;
        this.characterdstw = characterdstw;
        this.characterdsth = characterdsth;
        this.startedlocation=characterdsty;
    }
    //Karakterin Destination bilgilerini get eder.
    public Rect getCharacterdestination() {
        characterdestination.set(characterdstx, characterdsty, characterdstw + characterdstx, characterdsth + characterdsty);
        return characterdestination;
    }
    //Karakterin Source bilgilerini get eder.
    public Rect getCharactersource(){
        charactersource.set(charactersrcx, charactersrcy, charactersrcx + charactersrcw, charactersrcy + charactersrch);
        return charactersource;
    }
    public void  jump(){
        artıs +=10;
        setCharacterdsty(characterdsty + artıs);
        setCharacterdsty(characterdsty - artıs);
    }
    public void  power(){}
}
