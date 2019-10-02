package com.tony.theresistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AddPlayers extends AppCompatActivity {

    private int playerNumber;
    private Values values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        Intent intent = getIntent();
        playerNumber = intent.getIntExtra(Values.PLAYER_NUMBER_EXTRA, 5);

        values = Values.getInstance();

    }
}
