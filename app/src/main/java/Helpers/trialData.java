package Helpers;

import java.io.Serializable;

/**
 * Created by broly on 14/03/2017.
 */

public class trialData implements Serializable {
    int trialNumber;
    boolean isCorrect;
    double touchAccuracy;
    String stimuliName;

    public trialData(int trialNumber, boolean isCorrect, int level, String stimName) {
        this.trialNumber = trialNumber;
        this.isCorrect = isCorrect;
        this.level = level;
        this.stimuliName = stimName;
    }

    public int getTrialNumber() {
        return trialNumber;
    }

    public int getLevel() {
        return level;
    }

    int level;

    public String getStimuliName() { return stimuliName;}

    public boolean getIsCorrect() {
        return isCorrect;
    }
}
