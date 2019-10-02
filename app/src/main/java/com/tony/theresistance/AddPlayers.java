package com.tony.theresistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddPlayers extends AppCompatActivity {

    private int playerNumber;
    private Values values;
    private int addedPlayerNumber;

    private Button addPlayerButton;
    private Button startNightPhaseButton;

    private TextView addPlayersTextView;
    private EditText editText;


    private TextView[] playerTextViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        Intent intent = getIntent();
        playerNumber = intent.getIntExtra(Values.PLAYER_NUMBER_EXTRA, 5);

        values = Values.getInstance();

        addPlayerButton = findViewById(R.id.buttonAddPlayer);
        startNightPhaseButton = findViewById(R.id.buttonNightPhase);

        addPlayersTextView = findViewById(R.id.textViewAddPlayers);

        addPlayersTextView.setText("ADD "+ playerNumber + " PLAYERS");

        values = Values.getInstance();
        values.generatePlayers(playerNumber);

        playerTextViews = new TextView[10];
        playerTextViews[0] = findViewById(R.id.textViewPlayer1);
        playerTextViews[1] = findViewById(R.id.textViewPlayer2);
        playerTextViews[2] = findViewById(R.id.textViewPlayer3);
        playerTextViews[3] = findViewById(R.id.textViewPlayer4);
        playerTextViews[4] = findViewById(R.id.textViewPlayer5);
        playerTextViews[5] = findViewById(R.id.textViewPlayer6);
        playerTextViews[6] = findViewById(R.id.textViewPlayer7);
        playerTextViews[7] = findViewById(R.id.textViewPlayer8);
        playerTextViews[8] = findViewById(R.id.textViewPlayer9);
        playerTextViews[9] = findViewById(R.id.textViewPlayer10);

        editText = findViewById(R.id.editText);

        addedPlayerNumber = 0;
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals(""))
                    return;

                playerTextViews[addedPlayerNumber].setVisibility(View.VISIBLE);
                playerTextViews[addedPlayerNumber].setText(editText.getText().toString());

                values.playerList.get(addedPlayerNumber).setName(editText.getText().toString());
                editText.setText("");
                addedPlayerNumber++;
                if(addedPlayerNumber>=playerNumber){
                    addPlayerButton.setEnabled(false);
                    startNightPhaseButton.setEnabled(true);
                    editText.setEnabled(false);
                }
            }
        });


    }
}
