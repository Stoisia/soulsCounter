package com.stoisia.tools.soulscounter.Model;

import java.util.ArrayList;

/**
 * Created by Nico on 30/05/2017.
 */

public class GameData {
    private static final GameData mInstance = new GameData();

    private GameData() {
        mPlayerList.add(new Player("Player 1"));
        mPlayerList.add(new Player("Player 2"));
        mPlayerList.add(new Player("Player 3"));
        mPlayerList.add(new Player("Player 4"));
    }

    public static GameData GetInstance() {
        return mInstance;
    }

    private ArrayList<Player> mPlayerList = new ArrayList<>();
    private boolean mIsInAFight = false;
    private int mRound = 0;

    public ArrayList<Player> getPlayerList() {
        return mPlayerList;
    }

    public int getRound() {
        return mRound;
    }

    public void setRound(int round) {
        this.mRound = round;
    }

    public boolean isInAFight() {
        return mIsInAFight;
    }

    public void setIsInAFight(boolean isInAFight) {
        this.mIsInAFight = isInAFight;
    }
}
