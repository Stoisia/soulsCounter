package com.stoisia.tools.soulscounter;

/**
 * Created by portableNico on 26/10/2016.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stoisia.tools.soulscounter.CustomComponent.PlayerView;

import com.stoisia.tools.soulscounter.Controller.SoulsController;
import com.stoisia.tools.soulscounter.CustomComponent.PresetUpdateDialog;

public class Souls extends Activity {
    /**
     * Called when the activity is first created.
     */
    private TextView totalSoulsCount;
    private PlayerView player1, player2, player3, player4;
    private LinearLayout fallenSoulsLayoutPlayer1, fallenSoulsLayoutPlayer2, fallenSoulsLayoutPlayer3, fallenSoulsLayoutPlayer4;
    private TextView fallenSoulsCountPlayer1, fallenSoulsCountPlayer2, fallenSoulsCountPlayer3, fallenSoulsCountPlayer4;
    private TextView howManySouls;
    private LinearLayout mPresetSoulsValuesLinearLayout;
    private Button mUpdatePresetSoulsValuesButton, selectPlayer, plusSouls, minusSouls;
    private SoulsController controller;
    private int countSelectedPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.souls_layout);
        totalSoulsCount = (TextView) findViewById(R.id.souls_totalSoulsCount);
        player1 = (PlayerView) findViewById(R.id.souls_player1);
        player2 = (PlayerView) findViewById(R.id.souls_player2);
        player3 = (PlayerView) findViewById(R.id.souls_player3);
        player4 = (PlayerView) findViewById(R.id.souls_player4);
        fallenSoulsLayoutPlayer1 = (LinearLayout) findViewById(R.id.souls_fallenSoulsLayoutPlayer1);
        fallenSoulsLayoutPlayer2 = (LinearLayout) findViewById(R.id.souls_fallenSoulsLayoutPlayer2);
        fallenSoulsLayoutPlayer3 = (LinearLayout) findViewById(R.id.souls_fallenSoulsLayoutPlayer3);
        fallenSoulsLayoutPlayer4 = (LinearLayout) findViewById(R.id.souls_fallenSoulsLayoutPlayer4);
        fallenSoulsCountPlayer1 = (TextView) findViewById(R.id.souls_fallenSoulsCountPlayer1);
        fallenSoulsCountPlayer2 = (TextView) findViewById(R.id.souls_fallenSoulsCountPlayer2);
        fallenSoulsCountPlayer3 = (TextView) findViewById(R.id.souls_fallenSoulsCountPlayer3);
        fallenSoulsCountPlayer4 = (TextView) findViewById(R.id.souls_fallenSoulsCountPlayer4);
        mPresetSoulsValuesLinearLayout = (LinearLayout) findViewById(R.id.mPresetSoulsValuesLinearLayout);
        mUpdatePresetSoulsValuesButton = (Button) findViewById(R.id.mUpdatePresetSoulsValuesButton);
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
        mUpdatePresetSoulsValuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String presetTitle = getResources().getString(R.string.soulsCounter);
                new PresetUpdateDialog(Souls.this, presetTitle, controller.getPresetSoulsCount(), new PresetUpdateDialog.IDialogEvent() {
                    @Override
                    public void onValidate() {
                        refreshInterface();
                    }

                    @Override
                    public void onAbort() {

                    }
                }).show();
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
                if (i < 4) controller.addSoulsToPlayer(i, numberOfSoulsInInt);
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
                if (i < 4) controller.removeSoulsToPlayer(i, numberOfSoulsInInt);
                else controller.removeSoulsToAllPlayersAlive(numberOfSoulsInInt);
                refreshInterface();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshInterface();
    }

    private void refreshInterface() {
        int deathColor = getResources().getColor(R.color.deathColor);
        int aliveColor = getResources().getColor(R.color.aliveColor);

        //affiche le total des ames
        totalSoulsCount.setText(Integer.toString(controller.getTotalSoulsPlayer()));

        //Nom des joueurs
        player1.setPlayerName(controller.getPlayerName(0));
        player2.setPlayerName(controller.getPlayerName(1));
        player3.setPlayerName(controller.getPlayerName(2));
        player4.setPlayerName(controller.getPlayerName(3));

        //couleur de fond des joueurs
        player1.setPlayerIsDead(controller.isPlayerDead(0));
        player2.setPlayerIsDead(controller.isPlayerDead(1));
        player3.setPlayerIsDead(controller.isPlayerDead(2));
        player4.setPlayerIsDead(controller.isPlayerDead(3));

        //compte d'ames de chaque joueurs
        player1.setPlayerSoulsCount(controller.getPlayerSoulsCount(0));
        player2.setPlayerSoulsCount(controller.getPlayerSoulsCount(1));
        player3.setPlayerSoulsCount(controller.getPlayerSoulsCount(2));
        player4.setPlayerSoulsCount(controller.getPlayerSoulsCount(3));

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

        mPresetSoulsValuesLinearLayout.removeAllViews();
        for (int presetSoulsCount : controller.getPresetSoulsCount()) {

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(70, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5, 15, 5, 15);

            TextView presetValueTextView = new TextView(this);
            presetValueTextView.setText(presetSoulsCount + "");
            presetValueTextView.setTag(presetSoulsCount);
            int colorPrimary = getResources().getColor(R.color.colorPrimary);
            presetValueTextView.setBackgroundColor(colorPrimary);
            presetValueTextView.setPadding(10, 10, 10, 10);
            presetValueTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            presetValueTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int value = (int)view.getTag();
                    controller.addSoulsToAllPlayersAlive(value);
                    refreshInterface();
                }
            });
            mPresetSoulsValuesLinearLayout.addView(presetValueTextView, layoutParams);
        }
    }
}