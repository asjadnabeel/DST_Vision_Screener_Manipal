package psychophyics_plugins;

/**
 * Created by broly on 13/03/2017.
 */

public class WeightedUpDown {
    private int nextLevel = 0;

    public WeightedUpDown() {
    }

    public int calculateNextLevel(Boolean correct, int level) {
        final double threshold = 0.75;

        if(correct){
            nextLevel++;
        }else{
            nextLevel = (int) Math.round(level - (threshold / (1 - threshold)));
        }

        return nextLevel;
    }
}