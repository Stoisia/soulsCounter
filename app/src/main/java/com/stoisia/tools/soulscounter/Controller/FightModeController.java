package com.stoisia.tools.soulscounter.Controller;

import com.stoisia.tools.soulscounter.Model.GameData;
import com.stoisia.tools.soulscounter.Model.Player;

import java.util.ArrayList;

/**
 * Created by Nico on 30/05/2017.
 */

public class FightModeController {
    private ArrayList<Player> listPlayer;

    public FightModeController() {
        listPlayer = GameData.GetInstance().getPlayerList();
    }
}
