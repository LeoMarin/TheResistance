package com.tony.theresistance;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Reveal extends AppCompatActivity {

    private int revealedPlayers = 0;
    private Values values;

    private Button nextPlayerButton;
    private TextView playerNameTextView;

    private Animation animation;
    private ImageView roleImageView;
    private ImageView revealedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);

        values = Values.getInstance();

        nextPlayerButton = findViewById(R.id.buttonNextPlayer);

        roleImageView = findViewById(R.id.roleImageView);
        revealedImageView = findViewById(R.id.revealedImageView);

        playerNameTextView = findViewById(R.id.textViewPlayerName);

        roleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateAnimation(roleImageView, revealedImageView);
            }
        });
        revealedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateAnimation(revealedImageView, roleImageView);
            }
        });

        playerNameTextView.setText(values.playerList.get(0).getName());

        nextPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(revealedPlayers < values.playerList.size()-1){
                    revealedPlayers++;
                    playerNameTextView.setText(values.playerList.get(revealedPlayers).getName());
                    nextPlayerButton.setEnabled(false);
                    //TODO: change image according to role
                    if(revealedPlayers == values.playerList.size()-1)
                        nextPlayerButton.setText("Start night phase");
                }
                else{
                    //TODO: night phase start
                }
            }
        });
    }

    private void rotateAnimation(ImageView shown, final ImageView hidden) {

        nextPlayerButton.setEnabled(true);

        ObjectAnimator animation = ObjectAnimator.ofFloat(shown, "rotationY", 0.0f, 180f);
        animation.setDuration(500);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator animation1 = ObjectAnimator.ofFloat(hidden, "rotationY", 180f, 360f);
        animation1.setDuration(500);
        animation1.setInterpolator(new AccelerateDecelerateInterpolator());


        animation.start();
        animation1.start();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hidden.bringToFront();
            }
        }, 250);
    }


}
