package com.ngdroidapp;

import android.content.res.Resources;

import istanbul.gamelab.ngdroid.util.Utils;

public class Animations {

    private Character character,targetCharacter;
    private FruitObject object;
    //Nesnenin X ve Y Konumu
    private int objectX, objectY;
    //Hedefin X ve Y Konumu
    private int targetX, targetY;
    //Karakterin X ve Y Konumu
    private int characterX, characterY, characterWidth, characterHeight;
    private int derece;

    public Animations() {

    }

    public Animations(Character character, Character target, FruitObject object) {

        //Character Sınıfından karakterin X ve Y'sinin çekilmesi

        targetCharacter = target;
        character = new Character();
        this.object = object;
        this.character = character;
        characterX = character.getNobjectdstx();
        characterY = character.getNobjectdsty();
        characterWidth = character.getNobjectdstw();
        characterHeight = character.getNobjectdsth();
        objectX = object.getNobjectdstx();
        objectY = object.getNobjectdsty();
        derece = 0;

    }

    public boolean Collision(Character character1, FruitObject nobject) {
    //Objelerin Karşılaştırılması
    if(Utils.checkCollision(character1.getNobjectdestination(),nobject.getNobjectdestination()))
    {


        return true;
        }else{
            return false;
        }
    }
    public boolean FruitCollision(FruitObject fruit1, FruitObject fruit2) {
        //Objelerin Karşılaştırılması
        if(Utils.checkCollision(fruit1.getNobjectdestination(),fruit2.getNobjectdestination()))
        {
            return true;
        }else{
            return false;
        }
    }

    public Character getTargetCharacter() {
        return targetCharacter;
    }

    public FruitObject getObject() {
        return object;
    }

    public void setTargetCharacter(Character targetCharacter) {
        this.targetCharacter = targetCharacter;
    }

    //YAPAY ZEKA DEFANS ÇARPIŞMA
    public boolean AIDefenceCollision(Nobject enemyobject) {
        if ((character.getNobjectdstx() + (targetCharacter.getNobjectdstx() - character.getNobjectdstx()) - 150) >= enemyobject.getNobjectdstx() && character.getNobjectdsty() < enemyobject.getNobjectdsty() - enemyobject.getNobjectdsth()) {
            return true;
        }else {
            return false;
        }
    }
    //YAPAY ZEKA ATAK ÇARPIŞMA
    public boolean AIAttackCollision(Nobject enemyobject) {
        if((character.getNobjectdstx() + (targetCharacter.getNobjectdstx() - character.getNobjectdstx()) - 100) >= enemyobject.getNobjectdstx()) {
            return true;
        }else {
            return false;
        }
    }
    //HACİVAT ATEŞ ANİMASYON
    public boolean ShoutAnımationHacivat() {
        if (!Collision(targetCharacter, object)) {
            objectX = object.getNobjectdstx();
            objectX += object.getVelocity();
            object.setNobjectdstx(objectX);
            return true;}
           else{return false;}
        }
    //KARAGÖZ ATEŞ ANİMASYON
    public boolean ShoutAnımationKaragoz() {
        if (!Collision(targetCharacter, object)) {
            objectX = object.getNobjectdstx();
            objectX -= object.getVelocity();
            object.setNobjectdstx(objectX);
            return true;}
        else {return false;}
    }
}
/*
        //EĞRİ
        public void SinCurve()
        {
                    derece += 15;

                    objectX -= 15;
                   if(derece<=90)
                   {
                       objectY -= Math.sin(Math.toRadians(derece)/180)*1000;}
                   else if(derece>90 && derece<=180)
                   {
                       objectY += Math.sin(Math.toRadians(derece)/180)*1000;
                   }

                    object.setNobjectdstx(objectX);
                    object.setNobjectdsty(objectY);






}
*/

