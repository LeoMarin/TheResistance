package com.tony.theresistance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class AssassinateCommander extends AppCompatActivity {

    private LinearLayout assassinateLayout;
    private ConstraintLayout assassinateMainLayout;
    private ConstraintLayout assassinationReveal;
    private Button buttonAssassinate;

    private Values values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assassinate_commander);

        assassinateLayout = findViewById(R.id.assassinateLayout);
        buttonAssassinate = findViewById(R.id.buttonAssassinate);
        assassinateMainLayout = findViewById(R.id.assassinateMainLayout);
        assassinationReveal = findViewById(R.id.assassinationReveal);

        values = Values.getInstance();

        buttonAssassinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(values.gameState.assassinTarget.getRole() == Values.Role.COMMANDER){
                    values.gameState.resistanceWins = false;
                    assassinationReveal.setBackgroundColor(getResources().getColor(R.color.colorSpy));
                }
                else{
                    values.gameState.resistanceWins = true;
                    assassinationReveal.setBackgroundColor(getResources().getColor(R.color.colorResistance));
                }

                assassinateMainLayout.setVisibility(View.GONE);
                Animation fadeInColor = new AlphaAnimation(0, 1);
                fadeInColor.setInterpolator(new DecelerateInterpolator()); //add this
                fadeInColor.setDuration(500);

                AnimationSet animation = new AnimationSet(false); //change to false
                animation.addAnimation(fadeInColor);
                assassinationReveal.setAnimation(animation);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getBaseContext(), EndGame.class);
                        startActivity(intent);
                    }
                }, 1000);
            }
        });

        buttonAssassinate.setEnabled(false);

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
                            buttonAssassinate.setEnabled(true);
                        } else{
                            buttonAssassinate.setEnabled(false);
                        }
                    }
                });
            }
        }

    }
}
