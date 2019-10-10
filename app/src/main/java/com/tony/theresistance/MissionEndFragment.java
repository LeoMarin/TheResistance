package com.tony.theresistance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Arrays;

public class MissionEndFragment extends Fragment {

    private Values values;

    private TextView textViewMissionNumber;
    private TextView textViewNextMissionLeader;

    private LinearLayout gameScoreLayout;

    private Button buttonBeginMission;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mission_end_fragment, container, false);

        values = Values.getInstance();


        buttonBeginMission = view.findViewById(R.id.buttonBeginMission);
        textViewMissionNumber = view.findViewById(R.id.textViewMissionNumber);
        textViewNextMissionLeader = view.findViewById(R.id.textViewNextMissionLeader);
        gameScoreLayout = view.findViewById(R.id.gameScoreLayout);


        buttonBeginMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Game)getActivity()).setViewPager(1);
            }
        });

        textViewMissionNumber.setText("Mission " + (++values.gameState.currentMission));
        values.gameState.currentPlayer = (values.gameState.currentPlayer >= values.playerList.size()-1) ? 0 : values.gameState.currentPlayer+1;
        textViewNextMissionLeader.setText(values.playerList.get(values.gameState.currentPlayer).getName());
        setNeededPlayers();

        for(int i=0; i<5; i++){
            switch (values.gameState.wins[i]){
                case 0:
                    ((ImageView)gameScoreLayout.getChildAt(i)).setImageResource(R.drawable.white_circle);
                    break;
                case 1:
                    ((ImageView)gameScoreLayout.getChildAt(i)).setImageResource(R.drawable.blue_circle);
                    break;
                case 2:
                    ((ImageView)gameScoreLayout.getChildAt(i)).setImageResource(R.drawable.red_circle);
                    break;
            }
        }
        setNeededPlayers();


        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
/*
            String currMission = "Mission " + (++values.gameState.currentMission);
            textViewMissionNumber.setText(currMission);
            textViewNextMissionLeader.setText(values.playerList.get(values.gameState.currentPlayer).getName());
            for(int i=0; i<5; i++){
                switch (values.gameState.wins[i]){
                    case 0:
                        ((ImageView)gameScoreLayout.getChildAt(i)).setImageResource(R.drawable.white_circle);
                        break;
                    case 1:
                        ((ImageView)gameScoreLayout.getChildAt(i)).setImageResource(R.drawable.blue_circle);
                        break;
                    case 2:
                        ((ImageView)gameScoreLayout.getChildAt(i)).setImageResource(R.drawable.red_circle);
                        break;
                }
            }
            setNeededPlayers();*/
        }
    }

    private void setNeededPlayers(){
        switch (values.gameState.currentMission){
            case 1:
                values.gameState.numNeededPlayers = (values.gameState.numPlayers > 7) ? 3 : 2;
                values.gameState.numNeededVotes = 1;
                break;
            case 2:
                values.gameState.numNeededPlayers = (values.gameState.numPlayers > 7) ? 4 : 3;
                values.gameState.numNeededVotes = 1;
                break;
            case 3:
                if(values.gameState.numPlayers == 5)
                    values.gameState.numNeededPlayers = 2;
                else if(values.gameState.numPlayers == 7)
                    values.gameState.numNeededPlayers = 3;
                else
                    values.gameState.numNeededPlayers = 4;
                values.gameState.numNeededVotes = 1;
                break;
            case 4:
                if(values.gameState.numPlayers < 7) {
                    values.gameState.numNeededPlayers = 3;
                    values.gameState.numNeededVotes = 1;
                } else if(values.gameState.numPlayers == 7) {
                    values.gameState.numNeededPlayers = 4;
                    values.gameState.numNeededVotes = 2;
                } else{
                    values.gameState.numNeededPlayers = 5;
                    values.gameState.numNeededVotes = 2;
                }
                break;
            case 5:
                if(values.gameState.numPlayers == 5)
                    values.gameState.numNeededPlayers = 3;
                else if(values.gameState.numPlayers < 8)
                    values.gameState.numNeededPlayers = 4;
                else
                    values.gameState.numNeededPlayers = 5;
                values.gameState.numNeededVotes = 1;
                break;
        }
    }
}
