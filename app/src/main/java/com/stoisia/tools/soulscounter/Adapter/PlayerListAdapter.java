package com.stoisia.tools.soulscounter.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import com.stoisia.tools.soulscounter.CustomComponent.PlayerNameDialog;
import com.stoisia.tools.soulscounter.CustomComponent.ValueUpdateDialog;
import com.stoisia.tools.soulscounter.Model.Player;
import com.stoisia.tools.soulscounter.R;

import java.util.ArrayList;

/**
 * Created by Nico on 30/05/2017.
 */

public class PlayerListAdapter extends BaseAdapter {

    private ArrayList<Player> mPlayers;
    private Context mContext;
    private static LayoutInflater inflater = null;

    public PlayerListAdapter(Context context, ArrayList<Player> players) {
        mPlayers = players;
        mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mPlayers.size();
    }

    @Override
    public Object getItem(int i) {
        if (i < 0 || i >= getCount()) return null;
        return mPlayers.get(i);
    }

    @Override
    public long getItemId(int i) {
        if (i < 0 || i >= getCount()) return 0;
        return i;
    }

    public class PlayerListItem {
        public TextView mPlayerNameTextView;
        public SeekBar mDeathLevelSeekBar;
        public TextView mPlayerHpValueTextView;
        public TextView mPlayerHpMaxAfterDeathEffectTextView;
        public TextView mPlayerHpMaxTextView;
        public SeekBar mHpSeekBar;
        public TextView mPlayerMpValueTextView;
        public TextView mPlayerMpMaxTextView;
        public SeekBar mMpSeekBar;
        public TextView mPlayerStaminaValueTextView;
        public TextView mPlayerStaminaMaxTextView;
        public SeekBar mStaminaSeekBar;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // TODO Auto-generated method stub
        View vi = view;
        if (vi == null)
            vi = inflater.inflate(R.layout.fight_mode_player_row, null);

        Player player = mPlayers.get(i);

        PlayerListItem playerListItem = null;
        if (vi.getTag() == null) {
            playerListItem = new PlayerListItem();
            playerListItem.mPlayerNameTextView = (TextView) vi.findViewById(R.id.mPlayerNameTextView);

            playerListItem.mDeathLevelSeekBar = (SeekBar) vi.findViewById(R.id.mDeathLevelSeekBar);

            playerListItem.mPlayerHpValueTextView = (TextView) vi.findViewById(R.id.mPlayerHpValueTextView);
            playerListItem.mPlayerHpMaxAfterDeathEffectTextView = (TextView) vi.findViewById(R.id.mPlayerHpMaxAfterDeathEffectTextView);
            playerListItem.mPlayerHpMaxTextView = (TextView) vi.findViewById(R.id.mPlayerHpMaxTextView);
            playerListItem.mHpSeekBar = (SeekBar) vi.findViewById(R.id.mHpSeekBar);

            playerListItem.mPlayerMpValueTextView = (TextView) vi.findViewById(R.id.mPlayerMpValueTextView);
            playerListItem.mPlayerMpMaxTextView = (TextView) vi.findViewById(R.id.mPlayerMpMaxTextView);
            playerListItem.mMpSeekBar = (SeekBar) vi.findViewById(R.id.mMpSeekBar);

            playerListItem.mPlayerStaminaValueTextView = (TextView) vi.findViewById(R.id.mPlayerStaminaValueTextView);
            playerListItem.mPlayerStaminaMaxTextView = (TextView) vi.findViewById(R.id.mPlayerStaminaMaxTextView);
            playerListItem.mStaminaSeekBar = (SeekBar) vi.findViewById(R.id.mStaminaSeekBar);

            playerListItem.mPlayerNameTextView.setTag(player);
            playerListItem.mPlayerNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View c) {
                    final Player player = (Player) c.getTag();
                    new PlayerNameDialog(mContext, player.getName(), new PlayerNameDialog.IDialogEvent() {
                        @Override
                        public void onValidate(String playerName) {
                            player.setName(playerName);
                            PlayerListAdapter.this.notifyDataSetChanged();
                        }

                        @Override
                        public void onAbort() {

                        }
                    }).show();
                }
            });

            playerListItem.mDeathLevelSeekBar.setTag(player);
            playerListItem.mDeathLevelSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Player player = (Player) seekBar.getTag();
                    player.setDeathLevel(seekBar.getProgress());
                    PlayerListAdapter.this.notifyDataSetChanged();
                }
            });

            playerListItem.mPlayerHpMaxAfterDeathEffectTextView.setTag(player);
            playerListItem.mPlayerHpMaxAfterDeathEffectTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Player player = (Player) view.getTag();
                    String title = mContext.getResources().getText(R.string.hpMaxTitle).toString();
                    new ValueUpdateDialog(mContext, title, player.getHpMax(), new ValueUpdateDialog.IDialogEvent() {
                        @Override
                        public void onValidate(int value) {
                            player.setHpMax(value);
                            PlayerListAdapter.this.notifyDataSetChanged();
                        }

                        @Override
                        public void onAbort() {

                        }
                    }).show();
                }
            });

            playerListItem.mHpSeekBar.setTag(player);
            playerListItem.mHpSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Player player = (Player) seekBar.getTag();
                    player.setHpValue(seekBar.getProgress());
                    PlayerListAdapter.this.notifyDataSetChanged();
                }
            });

            playerListItem.mPlayerMpMaxTextView.setTag(player);
            playerListItem.mPlayerMpMaxTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Player player = (Player) view.getTag();
                    String title = mContext.getResources().getText(R.string.mpMaxTitle).toString();
                    new ValueUpdateDialog(mContext, title, player.getMpMax(), new ValueUpdateDialog.IDialogEvent() {
                        @Override
                        public void onValidate(int value) {
                            player.setMpMax(value);
                            PlayerListAdapter.this.notifyDataSetChanged();
                        }

                        @Override
                        public void onAbort() {

                        }
                    }).show();
                }
            });

            playerListItem.mMpSeekBar.setTag(player);
            playerListItem.mMpSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Player player = (Player) seekBar.getTag();
                    player.setMpValue(seekBar.getProgress());
                    PlayerListAdapter.this.notifyDataSetChanged();
                }
            });

            playerListItem.mPlayerStaminaMaxTextView.setTag(player);
            playerListItem.mPlayerStaminaMaxTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Player player = (Player) view.getTag();
                    String title = mContext.getResources().getText(R.string.staminaMaxTitle).toString();
                    new ValueUpdateDialog(mContext, title, player.getStaminaMax(), new ValueUpdateDialog.IDialogEvent() {
                        @Override
                        public void onValidate(int value) {
                            player.setStaminaMax(value);
                            PlayerListAdapter.this.notifyDataSetChanged();
                        }

                        @Override
                        public void onAbort() {

                        }
                    }).show();
                }
            });

            playerListItem.mStaminaSeekBar.setTag(player);
            playerListItem.mStaminaSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Player player = (Player) seekBar.getTag();
                    player.setStaminaValue(seekBar.getProgress());
                    PlayerListAdapter.this.notifyDataSetChanged();
                }
            });

            vi.setTag(playerListItem);
        } else {
            playerListItem = (PlayerListItem) vi.getTag();
        }

        //Player name
        playerListItem.mPlayerNameTextView.setText(player.getName());

        //DeathLevel
        playerListItem.mDeathLevelSeekBar.setProgress(player.getDeathLevel());

        //Player HP
        playerListItem.mPlayerHpValueTextView.setText(player.getHpValue() + "");
        playerListItem.mPlayerHpMaxAfterDeathEffectTextView.setText(player.getHpMaxAfterDeathEffect() + "");
        if (player.getDeathLevel() > 0) {
            playerListItem.mPlayerHpMaxTextView.setVisibility(View.VISIBLE);
            playerListItem.mPlayerHpMaxTextView.setPaintFlags(playerListItem.mPlayerHpMaxTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            playerListItem.mPlayerHpMaxTextView.setText(player.getHpMax() + "");
        } else {
            playerListItem.mPlayerHpMaxTextView.setVisibility(View.GONE);
        }
        playerListItem.mHpSeekBar.setProgress(player.getHpValue());
        playerListItem.mHpSeekBar.setMax(player.getHpMaxAfterDeathEffect());

        //Player MP
        playerListItem.mPlayerMpValueTextView.setText(player.getMpValue() + "");
        playerListItem.mPlayerMpMaxTextView.setText(player.getMpMax() + "");
        playerListItem.mMpSeekBar.setProgress(player.getMpValue());
        playerListItem.mMpSeekBar.setMax(player.getMpMax());

        //Player Stamina
        playerListItem.mPlayerStaminaValueTextView.setText(player.getStaminaValue() + "");
        playerListItem.mPlayerStaminaMaxTextView.setText(player.getStaminaMax() + "");
        playerListItem.mStaminaSeekBar.setProgress(player.getStaminaValue());
        playerListItem.mStaminaSeekBar.setMax(player.getStaminaMax());

        return vi;
    }

    private void updateUI(Player player) {

    }
}
