package com.project.csci3130.dalrs;

import android.content.Context;

public abstract class BaseActivity extends Context {
    private static BaseActivity instance = null;

   // @Override
    public void setContentView(int layoutResID) {
        instance.setContentView(layoutResID);

        instance = this;
    }

    public static BaseActivity getInstance() {
        return instance;
    }
}
