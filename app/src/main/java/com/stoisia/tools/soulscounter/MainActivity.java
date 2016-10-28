package com.stoisia.tools.soulscounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, Souls.class);
        spec = tabHost
                .newTabSpec("Widget")
                .setIndicator(getResources().getString(R.string.soulsCounter))
                .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, LevelCost.class);
        spec = tabHost
                .newTabSpec("Form")
                .setIndicator(getResources().getString(R.string.levelCost))
                .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Transfer.class);
        spec = tabHost
                .newTabSpec("onglet3")
                .setIndicator(getResources().getString(R.string.transfer))
                .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);


    }
}
