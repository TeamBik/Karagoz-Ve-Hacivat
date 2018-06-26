package com.ngdroidapp;

public class Animations
{   private Character character;
    //Nesnenin X ve Y Konumu
    private int objectX,objectY;
    //Hedefin X ve Y Konumu
    private int targetX,targetY;
    //Karakterin X ve Y Konumu
    private int characterX,characterY;

    public Animations(Character character) {
        //Character Sınıfından karakterin X ve Y'sinin çekilmesi
    this.character = character;
    Character c=new Character();
    characterX=c.getNobjectdstx();
    characterY=c.getNobjectdsty();

}

public void shout()
{

}

}
