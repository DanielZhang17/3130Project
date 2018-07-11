package com.project.csci3130.dalrs;

import android.content.Context;

/**
 * The type Base activity.
 */
public abstract class BaseActivity extends Context {
    private static BaseActivity instance = null;

    /**
     * Sets content view.
     *
     * @param layoutResID the layout res id
     */
// @Override
    public void setContentView(int layoutResID) {
        instance.setContentView(layoutResID);

        instance = this;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static BaseActivity getInstance() {
        return instance;
    }
}
