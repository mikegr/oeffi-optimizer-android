package com.mintplex.oeffioptimizer;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;


public class AddExitFragment extends DialogFragment {

    public static final String FRAGMENT_TAG = "ADD_EXIT_FRAGMENT";
    
    EditText editName;
    EditText editHint;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_new_exit, null);
        editName = (EditText) view.findViewById(R.id.dialog_new_exit_name);
        editHint = (EditText) view.findViewById(R.id.dialog_new_exit_hint);
        
        View ok = view.findViewById(R.id.dialog_new_exit_save);
        ok.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                ((EnterExitFinished)getActivity()).enterExitFinished(editName.getText().toString(), editHint.getText().toString());
            }
        });
        
        getDialog().setTitle("Neuer Hinweis");
        return view;
    }
    
    public interface EnterExitFinished {
        public void enterExitFinished(String name, String hint);        
    }
    
    
}

