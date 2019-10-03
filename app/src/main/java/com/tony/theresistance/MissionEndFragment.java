package com.tony.theresistance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MissionEndFragment extends Fragment {

    private Button buttonBeginMission;
    private Values values;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mission_end_fragment, container, false);

        buttonBeginMission = view.findViewById(R.id.buttonBeginMission);

        buttonBeginMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Game)getActivity()).setViewPager(1);
            }
        });

        return view;
    }
}
