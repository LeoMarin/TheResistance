package com.tony.theresistance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NextPlayerFragment extends Fragment {

    private Button buttonVote;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.next_player_fragment, container, false);

        buttonVote = view.findViewById(R.id.buttonVote);

        buttonVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Game)getActivity()).setViewPager(3);
            }
        });

        return view;
    }
}
