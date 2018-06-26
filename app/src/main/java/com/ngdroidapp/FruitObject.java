package com.ngdroidapp;

public class FruitObject extends Nobject {
    private int weight,velocity;
    private boolean livecontrol;

    public FruitObject(int weight, int velocity) {
        this.velocity = velocity;
        this.weight = weight;
        livecontrol = false;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public boolean isLivecontrol() {
        return livecontrol;
    }

    public void setLivecontrol(boolean livecontrol) {
        this.livecontrol = livecontrol;
    }
}
