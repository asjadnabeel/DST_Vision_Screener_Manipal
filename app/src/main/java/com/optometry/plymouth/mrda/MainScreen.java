package com.optometry.plymouth.mrda;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {

    /**
     * Stimuli Variables
     * Related variables to the
     */
    private int stimuliRounds = 30;
    private int sequentialErrors = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Intent intent = new Intent(MainScreen.this, StimuliScreen.class);
                Intent intent = new Intent(MainScreen.this, Stimuli_Canvas.class);

                startActivity(intent);
                //Toast.makeText(MainScreen.this, "Start button clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        //Option button function
        findViewById(R.id.btnOptions).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final Dialog dialog = new Dialog(MainScreen.this);

                //Select our XML design
                dialog.setContentView(R.layout.options_menu);
                dialog.setTitle("Options");

                //Force button confirmation to leave the menu
                dialog.setCanceledOnTouchOutside(false);


                Button confirmButton = (Button) dialog.findViewById(R.id.dialogOptionsOK);

                // if ok button is clicked, save our settings and leave menu
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Note: User SharedPreferences to store/retrieve data
                        Toast.makeText(MainScreen.this, "Options 'OK' button clicked!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                Button cancelButton = (Button) dialog.findViewById(R.id.dialogOptionsCancel);
                // if cancel button is clicked, just close the custom dialog
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainScreen.this, "Options 'Cancel' button clicked!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                //Everything is prepared, show the user
                dialog.show();
            }
        });
    }
}
