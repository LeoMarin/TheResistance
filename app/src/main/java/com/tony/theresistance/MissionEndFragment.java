package com.tony.theresistance;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
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
                Log.e("mission", "start frg");
                ((Game)getActivity()).setViewPager(1);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();/*
        textViewMissionNumber.setText("Mission "+ (++values.gameState.currentMission));
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
        }*/
    }
}
