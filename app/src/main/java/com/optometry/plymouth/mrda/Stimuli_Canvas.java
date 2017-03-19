package com.optometry.plymouth.mrda;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class Stimuli_Canvas extends Activity {

    StimuliSubCanvas stimuliCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load game canvas into View
        stimuliCanvas = new StimuliSubCanvas(this);
        setContentView(stimuliCanvas);

//        if(stimuliCanvas.getVisibility() == View.GONE){
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            //ft.replace(R.id.fragment_container, sr);
//            ft.add(sr, null);
//            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            ft.commit();
//        }
    }
}