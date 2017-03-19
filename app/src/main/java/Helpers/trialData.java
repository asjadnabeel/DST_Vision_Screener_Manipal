package Helpers;

import java.io.Serializable;

/**
 * Created by broly on 14/03/2017.
 */

public class trialData implements Serializable {
    int trialNumber;
    boolean isCorrect;
    double touchAccuracy;

    public trialData(int trialNumber, boolean isCorrect, int level) {
        this.trialNumber = trialNumber;
        this.isCorrect = isCorrect;
        this.level = level;
    }

    public int getTrialNumber() {
        return trialNumber;
    }

    public int getLevel() {
        return level;
    }

    int level;

    public boolean isCorrect() {
        return isCorrect;
    }
}
