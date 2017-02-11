package com.optometry.plymouth.mrda;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StimuliScreen extends Activity {

    Bitmap[] stimuliList = new Bitmap[19];
    Bitmap blankCircle;
    LinearLayout stimuliPanel;
    int circleAmount = 5;
    int level = 1;
    //ty
    int numOfRounds = 19;

    Bitmap[] levelList = new Bitmap[circleAmount];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stimuli_screen);
        loadSimtuli();
        blankCircle = BitmapFactory.decodeResource(getResources(), R.drawable.rda_blank);
        stimuliPanel = (LinearLayout) findViewById(R.id.stimuliLayout);

        startRound();
    }

    private void startRound() {
        ImageView r;
        Bitmap bm;

        levelList = populatLevelList(level);

            for (int i = 0; i < circleAmount; i++) {
                r = new ImageView(this);
                r.setClickable(true);

                bm = levelList[i];

                if(bm.sameAs(blankCircle))
                {
                    r.setTag("false");
                }
                else{
                    r.setTag("true");
                }
                //t

                r.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tag = (String) v.getTag();
                        Toast.makeText(v.getContext(),
                                "You clicked: " + tag + ", On level " + level,
                                Toast.LENGTH_LONG).show();

                        stimuliPanel.removeAllViews();
                        level++;

                        if(level <= numOfRounds) {
                            startRound();
                        }
                        else{
                            Toast.makeText(v.getContext(), "GAME FINISHED :D", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                r.setImageBitmap(Bitmap.createScaledBitmap(bm, 200, 200, false));

                stimuliPanel.addView(r);
            }
        }

    private Bitmap[] populatLevelList(int level)
    {
        Bitmap[] options = new Bitmap[circleAmount];

        for(int i = 0; i < circleAmount - 1; i++)
        {
            options[i] = BitmapFactory.decodeResource(getResources(), R.drawable.rda_blank);
        }

        options[circleAmount - 1] = stimuliList[level - 1];

        return options;
    }

    //To maintain quick loading, load all into memory
    private void loadSimtuli()
    {
        stimuliList[0] = BitmapFactory.decodeResource(getResources(), R.drawable.rda1_00);
        stimuliList[1] = BitmapFactory.decodeResource(getResources(), R.drawable.rda1_20);
        stimuliList[2] = BitmapFactory.decodeResource(getResources(), R.drawable.rda1_40);
        stimuliList[3] = BitmapFactory.decodeResource(getResources(), R.drawable.rda1_55);
        stimuliList[4] = BitmapFactory.decodeResource(getResources(), R.drawable.rda1_70);
        stimuliList[5] = BitmapFactory.decodeResource(getResources(), R.drawable.rda1_85);
        stimuliList[6] = BitmapFactory.decodeResource(getResources(), R.drawable.rda2_00);
        stimuliList[7] = BitmapFactory.decodeResource(getResources(), R.drawable.rda2_20);
        stimuliList[8] = BitmapFactory.decodeResource(getResources(), R.drawable.rda2_30);
        stimuliList[9] = BitmapFactory.decodeResource(getResources(), R.drawable.rda2_40);
        stimuliList[10] = BitmapFactory.decodeResource(getResources(), R.drawable.rda2_50);
        stimuliList[11] = BitmapFactory.decodeResource(getResources(), R.drawable.rda2_60);
        stimuliList[12] = BitmapFactory.decodeResource(getResources(), R.drawable.rda2_70);
        stimuliList[13] = BitmapFactory.decodeResource(getResources(), R.drawable.rda2_80);
        stimuliList[14] = BitmapFactory.decodeResource(getResources(), R.drawable.rda2_90);
        stimuliList[15] = BitmapFactory.decodeResource(getResources(), R.drawable.rda3_00);
        stimuliList[16] = BitmapFactory.decodeResource(getResources(), R.drawable.rda3_10);
        stimuliList[17] = BitmapFactory.decodeResource(getResources(), R.drawable.rda3_20);
        stimuliList[18] = BitmapFactory.decodeResource(getResources(), R.drawable.rda3_30);
    }
}