package com.optometry.plymouth.mrda;

import android.app.Activity;
import android.os.Bundle;

public class Stimuli_Canvas extends Activity {

    StimuliSubCanvas stimuliCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load game canvas into View
        stimuliCanvas = new StimuliSubCanvas(this);
        setContentView(stimuliCanvas);
    }
}