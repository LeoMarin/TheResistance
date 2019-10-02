package com.tony.theresistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayerNumber extends AppCompatActivity {

    private ImageView leftArrow;
    private ImageView rightArrow;
    private Button buttonNext;
    private TextView textViewPlayerNumber;
    private TextView textViewResistanceNumber;
    private TextView textViewSpyNumber;

    private int playerNumber;
    private int spyNumber;
    private int resistanceNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_number);

        playerNumber = 8;
        spyNumber = 3;
        resistanceNumber = 5;

        leftArrow = findViewById(R.id.imageViewLeftArrow);
        rightArrow = findViewById(R.id.imageViewRightArrow);
        buttonNext = findViewById(R.id.buttonNext);
        textViewPlayerNumber = findViewById(R.id.textViewPlayerNumber);
        textViewResistanceNumber = findViewById(R.id.textViewResistanceNumber);
        textViewSpyNumber = findViewById(R.id.textViewSpyNumber);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playerNumber > 5){
                    playerNumber--;
                    setPlayerNumbers();
                }
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playerNumber<10){
                    playerNumber++;
                    setPlayerNumbers();
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddPlayers.class);
                intent.putExtra(Values.PLAYER_NUMBER_EXTRA, playerNumber);
                startActivity(intent);
            }
        });

    }

    private void setPlayerNumbers(){

        switch (playerNumber){
            case 5:
                resistanceNumber = 3;
                spyNumber = 2;
                break;
            case 6:
                resistanceNumber = 4;
                spyNumber = 2;
                break;
            case 7:
                resistanceNumber = 4;
                spyNumber = 3;
                break;
            case 8:
                resistanceNumber = 5;
                spyNumber = 3;
                break;
            case 9:
                resistanceNumber = 6;
                spyNumber = 3;
                break;
            case 10:
                resistanceNumber = 6;
                spyNumber = 4;
                break;
        }

        textViewPlayerNumber.setText(String.format("%d", playerNumber));
        textViewResistanceNumber.setText(String.format("%d", resistanceNumber));
        textViewSpyNumber.setText(String.format("%d", spyNumber));
    }
}
