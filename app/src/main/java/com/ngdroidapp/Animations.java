package com.ngdroidapp;

import android.content.res.Resources;

import istanbul.gamelab.ngdroid.util.Utils;

public class Animations {
    private double localX, StartCharacterX;
    private Character character, targetCharacter;
    private Nobject object;
    //Nesnenin X ve Y Konumu
    private int objectX, objectY;
    //Hedefin X ve Y Konumu
    private int targetX, targetY;
    //Karakterin X ve Y Konumu
    private int characterX, characterY, characterWidth, characterHeight;
    private int derece;

    public Animations(Character character, Character target, Nobject object) {
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

    public boolean Collision(Character character1, Nobject nobject) {
        //Objelerin Karşılaştırılması
        if (Utils.checkCollision(character1.getNobjectdestination(), nobject.getNobjectdestination())) {
            return true;
        } else {
            return false;
        }
    }

    //YAPAY ZEKA DEFANS ÇARPIŞMA
    public boolean AIDefenceCollision() {
        if ((character.getNobjectdstx() + 100) == objectX) {
            return true;
        } else {
            return false;
        }
    }

    //YAPAY ZEKA ATAK ÇARPIŞMA
    public boolean AIAttackCollision() {
        if ((character.getNobjectdstx() + 300) == objectX) {
            return true;
        } else {
            return false;
        }
    }

    //HACİVAT ATEŞ ANİMASYON
    public boolean ShoutAnımationHacivat() {
        if (!Collision(targetCharacter, object)) {
            objectX = object.getNobjectdstx();
            objectX += 25;
            object.setNobjectdstx(objectX);
            return true;
        } else {
            return false;
        }
    }

    //KARAGÖZ ATEŞ ANİMASYON
    public boolean ShoutAnımationKaragoz() {
        if (!Collision(targetCharacter, object)) {
            objectX = object.getNobjectdstx();
          //  SinCurve();

            return true;
        } else {
            return false;}
        }

        //EKRAN YÜKSEKLİĞİ ALMA
        public static int getScreenHeight()
        {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
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
}





