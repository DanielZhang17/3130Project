package com.project.csci3130.dalrs;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * The type Third fragment.
 */
public class ThirdFragment extends Fragment{

    /**
     * The My view.
     */
    View MyView;
    /**
     * The Phone.
     */
    CardView phone, /**
     * The Email.
     */
    email, /**
     * The Web.
     */
    web, /**
     * The Address.
     */
    address;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Contact Us");
        MyView = inflater.inflate(R.layout.third_layout,container,false);
        phone = MyView.findViewById(R.id.phone);
        email = MyView.findViewById(R.id.email);
        web = MyView.findViewById(R.id.web);
        address = MyView.findViewById(R.id.address);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9024942211"));
                startActivity(intent);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:support@dal.ca")
                        .buildUpon()
                        .build();

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(Intent.createChooser(emailIntent, "Choose client"));
            }
        });
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dal.ca"));
                startActivity(intent);
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=Dalhousie+University"));
                startActivity(intent);
            }
        });
        return MyView;
    }
}
