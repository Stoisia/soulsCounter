package com.stoisia.tools.soulscounter.CustomComponent;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.stoisia.tools.soulscounter.R;

/**
 * Created by Nico on 30/05/2017.
 */

public class ValueUpdateDialog extends Dialog {

    private IDialogEvent mListener;
    private String mValueTitle;
    private int mValue;
    private TextView mValueTitleTextView;
    private EditText mValueEditText;
    private Button mUpdateValueButton;
    private Button mAbortValueUpdateButton;

    public interface IDialogEvent {
        void onValidate(int value);

        void onAbort();
    }

    public ValueUpdateDialog(@NonNull Context context, String valueTitle, int value, IDialogEvent listener) {
        super(context);
        mListener = listener;
        mValueTitle = valueTitle;
        mValue = value;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.value_update_dialog);
        mValueTitleTextView = (TextView) findViewById(R.id.mValueTitleTextView);
        mValueEditText = (EditText) findViewById(R.id.mValueEditText);
        mUpdateValueButton = (Button) findViewById(R.id.mUpdateValueButton);
        mAbortValueUpdateButton = (Button) findViewById(R.id.mAbortValueUpdateButton);

        mValueTitleTextView.setText(mValueTitle);
        mValueEditText.setText(mValue + "");

        mUpdateValueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueStr = mValueEditText.getText().toString();
                int value = 0;
                try {
                    value = Integer.parseInt(valueStr);
                } catch (NumberFormatException ex) {
                }
                if (mListener != null) mListener.onValidate(value);
                dismiss();
            }
        });

        mAbortValueUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) mListener.onAbort();
                dismiss();
            }
        });
    }
}
