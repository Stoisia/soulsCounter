package com.stoisia.tools.soulscounter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.stoisia.tools.soulscounter.Adapter.PlayerListAdapter;
import com.stoisia.tools.soulscounter.Model.GameData;

/**
 * Created by Nico on 30/05/2017.
 */

public class FightModeActivity extends Activity {

    private ListView mPlayerListListView;
    private PlayerListAdapter mPlayerListAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_mode_layout);

        mPlayerListListView = (ListView) findViewById(R.id.mPlayerListListView);
        mPlayerListAdapter = new PlayerListAdapter(this, GameData.GetInstance().getPlayerList());
        mPlayerListListView.setAdapter(mPlayerListAdapter);
    }
}
