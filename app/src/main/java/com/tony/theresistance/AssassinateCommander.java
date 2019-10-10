package com.tony.theresistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class AssassinateCommander extends AppCompatActivity {

    private LinearLayout assassinateLayout;
    private Button buttonAssasinate;

    private Values values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assassinate_commander);

        assassinateLayout = findViewById(R.id.assassinateLayout);
        buttonAssasinate = findViewById(R.id.buttonAssassinate);

        values = Values.getInstance();

        buttonAssasinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(values.gameState.assassinTarget.getRole() == Values.Role.COMMANDER){
                    values.gameState.resistanceWins = false;
                    //TODO show if assassin successful
                }
                else{
                    values.gameState.resistanceWins = true;
                }

                Intent intent = new Intent(getBaseContext(), EndGame.class);
                startActivity(intent);
            }
        });

        buttonAssasinate.setEnabled(false);

        for(int i=0; i<10; i++){
            if(i>values.gameState.numPlayers-1)
                assassinateLayout.getChildAt(i).setVisibility(View.GONE);
            else if(values.playerList.get(i).getIsEvil())
                assassinateLayout.getChildAt(i).setVisibility(View.GONE);
            else {
                ((CheckBox) assassinateLayout.getChildAt(i)).setText(values.playerList.get(i).getName());
                assassinateLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int playerIndex = Integer.parseInt(view.getTag().toString());
                        boolean checked = ((CheckBox) view).isChecked();

                        if(checked) {
                            for (int i = 0; i < 10; i++)
                                ((CheckBox) assassinateLayout.getChildAt(i)).setChecked(false);
                            ((CheckBox) view).setChecked(true);
                            values.gameState.assassinTarget = values.playerList.get(playerIndex);
                            buttonAssasinate.setEnabled(true);
                        } else{
                            buttonAssasinate.setEnabled(false);
                        }
                    }
                });
            }
        }

    }
}
