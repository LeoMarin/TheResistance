package com.tony.theresistance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Game extends AppCompatActivity {

    private SectionsStatePagerAdapter sectionsStatePagerAdapter;
    private CustomViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        viewPager = findViewById(R.id.viewPager);

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MissionEndFragment(), "MissionEndFragment");
        adapter.addFragment(new MissionFragment(), "MissionFragment");
        adapter.addFragment(new NextPlayerFragment(), "NextPlayerFragment");
        adapter.addFragment(new VoteFragment(), "VoteFragment");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        viewPager.setCurrentItem(fragmentNumber);
    }
}
