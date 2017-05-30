package com.stoisia.tools.soulscounter.Model;

/**
 * Created by portableNico on 26/10/2016.
 */
public class Player {
    private String mName;
    private int soulsCount = 0;
    private boolean isDead = false;
    private int fallenSouls = 0;
    private int mHpMax = 100;
    private int mHpValue = 100;
    private int mMpMax = 50;
    private int mMpValue = 50;
    private int mStaminaMax = 120;
    private int mStaminaValue = 120;
    private int mDeathLevel = 0;

    public Player(String name) {
        mName = name;
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
        if (value > soulsCount) soulsCount = 0;
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

    public int getHpMax() {
        return mHpMax;
    }

    public int getHpMaxAfterDeathEffect() {
        double coef = 1.0;
        switch (mDeathLevel){
            case 1: coef = 0.9; break;
            case 2: coef = 0.75; break;
            case 3: coef = 0.5; break;
        }
        return (int)(mHpMax * coef);
    }

    public void setHpMax(int hpMax) {
        this.mHpMax = hpMax;

        if(mHpValue > getHpMaxAfterDeathEffect()){
            mHpValue = getHpMaxAfterDeathEffect();
        }
    }

    public int getHpValue() {
        return mHpValue;
    }

    public void setHpValue(int hpValue) {
        this.mHpValue = hpValue;
    }

    public int getMpMax() {
        return mMpMax;
    }

    public void setMpMax(int mpMax) {
        this.mMpMax = mpMax;
        if(mMpValue > mMpMax) mMpValue = mMpMax;
    }

    public int getMpValue() {
        return mMpValue;
    }

    public void setMpValue(int mpValue) {
        this.mMpValue = mpValue;
    }

    public int getStaminaMax() {
        return mStaminaMax;
    }

    public void setStaminaMax(int staminaMax) {
        this.mStaminaMax = staminaMax;
        if(mStaminaValue > mStaminaMax) mStaminaValue = mStaminaMax;
    }

    public int getStaminaValue() {
        return mStaminaValue;
    }

    public void setStaminaValue(int staminaValue) {
        this.mStaminaValue = staminaValue;
    }

    public int getDeathLevel() {
        return mDeathLevel;
    }

    public void setDeathLevel(int deathLevel) {
        this.mDeathLevel = deathLevel;

        if(mHpValue > getHpMaxAfterDeathEffect()){
            mHpValue = getHpMaxAfterDeathEffect();
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
