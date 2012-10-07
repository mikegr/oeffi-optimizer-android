package com.mintplex.oeffioptimizer;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
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
    
    EditText editName;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_new_location, null);
        
        editName = (EditText) view.findViewById(R.id.dialog_new_location_name);
        getDialog().setTitle(R.string.ort_hinzufuegen);
        
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
