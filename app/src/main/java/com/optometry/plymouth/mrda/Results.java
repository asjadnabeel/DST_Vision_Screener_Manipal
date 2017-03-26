package com.optometry.plymouth.mrda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Helpers.trialData;

public class Results extends AppCompatActivity {

    private Map<Integer, trialData> userHistory;
    String totalTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        userHistory = (Map<Integer, trialData>)bundle.getSerializable("userHistory");
        totalTime = bundle.getString("totalTime");

        placeContents();
    }

    public void placeContents()
    {
        TextView txtDateView;
        TextView txtAverageStim;
        TextView txtTotalRounds;
        TextView txtTotalTime;

        txtDateView =  (TextView)findViewById(R.id.txtDate);
        txtTotalTime = (TextView)findViewById(R.id.txtTotalTime);
        txtTotalRounds =  (TextView)findViewById(R.id.txtTotalRounds);
        txtAverageStim =  (TextView)findViewById(R.id.txtOptStim);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        txtDateView.setText(df.format(new Date()));
        txtTotalTime.setText(totalTime);
        txtTotalRounds.setText(Integer.toString(userHistory.size()));
        txtAverageStim.setText(calculateThreashold());
    }

    private String calculateThreashold()
    {
        int sum = 0;
        for(int i = userHistory.size() - 5; i < userHistory.size();i++)
        {
            sum += userHistory.get(i).getLevel();
        }

        int averageLevel = sum / userHistory.size();

        //Now get trialName
        String trialName = "";
        for(int i = userHistory.size() - 5; i < userHistory.size();i++)
        {
            if(userHistory.get(i).getLevel() == averageLevel)
            {
                trialName = userHistory.get(i).getStimuliName();
            }
        }
        return trialName;
    }

    private Map<Integer, Double> calculateLevelPercentage() {
        int hits = 0;
        int misses = 0;

        Map<Integer, Double> dictPercentage = new HashMap<>();

        for(int i = 0; i < userHistory.size(); i++) {
            int total = 0;
            int numCorrect = 0;
            for (Map.Entry<Integer, trialData> entry : userHistory.entrySet()) {

                if (entry.getValue().getLevel() == i) {
                    total++;

                    if(entry.getValue().getIsCorrect())
                    {
                        numCorrect++;
                    }
                }

            }
            if(total > 0) {
                double percentage = (double) numCorrect / (double) total;
                dictPercentage.put(i, percentage);
            }
            //Do calc for each entry here
        }
        return dictPercentage;
    }
}
