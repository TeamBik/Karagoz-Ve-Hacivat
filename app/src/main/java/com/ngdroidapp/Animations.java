package com.ngdroidapp;

import istanbul.gamelab.ngdroid.util.Utils;

public class Animations {
    private Character character,targetCharacter;
    private Nobject object;
    //Nesnenin X ve Y Konumu
    private int objectX, objectY;
    //Hedefin X ve Y Konumu
    private int targetX, targetY;
    //Karakterin X ve Y Konumu
    private int characterX, characterY, characterWidth, characterHeight;

    public Animations(Character character,Character target) {
        //Character Sınıfından karakterin X ve Y'sinin çekilmesi
        targetCharacter = target;
        character = new Character();
        object = new Nobject();
        this.character = character;
        characterX = character.getNobjectdstx();
        characterY = character.getNobjectdstx();
        characterWidth = character.getNobjectdstw();
        characterHeight = character.getNobjectdsth();
        objectX = object.getNobjectdstx();
        objectY = object.getNobjectdsty();
    }
    public boolean Collision(Character character1, Nobject nobject) {
    //Objelerin Karşılaştırılması
    if(Utils.checkCollision(character1.getNobjectdestination(),nobject.getNobjectdestination()))
    { return true;
        }else{
            return false;
        }
    }
    public boolean AIDefenceCollision() {
        if ((character.getNobjectdstx() + 100) == objectX) {
            return true;
        } else {
            return false;
        }
    }
    public boolean AIAttackCollision() {
        if((character.getNobjectdstx() + 300) == objectX) {
            return true;
        }else {
            return false;
        }
    }

    public boolean ShoutAnımation() {
        if (!Collision(targetCharacter,object)){
            objectX += 15;
            object.setNobjectdstx(objectX);
            return true;
            }
        else
            {
            return false;
            }


        }


    }