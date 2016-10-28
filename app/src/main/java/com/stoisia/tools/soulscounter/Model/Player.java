package com.stoisia.tools.soulscounter.Model;

/**
 * Created by portableNico on 26/10/2016.
 */
public class Player {
    private int soulsCount;
    private boolean isDead;
    private int fallenSouls;

    public Player() {
        this.soulsCount = 0;
        isDead = false;
        fallenSouls = 0;
    }

    public int getSoulsCount() {
        return soulsCount;
    }

    public boolean isDead() {
        return isDead;
    }

    public int getFallenSouls() {
        return fallenSouls;
    }

    public void addSouls(int value) {
        soulsCount += value;
    }

    public void removeSouls(int value) {
        if (value>soulsCount) soulsCount=0;
        else soulsCount -= value;
    }

    public void kill() {
        isDead = true;
        fallenSouls = soulsCount;
        soulsCount = 0;
    }

    public void resurect() {
        isDead = false;
    }

    public void takeBackFallenSouls() {
        soulsCount += fallenSouls;
        fallenSouls = 0;
    }
}
