package com.tony.theresistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EndGame extends AppCompatActivity {

    public TextView textViewEndGame;
    public Button buttonEndgameNew;
    public Button buttonEndgameOld;
    public LinearLayout endgameLayout;

    public Values values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        textViewEndGame = findViewById(R.id.textViewEndGame);
        buttonEndgameNew = findViewById(R.id.buttonEndgameNew);
        buttonEndgameOld = findViewById(R.id.buttonEndgameOld);
        endgameLayout = findViewById(R.id.endgameLayout);

        values = Values.getInstance();

        buttonEndgameNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values.resetVote();
                values.restGameState();
                values.playerList.clear();
                Intent intent = new Intent(getBaseContext(), PlayerNumber.class);
                startActivity(intent);
            }
        });

        buttonEndgameOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values.resetVote();
                values.restGameState();
                values.generateRoles();
                Intent intent = new Intent(getBaseContext(), Reveal.class);
                startActivity(intent);
            }
        });


        if(values.gameState.resistanceWins){
            textViewEndGame.setText("Resistance has won");
            for(int i=0; i<values.playerList.size(); i++){
                if(!values.playerList.get(i).getIsEvil())
                    values.playerList.get(i).setScore(values.playerList.get(i).getScore()+1);
            }
        }else{
            textViewEndGame.setText("Spies have won");
            for(int i=0; i<values.playerList.size(); i++){
                if(values.playerList.get(i).getIsEvil())
                    values.playerList.get(i).setScore(values.playerList.get(i).getScore()+1);
            }
        }


        for(int i=0; i<10; i++){
            if(i>values.playerList.size()-1)
                endgameLayout.getChildAt(i).setVisibility(View.GONE);
            else{
                ((TextView)endgameLayout.getChildAt(i)).setText(values.playerList.get(i).getScore() + "  -  " + values.playerList.get(i).getName() );
            }
        }
    }
}
