package com.tony.theresistance;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class VoteFragment extends Fragment {

    private Button buttonPass;
    private Button buttonSabotage;

    private Values values;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vote_fragment, container, false);

        buttonPass = view.findViewById(R.id.buttonPass);
        buttonSabotage = view.findViewById(R.id.buttonSabotage);

        values = Values.getInstance();

        buttonPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values.gameState.numVotes++;
                values.gameState.numPassVotes++;
                if(values.gameState.numVotes >= values.gameState.selectedPlayers.size()){
                    ((Game)getActivity()).setViewPager(4);
                }
                else {
                    ((Game)getActivity()).setViewPager(2);
                }
            }
        });
        buttonSabotage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values.gameState.numVotes++;
                if(values.gameState.currentVoter.getIsEvil())
                    values.gameState.numSabotageVotes++;
                else
                    values.gameState.numPassVotes++;

                if(values.gameState.numVotes >= values.gameState.selectedPlayers.size()){
                    ((Game)getActivity()).setViewPager(4);
                }
                else {
                    ((Game)getActivity()).setViewPager(2);
                }
            }
        });

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if(values.gameState.currentVoter.getIsEvil()){
                Drawable top = getResources().getDrawable(R.drawable.spy_black);
                buttonSabotage.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                buttonSabotage.setText("SABOTAGE");
            }
            else{
                Drawable top = getResources().getDrawable(R.drawable.resistance_black);
                buttonSabotage.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                buttonSabotage.setText("PASS");
            }
        }
    }
}
