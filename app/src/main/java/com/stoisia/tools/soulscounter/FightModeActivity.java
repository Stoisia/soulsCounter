package com.stoisia.tools.soulscounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.stoisia.tools.soulscounter.Adapter.PlayerListAdapter;
import com.stoisia.tools.soulscounter.Controller.FightModeController;
import com.stoisia.tools.soulscounter.Model.GameData;

/**
 * Created by Nico on 30/05/2017.
 */

public class FightModeActivity extends Activity {

    private FightModeController mController = new FightModeController();

    private ListView mPlayerListListView;
    private PlayerListAdapter mPlayerListAdapter;
    private Button mStartFightButton;
    private LinearLayout mFightCommandsLinearLayout;
    private TextView mRoundTextView;
    private Button mNextRoundButton;
    private Button mStopFightButton;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_mode_layout);

        mPlayerListListView = (ListView) findViewById(R.id.mPlayerListListView);
        mStartFightButton = (Button) findViewById(R.id.mStartFightButton);
        mFightCommandsLinearLayout = (LinearLayout) findViewById(R.id.mFightCommandsLinearLayout);
        mRoundTextView = (TextView) findViewById(R.id.mRoundTextView);
        mNextRoundButton = (Button) findViewById(R.id.mNextRoundButton);
        mStopFightButton = (Button) findViewById(R.id.mStopFightButton);

        mPlayerListAdapter = new PlayerListAdapter(this, mController.getListPlayer());
        mPlayerListListView.setAdapter(mPlayerListAdapter);

        mStartFightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mController.setIsInFight(true);
                updateUi();
            }
        });

        mNextRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mController.nextRound();
                updateUi();
            }
        });

        mStopFightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mController.setIsInFight(false);
                updateUi();
            }
        });
    }

    private void updateUi() {
        if (mController.isInFight()) {
            mStartFightButton.setVisibility(View.GONE);
            mFightCommandsLinearLayout.setVisibility(View.VISIBLE);
            mRoundTextView.setText(mController.getRoundCount() + "");
        } else {
            mStartFightButton.setVisibility(View.VISIBLE);
            mFightCommandsLinearLayout.setVisibility(View.GONE);
        }
        mPlayerListAdapter.notifyDataSetChanged();
    }
}
