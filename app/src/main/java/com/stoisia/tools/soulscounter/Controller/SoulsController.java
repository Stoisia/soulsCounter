package com.stoisia.tools.soulscounter.Controller;

import com.stoisia.tools.soulscounter.Model.GameData;
import com.stoisia.tools.soulscounter.Model.Player;

import java.util.ArrayList;

/**
 * Created by portableNico on 26/10/2016.
 */
public class SoulsController {
    private ArrayList<Player> listPlayer;
    private ArrayList<Integer> mPresetSoulsCount = new ArrayList<>();

    public SoulsController() {
        listPlayer = GameData.GetInstance().getPlayerList();
        mPresetSoulsCount.add(10);
        mPresetSoulsCount.add(20);
        mPresetSoulsCount.add(35);
        mPresetSoulsCount.add(40);
        mPresetSoulsCount.add(50);
        mPresetSoulsCount.add(80);
        mPresetSoulsCount.add(120);
        mPresetSoulsCount.add(150);
        mPresetSoulsCount.add(200);
        mPresetSoulsCount.add(400);
        mPresetSoulsCount.add(600);
    }

    public int getTotalSoulsPlayer() {
        int total = 0;
        for (Player onePlayer : listPlayer) {
            total += onePlayer.getSoulsCount();
        }
        return total;
    }

    public String getPlayerName(int playerNb) {
        if (playerNb < 0 || playerNb > listPlayer.size()) return "";
        return listPlayer.get(playerNb).getName();
    }

    public int getPlayerSoulsCount(int playerNb) {
        if (playerNb < 0 || playerNb > listPlayer.size()) return -1;
        return listPlayer.get(playerNb).getSoulsCount();
    }

    public boolean isPlayerDead(int playerNb) {
        if (playerNb < 0 || playerNb > listPlayer.size()) return false;
        return listPlayer.get(playerNb).isDead();
    }

    public int getPlayerFallenSoulsCount(int playerNb) {
        if (playerNb < 0 || playerNb > listPlayer.size()) return -1;
        return listPlayer.get(playerNb).getFallenSouls();
    }

    public boolean addSoulsToPlayer(int playerNb, int soulsCount) {
        if (playerNb < 0 || playerNb > listPlayer.size()) return false;
        listPlayer.get(playerNb).addSouls(soulsCount);
        return true;
    }

    public boolean removeSoulsToPlayer(int playerNb, int soulsCount) {
        if (playerNb < 0 || playerNb > listPlayer.size()) return false;
        listPlayer.get(playerNb).removeSouls(soulsCount);
        return true;
    }

    public void addSoulsToAllPlayersAlive(int soulsCount) {
        for (Player onePlayer : listPlayer) {
            if (onePlayer.isDead()) continue;
            onePlayer.addSouls(soulsCount);
        }
    }

    public void removeSoulsToAllPlayersAlive(int soulsCount) {
        for (Player onePlayer : listPlayer) {
            if (onePlayer.isDead()) continue;
            onePlayer.removeSouls(soulsCount);
        }
    }

    public boolean killPlayer(int playerNb) {
        if (playerNb < 0 || playerNb > listPlayer.size()) return false;
        listPlayer.get(playerNb).kill();
        return true;
    }

    public boolean resurectPlayer(int playerNb) {
        if (playerNb < 0 || playerNb > listPlayer.size()) return false;
        listPlayer.get(playerNb).resurect();
        return true;
    }

    public boolean takeBackPlayerFallenSouls(int playerNb) {
        if (playerNb < 0 || playerNb > listPlayer.size()) return false;
        if (listPlayer.get(playerNb).isDead()) return false;
        listPlayer.get(playerNb).takeBackFallenSouls();
        return true;
    }

    public ArrayList<Integer> getPresetSoulsCount() {
        return mPresetSoulsCount;
    }
}
