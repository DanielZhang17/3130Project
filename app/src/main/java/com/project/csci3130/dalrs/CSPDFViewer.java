package com.project.csci3130.dalrs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class CSPDFViewer extends AppCompatActivity {
    PDFView CS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cspdfviewer);

        CS = (PDFView)findViewById(R.id.CSPDFVIEWER);
        CS.fromAsset("CS.pdf");
    }
}
