package com.tony.theresistance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NextPlayerFragment extends Fragment {

    private Button buttonVote;
    private TextView textViewNextPlayer;

    private Values values;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.next_player_fragment, container, false);

        buttonVote = view.findViewById(R.id.buttonVote);
        textViewNextPlayer = view.findViewById(R.id.textViewNextPlayer);

        values = Values.getInstance();

        buttonVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Game)getActivity()).setViewPager(3);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        textViewNextPlayer.setText(values.gameState.selectedPlayers.get(values.gameState.numVotes).getName());
    }
}
