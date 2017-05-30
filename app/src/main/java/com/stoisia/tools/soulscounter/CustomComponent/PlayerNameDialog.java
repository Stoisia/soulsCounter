package com.stoisia.tools.soulscounter.CustomComponent;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.stoisia.tools.soulscounter.R;

/**
 * Created by Nico on 30/05/2017.
 */

public class PlayerNameDialog extends Dialog {

    private IDialogEvent mListener;
    private String mPlayerName;
    private EditText mPlayerNameEditText;
    private Button mUpdatePlayerNameButton;
    private Button mAbortPlayerNameUpdateButton;

    public interface IDialogEvent {
        void onValidate(String playerName);

        void onAbort();
    }

    public PlayerNameDialog(@NonNull Context context, String playerName, IDialogEvent listener) {
        super(context);
        mListener = listener;
        mPlayerName = playerName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.player_name_dialog);
        mPlayerNameEditText = (EditText) findViewById(R.id.mPlayerNameEditText);
        mUpdatePlayerNameButton = (Button) findViewById(R.id.mUpdatePlayerNameButton);
        mAbortPlayerNameUpdateButton = (Button) findViewById(R.id.mAbortPlayerNameUpdateButton);
        mPlayerNameEditText.setText(mPlayerName);

        mUpdatePlayerNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null)
                    mListener.onValidate(mPlayerNameEditText.getText().toString());
                dismiss();
            }
        });

        mAbortPlayerNameUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) mListener.onAbort();
                dismiss();
            }
        });
    }
}
