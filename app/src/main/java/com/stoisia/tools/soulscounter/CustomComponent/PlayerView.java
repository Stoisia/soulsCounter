package com.stoisia.tools.soulscounter.CustomComponent;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stoisia.tools.soulscounter.R;

/**
 * Created by Nico on 28/10/2016.
 */

public class PlayerView extends RelativeLayout {

    public PlayerView(Context context) {
        super(context);
        buildView(context);
    }

    public PlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        buildView(context);
    }

    public PlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        buildView(context);
    }

//    <RelativeLayout
//    android:id="@+id/souls_player1"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_margin="3dp"
//    android:layout_weight="1"
//    android:background="@color/aliveColor"
//    android:clickable="true"
//    android:padding="2dp">
//
//          <ImageView
//          android:layout_width="90dp"
//          android:layout_height="90dp"
//          android:layout_centerHorizontal="true"
//          android:layout_gravity="center"
//          android:src="@drawable/character" />
//
//          <TextView
//          android:id="@+id/souls_soulsPlayer1"
//          android:layout_width="wrap_content"
//          android:layout_height="wrap_content"
//          android:layout_centerHorizontal="true"
//          android:layout_centerVertical="true"
//          android:text="0"
//          android:textSize="18dp"
//          android:textStyle="bold"/>
//
//    </RelativeLayout>

    private TextView playerSoulsCountTextView;
    private int aliveColor;
    private int deathColor;

    private void buildView(Context context) {

        final float scale = getContext().getResources().getDisplayMetrics().density;
        int size90dp = (int) (90 * scale + 0.5f);

        aliveColor = getResources().getColor(R.color.aliveColor);
        deathColor = getResources().getColor(R.color.deathColor);
        setBackgroundColor(aliveColor);
        setClickable(true);
        setPadding(2, 2, 2, 2);

        LayoutParams layoutParams = new LayoutParams(size90dp,size90dp);

        ImageView image = new ImageView(context);
        image.setImageResource(R.drawable.character);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        addView(image, layoutParams);

        playerSoulsCountTextView = new TextView(context);
        layoutParams = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        playerSoulsCountTextView.setText("0");
        playerSoulsCountTextView.setTextSize(18);
        addView(playerSoulsCountTextView, layoutParams);
    }

    public void SetPlayerSoulsCount(int soulsCount){
        playerSoulsCountTextView.setText(Integer.toString(soulsCount));
    }

    public void SetPlayerIsDead(boolean isDead){
        setBackgroundColor(isDead ? deathColor : aliveColor);
    }
}
