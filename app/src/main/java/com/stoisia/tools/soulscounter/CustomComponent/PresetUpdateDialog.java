package com.stoisia.tools.soulscounter.CustomComponent;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
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

public class PresetUpdateDialog extends Dialog {

    private Context mContext;
    private IDialogEvent mListener;
    private String mValueTitle;
    private ArrayList<Integer> mPresetValues;
    private ArrayList<Integer> mPresetValuesCopy = new ArrayList<>();

    private TextView mPresetTitleTextView;
    private LinearLayout mPresetValuesLinearLayout;
    private EditText mNewPresetValueEditText;
    private Button mAddNewPresetValueButton;
    private Button mUpdatePresetsButton;
    private Button mAbortPresetUpdateButton;

    public interface IDialogEvent {
        void onValidate();

        void onAbort();
    }

    public PresetUpdateDialog(@NonNull Context context, String valueTitle, ArrayList<Integer> presetValues, IDialogEvent listener) {
        super(context);
        mContext = context;
        mListener = listener;
        mPresetValues = presetValues;
        mValueTitle = valueTitle;

        for (int presetValue : presetValues) {
            mPresetValuesCopy.add(presetValue);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.preset_update_dialog);
        mPresetTitleTextView = (TextView) findViewById(R.id.mPresetTitleTextView);
        mPresetValuesLinearLayout = (LinearLayout) findViewById(R.id.mPresetValuesLinearLayout);
        mNewPresetValueEditText = (EditText) findViewById(R.id.mNewPresetValueEditText);
        mAddNewPresetValueButton = (Button) findViewById(R.id.mAddNewPresetValueButton);
        mUpdatePresetsButton = (Button) findViewById(R.id.mUpdatePresetsButton);
        mAbortPresetUpdateButton = (Button) findViewById(R.id.mAbortPresetUpdateButton);

        mPresetTitleTextView.setText(mValueTitle);
        updatePresetValues();
        mAddNewPresetValueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String presetValueStr = mNewPresetValueEditText.getText().toString();
                int presetValue = 0;
                try {
                    presetValue = Integer.parseInt(presetValueStr);
                } catch (NumberFormatException ex) {
                    return;
                }
                mNewPresetValueEditText.setText("");
                if (mPresetValuesCopy.contains(presetValue)) return;
                mPresetValuesCopy.add(presetValue);
                updatePresetValues();
            }
        });

        mUpdatePresetsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresetValues.clear();
                for (int presetValue : mPresetValuesCopy) {
                    mPresetValues.add(presetValue);
                }
                if (mListener != null) mListener.onValidate();
                dismiss();
            }
        });

        mAbortPresetUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) mListener.onAbort();
                dismiss();
            }
        });
    }

    private void updatePresetValues() {
        mPresetValuesLinearLayout.removeAllViewsInLayout();
        for (Integer presetValue : mPresetValuesCopy) {

            int primaryColor = mContext.getResources().getColor(R.color.colorPrimary);

            TextView presetValueTextView = new TextView(mContext);
            presetValueTextView.setText(presetValue + "");
            presetValueTextView.setTag(presetValue);
            presetValueTextView.setBackgroundColor(primaryColor);
            presetValueTextView.setGravity(Gravity.CENTER);
            presetValueTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int presetValue = (int) view.getTag();
                    if (!mPresetValuesCopy.contains(presetValue)) return true;
                    mPresetValuesCopy.remove((Object) presetValue);
                    updatePresetValues();
                    return true;
                }
            });

            LinearLayout.LayoutParams presetValueTextViewLayoutParams = new LinearLayout.LayoutParams(60, 60);
            presetValueTextViewLayoutParams.setMargins(5, 5, 5, 5);
            mPresetValuesLinearLayout.addView(presetValueTextView, presetValueTextViewLayoutParams);
        }
    }
}
