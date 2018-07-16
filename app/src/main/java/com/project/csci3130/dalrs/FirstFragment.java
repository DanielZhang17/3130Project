package com.project.csci3130.dalrs;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * The type First fragment.
 */
public class FirstFragment extends Fragment{
    /**
     * The My view.
     */
    View MyView;
    /**
     * The constant termNum.
     */
    public static long termNum;
    /**
     * The constant term.
     */
    public static int term;
    /**
     * The constant termNumber.
     */
    public static String termNumber;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        MyView = inflater.inflate(R.layout.first_layout,container,false);
        Button submit = MyView.findViewById(R.id.changeTerm);
        final Spinner spinner = MyView.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Terms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"You selected :"+spinner.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                termNum = spinner.getSelectedItemId();
                term = (int) termNum + 1;
                termNumber = ""+term;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //actions to set term
                Intent intent = new Intent(getActivity(),RegistActivity.class);
                startActivity(intent);
            }
        });
        return MyView;
    }
}
