package com.optometry.plymouth.mrda;

import android.graphics.Bitmap;

/**
 * Created by David Bear on 05/02/2017.
 */

public class Stimuli {
    public Bitmap image;
    public float xCoordinate;
    public float yCoordinate;
    public Bitmap feedbackImage;
    boolean isSelected;

    public Stimuli(Bitmap img, Bitmap feedbackImg)
    {
        image = img;
        feedbackImage = feedbackImg;
        isSelected = false;
    }

    public void setCoordinates(float x, float y)
    {
        xCoordinate = x;
        yCoordinate = y;
    }
}
