package com.project.csci3130.dalrs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.barteksc.pdfviewer.PDFView;

/**
 * The type Applied cspdf.
 */
public class AppliedCSPDF extends AppCompatActivity {
    /**
     * The Applied cs.
     */
    PDFView AppliedCS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_cspdf);

        AppliedCS = (PDFView)findViewById(R.id.APPLIEDCSPDFVIEWER);
        AppliedCS.fromAsset("Applied CS.pdf").load();
    }
}
