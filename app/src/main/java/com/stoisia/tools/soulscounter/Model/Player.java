package com.stoisia.tools.soulscounter.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
    private ArrayList<Integer> mPresetHpValues = new ArrayList<>();
    private int mMpMax = 50;
    private int mMpValue = 50;
    private ArrayList<Integer> mPresetMpValues = new ArrayList<>();
    private int mStaminaMax = 120;
    private int mStaminaValue = 120;
    private ArrayList<Integer> mPresetStaminaValues = new ArrayList<>();
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

    public void setHpMax(int hpMax) {
        this.mHpMax = hpMax;

        if (mHpMax < 0) mHpMax = 0;
        if (mHpValue > getHpMaxAfterDeathEffect()) {
            mHpValue = getHpMaxAfterDeathEffect();
        }
    }

    public int getHpValue() {
        return mHpValue;
    }

    public void setHpValue(int hpValue) {
        if (hpValue > getHpMaxAfterDeathEffect()) {
            this.mHpValue = getHpMaxAfterDeathEffect();
            return;
        }
        if (hpValue < 0) {
            this.mHpValue = 0;
            return;
        }
        this.mHpValue = hpValue;
    }

    public int getHpMaxAfterDeathEffect() {
        double coef = 1.0;
        switch (mDeathLevel) {
            case 1:
                coef = 0.9;
                break;
            case 2:
                coef = 0.75;
                break;
            case 3:
                coef = 0.5;
                break;
        }
        return (int) (mHpMax * coef);
    }

    public ArrayList<Integer> getPresetHpValues() {
        return mPresetHpValues;
    }

    public void removePresetHpValue(int value) {
        if (mPresetHpValues.contains(value)) mPresetHpValues.remove(value);
    }

    public void addPresetHpValue(int value) {
        if (!mPresetHpValues.contains(value)) mPresetHpValues.add(value);
    }

    public int getMpMax() {
        return mMpMax;
    }

    public void setMpMax(int mpMax) {
        this.mMpMax = mpMax;
        if (mMpMax < 0) mMpValue = 0;
        if (mMpValue > mMpMax) mMpValue = mMpMax;
    }

    public int getMpValue() {
        return mMpValue;
    }

    public void setMpValue(int mpValue) {
        if (mpValue > mMpMax) {
            this.mMpValue = mMpMax;
            return;
        }
        if (mpValue < 0) {
            this.mMpValue = 0;
            return;
        }
        this.mMpValue = mpValue;
    }

    public ArrayList<Integer> getPresetMpValues() {
        return mPresetMpValues;
    }

    public void removePresetMpValue(int value) {
        if (mPresetMpValues.contains(value)) mPresetMpValues.remove(value);
    }

    public void addPresetMpValue(int value) {
        if (!mPresetMpValues.contains(value)) mPresetMpValues.add(value);
    }

    public int getStaminaMax() {
        return mStaminaMax;
    }

    public void setStaminaMax(int staminaMax) {
        this.mStaminaMax = staminaMax;
        if (mStaminaMax < 0) mStaminaMax = 0;
        if (mStaminaValue > mStaminaMax) mStaminaValue = mStaminaMax;
    }

    public int getStaminaValue() {
        return mStaminaValue;
    }

    public void setStaminaValue(int staminaValue) {
        if (staminaValue > mStaminaMax) {
            this.mStaminaValue = mStaminaMax;
            return;
        }
        if (staminaValue < 0) {
            this.mStaminaValue = 0;
            return;
        }
        this.mStaminaValue = staminaValue;
    }

    public ArrayList<Integer> getPresetStaminaValues() {
        return mPresetStaminaValues;
    }

    public void removePresetStaminaValue(int value) {
        if (mPresetStaminaValues.contains(value)) mPresetStaminaValues.remove(value);
    }

    public void addPresetStaminaValue(int value) {
        if (!mPresetStaminaValues.contains(value)) mPresetStaminaValues.add(value);
    }

    public int getDeathLevel() {
        return mDeathLevel;
    }

    public void setDeathLevel(int deathLevel) {
        if (deathLevel > 3 || deathLevel < 0) return;
        this.mDeathLevel = deathLevel;

        if (mHpValue > getHpMaxAfterDeathEffect()) {
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
