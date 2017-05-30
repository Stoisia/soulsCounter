package com.stoisia.tools.soulscounter.CustomComponent;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stoisia.tools.soulscounter.R;

import java.util.ArrayList;

/**
 * Created by Nico on 30/05/2017.
 */

public class ValueUpdateWithPresetDialog extends Dialog {

    private Context mContext;
    private IDialogEvent mListener;
    private String mValueTitle;
    private int mValue;
    private int mMaxValue;
    private boolean mIsPlusOperator = false;
    private ArrayList<Integer> mPresetValues;

    private TextView mPlusOperatorTextView;
    private TextView mMinusOperatorTextView;
    private TextView mValueNameTextView;
    private TextView mValueTextView;
    private TextView mMaxValueTextView;
    private LinearLayout mPresetValuesLinearLayout;
    private Button mUpdatePresetValuesButton;
    private EditText mValueEditText;
    private Button mUpdateValueButton;
    private Button mAbortValueUpdateButton;

    public interface IDialogEvent {
        void onValidate(int value);

        void onAbort();
    }

    public ValueUpdateWithPresetDialog(@NonNull Context context, String valueTitle, int value, int maxValue, ArrayList<Integer> presetValues, IDialogEvent listener) {
        super(context);
        mContext = context;
        mListener = listener;
        mValueTitle = valueTitle;
        mValue = value;
        mMaxValue = maxValue;
        mPresetValues = presetValues;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.value_update_with_preset_dialog);

        //Get view objects
        mPlusOperatorTextView = (TextView) findViewById(R.id.mPlusOperatorTextView);
        mMinusOperatorTextView = (TextView) findViewById(R.id.mMinusOperatorTextView);

        mValueNameTextView = (TextView) findViewById(R.id.mValueNameTextView);
        mValueTextView = (TextView) findViewById(R.id.mValueTextView);
        mMaxValueTextView = (TextView) findViewById(R.id.mMaxValueTextView);

        mPresetValuesLinearLayout = (LinearLayout) findViewById(R.id.mPresetValuesLinearLayout);
        mUpdatePresetValuesButton = (Button) findViewById(R.id.mUpdatePresetValuesButton);

        mValueEditText = (EditText) findViewById(R.id.mValueEditText);
        mUpdateValueButton = (Button) findViewById(R.id.mUpdateValueButton);
        mAbortValueUpdateButton = (Button) findViewById(R.id.mAbortValueUpdateButton);

        //Set data
        mValueNameTextView.setText(mValueTitle);
        mValueTextView.setText(mValue + "");
        mMaxValueTextView.setText(mMaxValue + "");
        updatePresetValues();

        //Set click listeners
        mUpdatePresetValuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PresetUpdateDialog(mContext, mValueTitle, mPresetValues, new PresetUpdateDialog.IDialogEvent() {
                    @Override
                    public void onValidate() {
                        updatePresetValues();
                    }

                    @Override
                    public void onAbort() {

                    }
                }).show();
            }
        });

        mUpdateValueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String valueStr = mValueEditText.getText().toString();
                int value = 0;
                try {
                    value = Integer.parseInt(valueStr);
                } catch (NumberFormatException ex) {
                }
                if (mListener != null) mListener.onValidate(value * (mIsPlusOperator ? 1 : -1));
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

        mPlusOperatorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsPlusOperator = true;
                int selectedColor = mContext.getResources().getColor(R.color.colorPrimary);
                mPlusOperatorTextView.setBackgroundColor(selectedColor);
                mPlusOperatorTextView.setTextColor(Color.WHITE);
                mMinusOperatorTextView.setBackgroundColor(Color.TRANSPARENT);
                mMinusOperatorTextView.setTextColor(Color.BLACK);
            }
        });

        mMinusOperatorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsPlusOperator = false;
                int selectedColor = mContext.getResources().getColor(R.color.colorPrimary);
                mPlusOperatorTextView.setBackgroundColor(Color.TRANSPARENT);
                mPlusOperatorTextView.setTextColor(Color.BLACK);
                mMinusOperatorTextView.setBackgroundColor(selectedColor);
                mMinusOperatorTextView.setTextColor(Color.WHITE);
            }
        });
    }


    private void updatePresetValues() {
        mPresetValuesLinearLayout.removeAllViewsInLayout();
        for (Integer presetValue : mPresetValues) {

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(70, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5, 15, 5, 15);

            TextView presetValueTextView = new TextView(mContext);
            presetValueTextView.setText(presetValue + "");
            int selectedColor = mContext.getResources().getColor(R.color.colorPrimary);
            presetValueTextView.setBackgroundColor(selectedColor);
            presetValueTextView.setPadding(10, 10, 10, 10);
            presetValueTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            presetValueTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String valueStr = ((TextView) view).getText().toString();
                    int value = 0;
                    try {
                        value = Integer.parseInt(valueStr);
                    } catch (NumberFormatException ex) {
                    }
                    if (mListener != null) mListener.onValidate(value * (mIsPlusOperator ? 1 : -1));
                    dismiss();
                }
            });
            mPresetValuesLinearLayout.addView(presetValueTextView, layoutParams);
        }
    }
}
