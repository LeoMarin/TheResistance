package com.tony.theresistance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class VoteFragment extends Fragment {

    private Button buttonPass;
    private Button buttonSabotage;

    private ImageView imageViewLeftButton;
    private ImageView imageViewRightButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vote_fragment, container, false);

        buttonPass = view.findViewById(R.id.buttonPass);
        buttonSabotage = view.findViewById(R.id.buttonSabotage);

        imageViewLeftButton = view.findViewById(R.id.imageViewLeftButton);
        imageViewRightButton = view.findViewById(R.id.imageViewRightButton);

        imageViewLeftButton.bringToFront();
        imageViewRightButton.bringToFront();

        buttonPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Game)getActivity()).setViewPager(0);
            }
        });
        buttonSabotage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Game)getActivity()).setViewPager(0);
            }
        });

        return view;
    }
}
