package com.project.csci3130.dalrs;
/*
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogUtil {

    private AlertDialog dlg;
    private TextView tvText;
    private Button btnCancel,btnSure;

    private BaseActivity context;
    private String text;
    private DialogButtonListener listener;

    public void show(String text, final DialogButtonListener listener) {
        this.context = BaseActivity.getInstance();
        this.text = text;
        this.listener = listener;
        createDialog();
        setValue();
    }


    public void show(BaseActivity context, String text, final DialogButtonListener listener) {
        this.context = context;
        this.text = text;
        this.listener = listener;
        createDialog();
        setValue();
    }



    private void createDialog() {
        dlg = new AlertDialog.Builder(context).create();
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.dialog);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tvText = (TextView) window.findViewById(R.id.tvText);
        btnCancel = (Button) window.findViewById(R.id.btnCancel);
        btnSure = (Button) window.findViewById(R.id.btnSure);
    }

    private void setValue() {

        tvText.setText(text);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.cancel();
                dlg.dismiss();
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.sure();
                dlg.dismiss();
            }
        });
    }
}*/