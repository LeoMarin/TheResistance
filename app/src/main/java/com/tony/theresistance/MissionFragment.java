package com.tony.theresistance;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MissionFragment extends Fragment {

    private Button buttonLockIn;
    private Button buttonYes;
    private Button buttonNo;
    private LinearLayout linearLayout;
    private View overlay;
    private LinearLayout majorityVote;

    private TextView textViewMission;
    private TextView textViewMissionLeader;
    private TextView textViewHint;

    private Values values;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mission_fragment, container, false);

        buttonLockIn = view.findViewById(R.id.buttonLockIn);
        buttonYes = view.findViewById(R.id.buttonYes);
        buttonNo = view.findViewById(R.id.buttonNo);

        overlay = view.findViewById(R.id.overlay);
        linearLayout = view.findViewById(R.id.selectPlayersLayout);
        textViewMission = view.findViewById(R.id.textViewMission);
        textViewMissionLeader = view.findViewById(R.id.textViewMissionLeader);
        textViewHint = view.findViewById(R.id.textViewHint);
        majorityVote = view.findViewById(R.id.majorityVote);


        values = Values.getInstance();
        buttonLockIn.setEnabled(false);

        for(int i=0; i<10; i++){
            if(i>values.gameState.numPlayers-1)
                linearLayout.getChildAt(i).setVisibility(View.GONE);
            else {
                ((CheckBox) linearLayout.getChildAt(i)).setText(values.playerList.get(i).getName());
                linearLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int playerIndex = Integer.parseInt(view.getTag().toString());
                        boolean checked = ((CheckBox) view).isChecked();
                        if (checked) {
                            if (values.gameState.numNeededPlayers > values.gameState.selectedPlayers.size())
                                values.gameState.selectedPlayers.add(values.playerList.get(playerIndex));
                            else
                                ((CheckBox) view).setChecked(false);
                        } else {
                            values.gameState.selectedPlayers.remove(values.playerList.get(playerIndex));
                        }

                        if(values.gameState.numNeededPlayers == values.gameState.selectedPlayers.size())
                            buttonLockIn.setEnabled(true);
                        else
                            buttonLockIn.setEnabled(false);

                    }
                });
            }
        }

        buttonLockIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=1; i<6; i++){
                    if(i <= values.gameState.selectedPlayers.size())
                        ((TextView)majorityVote.getChildAt(i)).setText(values.gameState.selectedPlayers.get(i-1).getName());
                    else
                        majorityVote.getChildAt(i).setVisibility(View.GONE);
                }

                overlay.bringToFront();
                overlay.setVisibility(View.VISIBLE);
                buttonLockIn.setEnabled(false);
            }
        });

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLockIn.setEnabled(true);
                ((Game)getActivity()).setViewPager(2);
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLockIn.setEnabled(true);
                overlay.setVisibility(View.GONE);
                values.gameState.numFailedVotes++;
                values.gameState.currentPlayer++;
                if(values.gameState.currentPlayer == values.gameState.numPlayers)
                    values.gameState.currentPlayer = 0;
                textViewMissionLeader.setText(values.playerList.get(values.gameState.currentPlayer).getName());
                values.gameState.selectedPlayers.clear();
                for(int i=0; i<10; i++)
                    ((CheckBox) linearLayout.getChildAt(i)).setChecked(false);


            }
        });

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            textViewHint.setText(String.format("Select %d players, %d spies needed to sabotage", values.gameState.numNeededPlayers, values.gameState.numNeededVotes));
            textViewMission.setText("Mission " + (values.gameState.currentMission));
            textViewMissionLeader.setText(values.playerList.get(values.gameState.currentPlayer).getName());


            for(int i=0; i<10; i++)
                ((CheckBox) linearLayout.getChildAt(i)).setChecked(false);
            buttonLockIn.setEnabled(false);
        }
    }
}
