package com.optometry.plymouth.mrda;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by David Bear on 05/02/2017.
 */

public class StimuliSubCanvas extends View implements View.OnTouchListener {

    //Store images in memory for easy retrieval
    Bitmap bmpFeedback;
    Bitmap bmpBlankFeedback;
    Bitmap bmpStimuli;
    Bitmap bmpBlankStimuli;

    int level = 1;

    //These should reflect user options
    int intNumOfStimuli = 5;
    int intNumOfRealStimuli = 2;

    //positional information
    final int bitmapDimension = 200;
    final int stimImageGap = 10;

    Stimuli[] stimulisList; //R

    public StimuliSubCanvas(Context context){
        super(context);
        this.setOnTouchListener(this);
        createLevel();
    }

    public void createLevel()
    {
        bmpBlankStimuli = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rda_blank), bitmapDimension,bitmapDimension, false);

        //This one should change according to the level
        bmpStimuli = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rda1_20), bitmapDimension,bitmapDimension, false);

        bmpFeedback = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.feedback_arrow2), bitmapDimension,bitmapDimension, false);
        bmpBlankFeedback = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.feedback_blank), bitmapDimension,bitmapDimension, false);

        stimulisList = new Stimuli[intNumOfStimuli];
        Stimuli x;

        /*Populate the stimuli array with blank stimulis*/
        for(int i = 0; i < intNumOfStimuli - intNumOfRealStimuli; i++)
        {
            x = new Stimuli(bmpBlankStimuli, bmpBlankFeedback);
            stimulisList[i] = x;
        }

        /*Populate the stimuli array with real stimulis*/
        for(int i = intNumOfStimuli - intNumOfRealStimuli; i < intNumOfStimuli; i++)
        {
            x = new Stimuli(bmpStimuli, bmpBlankFeedback);
            stimulisList[i] = x;
        }

        /*Shuffle the contents of stimuli array*/
        Collections.shuffle(Arrays.asList(stimulisList));

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        float xPosition, yPosition;

        //Ensure the list is balanced in the center horizontally
        xPosition = (width / 2) -  (((bmpStimuli.getWidth() * intNumOfStimuli) + (stimImageGap * intNumOfStimuli)) / 2);
        //Set in the center vertically
        yPosition = (height / 2) - bmpStimuli.getHeight();

        /*Set coordinates according to position*/
        for(int i = 0; i < intNumOfStimuli; i++)
        {
            stimulisList[i].setCoordinates(xPosition,yPosition);
            //Update xPosition
            xPosition += bmpStimuli.getWidth() + stimImageGap;
        }
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#707070"));

        //Go through our circles and render them to screen
        for(int i = 0; i < intNumOfStimuli; i++) {
            canvas.drawBitmap(stimulisList[i].image, stimulisList[i].xCoordinate, stimulisList[i].yCoordinate, new Paint());
            canvas.drawBitmap(stimulisList[i].feedbackImage, stimulisList[i].xCoordinate, stimulisList[i].yCoordinate + stimulisList[i].image.getHeight() + 20, new Paint());
        }

        //Allows the screen to be updated automatically
        invalidate();
    }


    public boolean onTouch(View v, MotionEvent event) {
        //Get user touch coordinates
        float touchXCoord = event.getX();
        float touchYCoord = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            float xImgCoord;
            float yImgCoord;
            for(int i = 0; i < intNumOfStimuli; i++)
            {
                xImgCoord = stimulisList[i].xCoordinate;
                yImgCoord = stimulisList[i].yCoordinate;

                //Check if the user touched within the boundaries of any of our icons
                if ((touchXCoord >= xImgCoord && touchXCoord <= xImgCoord + bmpStimuli.getWidth())
                        && (touchYCoord >= yImgCoord && touchYCoord <= yImgCoord + bmpStimuli.getHeight())) {

                    //Select accordingly
                    if(stimulisList[i].isSelected == true)
                    {
                        stimulisList[i].isSelected = false;
                        stimulisList[i].feedbackImage = bmpBlankFeedback;}
                    else{
                        stimulisList[i].isSelected = true;
                        stimulisList[i].feedbackImage = bmpFeedback;
                    }
                }
            }
        }
        return true;
    }
}
