package com.mintplex.oeffioptimizer;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class OOActivity extends SherlockFragmentActivity {

    protected OOApplication app;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        app = getApp();
    }
    public OOApplication getApp() {
        return (OOApplication) getApplication();
    }
    
}
