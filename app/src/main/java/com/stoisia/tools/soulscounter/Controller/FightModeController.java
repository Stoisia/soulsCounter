package com.stoisia.tools.soulscounter.Controller;

import com.stoisia.tools.soulscounter.Model.GameData;
import com.stoisia.tools.soulscounter.Model.Player;

import java.util.ArrayList;

/**
 * Created by Nico on 30/05/2017.
 */

public class FightModeController {
    private ArrayList<Player> listPlayer;
    private boolean mIsInFight = false;
    private int mRoundCount = 0;

    public FightModeController() {
        listPlayer = GameData.GetInstance().getPlayerList();
    }

    public ArrayList<Player> getListPlayer() {
        return listPlayer;
    }

    public boolean isInFight() {
        return mIsInFight;
    }

    public void setIsInFight(boolean isInFight) {
        if(isInFight){
            mRoundCount = 0;
        }
        else{
            refillAllPlayerStamina();
        }
        this.mIsInFight = isInFight;
    }

    public int getRoundCount() {
        return mRoundCount;
    }

    public void nextRound(){
        mRoundCount++;
        refillAllPlayerStamina();
    }

    private void refillAllPlayerStamina(){
        for (Player player :
                listPlayer) {
            player.setStaminaValue(player.getStaminaMax());
        }
    }
}
