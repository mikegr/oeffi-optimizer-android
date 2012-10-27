package com.mintplex.oeffioptimizer;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class AddLocationFragment extends DialogFragment implements OnEditorActionListener{

    public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
    }

    public static final String ARG_TYPE = "type";
    
    EditText editName;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_new_location, null);
        
        View ok = view.findViewById(R.id.button_ok);
        ok.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                ((EditNameDialogListener)getActivity()).onFinishEditDialog(editName.getText().toString());
            }
        });
        
        View cancel = view.findViewById(R.id.button_cancel);
        cancel.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        
        editName = (EditText) view.findViewById(R.id.dialog_new_location_name);
        String levelName = getArguments().getString(ARG_TYPE);
        getDialog().setTitle(getString(R.string.location_hinzufuegen, new Object[] {levelName}));
        
        getDialog().getWindow().setSoftInputMode(
                LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        editName.setOnEditorActionListener(this);
        
        return view;
    }
    
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListener activity = (EditNameDialogListener) getActivity();
            activity.onFinishEditDialog(editName.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }
}
