package com.ngdroidapp;

public class Skills
{
    public boolean BigAttack(Character obje,Boolean status)
    {   int Big;


        if(status)
        {
        //Width*2
        Big = obje.getNobjectdstw();
        Big *= 2;
        obje.setNobjectdstw(Big);

        //Height*2
        Big=obje.getNobjectdsth();
        Big*=2;
        obje.setNobjectdsth(Big);
        }


        return false;

    }
    public boolean Ice(Character character,Boolean status)
    {   //3 SANİYE HAREKETSİZ
        if(status)
        {
        character.setJumpcontrol(false);
        }
        return status;

    }


    public boolean Poison(Character character,Boolean status)
    {   //5 SANİYE BOYUNCA HER SANİYE
       int health = character.getHealth();

       if(status)
       {
           health -= 5;
           character.setHealth(health);
       }
       return status;
    }

}
