package com.stoisia.tools.soulscounter;

/**
 * Created by portableNico on 26/10/2016.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stoisia.tools.soulscounter.Controller.SoulsController;

public class Souls extends Activity {
    /**
     * Called when the activity is first created.
     */
    private TextView totalSoulsCount;
    private RelativeLayout player1, player2, player3, player4;
    private TextView soulsCountPlayer1, soulsCountPlayer2, soulsCountPlayer3, soulsCountPlayer4;
    private RelativeLayout fallenSoulsLayoutPlayer1, fallenSoulsLayoutPlayer2, fallenSoulsLayoutPlayer3, fallenSoulsLayoutPlayer4;
    private TextView fallenSoulsCountPlayer1, fallenSoulsCountPlayer2, fallenSoulsCountPlayer3, fallenSoulsCountPlayer4;
    private TextView howManySouls;
    private Button add10Souls, add30Souls, add35Souls, add40Souls, add150Souls, add160Souls, add200Souls, selectPlayer, plusSouls, minusSouls;
    private SoulsController controller;
    private int countSelectedPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.souls_layout);
        totalSoulsCount = (TextView) findViewById(R.id.souls_totalSoulsCount);
        player1 = (RelativeLayout) findViewById(R.id.souls_player1);
        player2 = (RelativeLayout) findViewById(R.id.souls_player2);
        player3 = (RelativeLayout) findViewById(R.id.souls_player3);
        player4 = (RelativeLayout) findViewById(R.id.souls_player4);
        soulsCountPlayer1 = (TextView) findViewById(R.id.souls_soulsPlayer1);
        soulsCountPlayer2 = (TextView) findViewById(R.id.souls_soulsPlayer2);
        soulsCountPlayer3 = (TextView) findViewById(R.id.souls_soulsPlayer3);
        soulsCountPlayer4 = (TextView) findViewById(R.id.souls_soulsPlayer4);
        fallenSoulsLayoutPlayer1 = (RelativeLayout) findViewById(R.id.souls_fallenSoulsLayoutPlayer1);
        fallenSoulsLayoutPlayer2 = (RelativeLayout) findViewById(R.id.souls_fallenSoulsLayoutPlayer2);
        fallenSoulsLayoutPlayer3 = (RelativeLayout) findViewById(R.id.souls_fallenSoulsLayoutPlayer3);
        fallenSoulsLayoutPlayer4 = (RelativeLayout) findViewById(R.id.souls_fallenSoulsLayoutPlayer4);
        fallenSoulsCountPlayer1 = (TextView) findViewById(R.id.souls_fallenSoulsCountPlayer1);
        fallenSoulsCountPlayer2 = (TextView) findViewById(R.id.souls_fallenSoulsCountPlayer2);
        fallenSoulsCountPlayer3 = (TextView) findViewById(R.id.souls_fallenSoulsCountPlayer3);
        fallenSoulsCountPlayer4 = (TextView) findViewById(R.id.souls_fallenSoulsCountPlayer4);
        add10Souls = (Button) findViewById(R.id.souls_add10Souls);
        add30Souls = (Button) findViewById(R.id.souls_add30Souls);
        add35Souls = (Button) findViewById(R.id.souls_add35Souls);
        add40Souls = (Button) findViewById(R.id.souls_add40Souls);
        add150Souls = (Button) findViewById(R.id.souls_add150Souls);
        add160Souls = (Button) findViewById(R.id.souls_add160Souls);
        add200Souls = (Button) findViewById(R.id.souls_add200Souls);
        selectPlayer = (Button) findViewById(R.id.souls_selectPlayer);
        howManySouls = (TextView) findViewById(R.id.souls_howManySouls);
        plusSouls = (Button) findViewById((R.id.souls_plusSouls));
        minusSouls = (Button) findViewById((R.id.souls_minusSouls));

        controller = new SoulsController();

        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller.isPlayerDead(0))
                    controller.resurectPlayer(0);
                else
                    controller.killPlayer(0);
                refreshInterface();
            }
        });
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller.isPlayerDead(1))
                    controller.resurectPlayer(1);
                else
                    controller.killPlayer(1);
                refreshInterface();
            }
        });
        player3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller.isPlayerDead(2))
                    controller.resurectPlayer(2);
                else
                    controller.killPlayer(2);
                refreshInterface();
            }
        });
        player4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller.isPlayerDead(3))
                    controller.resurectPlayer(3);
                else
                    controller.killPlayer(3);
                refreshInterface();
            }
        });
        fallenSoulsLayoutPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller.isPlayerDead(0)) return;
                controller.takeBackPlayerFallenSouls(0);
                refreshInterface();
            }
        });
        fallenSoulsLayoutPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller.isPlayerDead(1)) return;
                controller.takeBackPlayerFallenSouls(1);
                refreshInterface();
            }
        });
        fallenSoulsLayoutPlayer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller.isPlayerDead(2)) return;
                controller.takeBackPlayerFallenSouls(2);
                refreshInterface();
            }
        });
        fallenSoulsLayoutPlayer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller.isPlayerDead(3)) return;
                controller.takeBackPlayerFallenSouls(3);
                refreshInterface();
            }
        });
        add10Souls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addSoulsToAllPlayersAlive(10);
                refreshInterface();
            }
        });
        add30Souls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addSoulsToAllPlayersAlive(30);
                refreshInterface();
            }
        });
        add35Souls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addSoulsToAllPlayersAlive(35);
                refreshInterface();
            }
        });
        add40Souls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addSoulsToAllPlayersAlive(40);
                refreshInterface();
            }
        });
        add150Souls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addSoulsToAllPlayersAlive(150);
                refreshInterface();
            }
        });
        add160Souls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addSoulsToAllPlayersAlive(160);
                refreshInterface();
            }
        });
        add200Souls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.addSoulsToAllPlayersAlive(200);
                refreshInterface();
            }
        });

        //incrémentation
        selectPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countSelectedPlayer < 4) countSelectedPlayer++;
                else countSelectedPlayer = 0;
                refreshInterface();
            }
        });

        plusSouls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence numberOfSoulsInString = howManySouls.getText();
                int numberOfSoulsInInt = Integer.parseInt(numberOfSoulsInString.toString());
                CharSequence player1 = "Joueur 1", player2 = "Joueur 2", player3 = "Joueur 3", player4 = "Joueur 4";
                int i = 4;
                if (selectPlayer.getText().equals(player1)) i = 0;
                else if (selectPlayer.getText().equals(player2)) i = 1;
                else if (selectPlayer.getText().equals(player3)) i = 2;
                else if (selectPlayer.getText().equals(player4)) i = 3;
                if (i<4)controller.addSoulsToPlayer(i, numberOfSoulsInInt);
                else controller.addSoulsToAllPlayersAlive(numberOfSoulsInInt);
                refreshInterface();
            }
        });

        minusSouls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence numberOfSoulsInString = howManySouls.getText();
                int numberOfSoulsInInt = Integer.parseInt(numberOfSoulsInString.toString());
                CharSequence player1 = "Joueur 1", player2 = "Joueur 2", player3 = "Joueur 3", player4 = "Joueur 4";
                int i = 4;
                if (selectPlayer.getText().equals(player1)) i = 0;
                else if (selectPlayer.getText().equals(player2)) i = 1;
                else if (selectPlayer.getText().equals(player3)) i = 2;
                else if (selectPlayer.getText().equals(player4)) i = 3;
                if (i<4)controller.removeSoulsToPlayer(i, numberOfSoulsInInt);
                else controller.removeSoulsToAllPlayersAlive(numberOfSoulsInInt);
                refreshInterface();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void refreshInterface() {
        int deathColor = getResources().getColor(R.color.deathColor);
        int aliveColor = getResources().getColor(R.color.aliveColor);

        //affiche le total des ames
        totalSoulsCount.setText(Integer.toString(controller.getTotalSoulsPlayer()));

        //couleur de fond des joueurs
        player1.setBackgroundColor(controller.isPlayerDead(0) ? deathColor : aliveColor);
        player2.setBackgroundColor(controller.isPlayerDead(1) ? deathColor : aliveColor);
        player3.setBackgroundColor(controller.isPlayerDead(2) ? deathColor : aliveColor);
        player4.setBackgroundColor(controller.isPlayerDead(3) ? deathColor : aliveColor);

        //compte d'ames de chaque joueurs
        soulsCountPlayer1.setText(Integer.toString(controller.getPlayerSoulsCount(0)));
        soulsCountPlayer2.setText(Integer.toString(controller.getPlayerSoulsCount(1)));
        soulsCountPlayer3.setText(Integer.toString(controller.getPlayerSoulsCount(2)));
        soulsCountPlayer4.setText(Integer.toString(controller.getPlayerSoulsCount(3)));

        //visibilité d'ames au sol
        fallenSoulsLayoutPlayer1.setVisibility(controller.getPlayerFallenSoulsCount(0) == 0 ? View.INVISIBLE : View.VISIBLE);
        fallenSoulsLayoutPlayer2.setVisibility(controller.getPlayerFallenSoulsCount(1) == 0 ? View.INVISIBLE : View.VISIBLE);
        fallenSoulsLayoutPlayer3.setVisibility(controller.getPlayerFallenSoulsCount(2) == 0 ? View.INVISIBLE : View.VISIBLE);
        fallenSoulsLayoutPlayer4.setVisibility(controller.getPlayerFallenSoulsCount(3) == 0 ? View.INVISIBLE : View.VISIBLE);

        //compte d'ames au sol
        fallenSoulsCountPlayer1.setText(Integer.toString(controller.getPlayerFallenSoulsCount(0)));
        fallenSoulsCountPlayer2.setText(Integer.toString(controller.getPlayerFallenSoulsCount(1)));
        fallenSoulsCountPlayer3.setText(Integer.toString(controller.getPlayerFallenSoulsCount(2)));
        fallenSoulsCountPlayer4.setText(Integer.toString(controller.getPlayerFallenSoulsCount(3)));

        //joueur sélectionné
        if (countSelectedPlayer == 0)
            selectPlayer.setText(getResources().getText(R.string.player1Name));
        else if (countSelectedPlayer == 1)
            selectPlayer.setText(getResources().getText(R.string.player2Name));
        else if (countSelectedPlayer == 2)
            selectPlayer.setText(getResources().getText(R.string.player3Name));
        else if (countSelectedPlayer == 3)
            selectPlayer.setText(getResources().getText(R.string.player4Name));
        else
            selectPlayer.setText(getResources().getText(R.string.allPlayers));
    }
}