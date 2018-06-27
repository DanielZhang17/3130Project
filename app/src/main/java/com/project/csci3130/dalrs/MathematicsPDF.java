package com.project.csci3130.dalrs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class MathematicsPDF extends AppCompatActivity {
    PDFView Mathematics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematics_pdf);

        Mathematics = (PDFView)findViewById(R.id.MATHPDFVIEWER);
        Mathematics.fromAsset("Mathematics.pdf").load();
    }
}
