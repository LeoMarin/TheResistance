package com.tony.theresistance;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class RevealFragment extends Fragment {

    private Values values;

    private TextView textViewRevealFragment;
    private TextView textViewPassedCount;
    private TextView textViewSabotagedCount;

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


        buttonRevealFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(values.gameState.numSpyWins == 3 || values.gameState.numResistanceWins == 3){
                    //TODO: create endagme activity
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
            }
            else{
                textViewRevealFragment.setText("MISSION PASSED");
                values.gameState.wins[values.gameState.currentMission-1] = 1;
                values.gameState.numResistanceWins++;
            }
        }
    }
}