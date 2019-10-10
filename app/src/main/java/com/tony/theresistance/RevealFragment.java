package com.tony.theresistance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;


public class RevealFragment extends Fragment {

    private Values values;

    private TextView textViewRevealFragment;
    private TextView textViewPassedCount;
    private TextView textViewSabotagedCount;

    private TextView textViewTimer;
    private ConstraintLayout timerLayout;
    private ConstraintLayout mainRevealLayout;
    private ConstraintLayout colorLayout;

    private Button buttonRevealFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reveal_fragment, container, false);
        values = Values.getInstance();

        textViewRevealFragment = view.findViewById(R.id.textViewRevealFragment);
        textViewPassedCount = view.findViewById(R.id.textViewPassedCount);
        textViewSabotagedCount = view.findViewById(R.id.textViewSabotagedCount);
        buttonRevealFragment = view.findViewById(R.id.buttonRevealFragment);

        textViewTimer = view.findViewById(R.id.textViewTimer);
        timerLayout = view.findViewById(R.id.timerLayout);
        mainRevealLayout = view.findViewById(R.id.mainRevealLayout);
        colorLayout = view.findViewById(R.id.colorLayout);


        buttonRevealFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(values.gameState.numSpyWins == 3 ){
                    values.gameState.resistanceWins = false;
                    Intent intent = new Intent(getActivity(), EndGame.class);
                    startActivity(intent);
                }

                else if(values.gameState.numResistanceWins == 3){
                    boolean hasCommander = false;
                    for(int i=0; i<values.playerList.size(); i++){
                        if(values.playerList.get(i).getRole() == Values.Role.COMMANDER)
                            hasCommander = true;
                    }

                    if(hasCommander) {
                        Intent intent = new Intent(getActivity(), AssassinateCommander.class);
                        startActivity(intent);
                    } else {
                        values.gameState.resistanceWins = true;
                        Intent intent = new Intent(getActivity(), EndGame.class);
                        startActivity(intent);
                    }
                }

                else{
                    values.resetVote();
                    ((Game)getActivity()).setViewPager(0);
                }
            }
        });

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            textViewPassedCount.setText(Integer.toString(values.gameState.numPassVotes));
            textViewSabotagedCount.setText(Integer.toString(values.gameState.numSabotageVotes));

            if(values.gameState.numSabotageVotes >= values.gameState.numNeededVotes){
                textViewRevealFragment.setText("MISSION SABOTAGED");
                values.gameState.wins[values.gameState.currentMission-1] = 2;
                values.gameState.numSpyWins++;
                colorLayout.setBackgroundColor(getResources().getColor(R.color.colorSpy));
            }
            else{
                textViewRevealFragment.setText("MISSION PASSED");
                values.gameState.wins[values.gameState.currentMission-1] = 1;
                values.gameState.numResistanceWins++;
                colorLayout.setBackgroundColor(getResources().getColor(R.color.colorResistance));
            }

            colorLayout.setVisibility(View.GONE);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textViewTimer.setText("2");
                }
            }, 1000);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textViewTimer.setText("1");
                    Animation fadeInColor = new AlphaAnimation(0, 1);
                    fadeInColor.setInterpolator(new DecelerateInterpolator()); //add this
                    fadeInColor.setDuration(1000);

                    colorLayout.setVisibility(View.VISIBLE);
                    AnimationSet animation = new AnimationSet(false); //change to false
                    animation.addAnimation(fadeInColor);
                    colorLayout.setAnimation(animation);
                }
            }, 2000);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mainRevealLayout.bringToFront();
                    Animation fadeIn = new AlphaAnimation(0, 1);
                    fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
                    fadeIn.setDuration(1000);

                    AnimationSet animation = new AnimationSet(false); //change to false
                    animation.addAnimation(fadeIn);
                    mainRevealLayout.setVisibility(View.VISIBLE);
                    timerLayout.setVisibility(View.GONE);
                    colorLayout.setVisibility(View.GONE);
                    mainRevealLayout.setAnimation(animation);
                }
            }, 3000);

        }
    }
}
