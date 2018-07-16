package com.project.csci3130.dalrs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * The type Sixth fragment.
 */
public class SixthFragment extends Fragment {
    /**
     * The My view.
     */
    View myView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.sixth_layout,container,false);
        return myView;
    }
}
