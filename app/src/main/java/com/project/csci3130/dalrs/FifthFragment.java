package com.project.csci3130.dalrs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * The type Fifth fragment.
 */
public class FifthFragment extends Fragment{

    /**
     * The My view.
     */
    View MyView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        MyView = inflater.inflate(R.layout.fifth_layout,container,false);
        return MyView;
    }
}
