package com.tony.theresistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Button buttonAssassin;
    private Button buttonDefector;
    private Button buttonTrapper;
    private Button buttonReverser;
    private Button buttonHunter;
    private Button buttonStart;
    private Button buttonExit;

    private LinearLayout linearLayout;
    private Values values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAssassin = findViewById(R.id.buttonAssassin);
        buttonDefector = findViewById(R.id.buttonDefector);
        buttonTrapper = findViewById(R.id.buttonTrapper);
        buttonReverser = findViewById(R.id.buttonReverser);
        buttonHunter = findViewById(R.id.buttonHunter);
        buttonStart = findViewById(R.id.buttonStart);
        buttonExit = findViewById(R.id.buttonExit);

        linearLayout = findViewById(R.id.linearLayout);

        buttonAssassin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAssassin.setEnabled(false);
                linearLayout.bringToFront();
                linearLayout.setVisibility(View.VISIBLE);
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlayersActivity();
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAssassin.setEnabled(true);
                linearLayout.setVisibility(View.GONE);
            }
        });

        values = Values.getInstance();

    }

    public void openPlayersActivity(){
        Intent intent = new Intent(getBaseContext(), PlayerNumber.class);
        startActivity(intent);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkBoxCommander:
                if (checked)
                    values.selectedRoles.add(Values.Role.COMMANDER);
                else
                    values.selectedRoles.remove(Values.Role.COMMANDER);
                break;
            case R.id.checkBoxBodyguard:
                if (checked){
                    values.selectedRoles.add(Values.Role.BODYGUARD);
                    values.selectedRoles.add(Values.Role.FALSE_COMMANDER);
                }
                else{
                    values.selectedRoles.remove(Values.Role.BODYGUARD);
                    values.selectedRoles.remove(Values.Role.FALSE_COMMANDER);
                }
                break;/*
            case R.id.checkBoxInquisitor:
                if (checked)
                    values.selectedRoles.add(Values.Role.INQUISITOR);
                else
                    values.selectedRoles.remove(Values.Role.INQUISITOR);
                break;*/
            case R.id.checkBoxBlind:
                if (checked)
                    values.selectedRoles.add(Values.Role.BLIND_SPY);
                else
                    values.selectedRoles.remove(Values.Role.BLIND_SPY);
                break;
            case R.id.checkBoxDeep:
                if (checked)
                    values.selectedRoles.add(Values.Role.DEEP_SPY);
                else
                    values.selectedRoles.remove(Values.Role.DEEP_SPY);
                break;
            case R.id.checkBoxChaoticSpy:
                if (checked)
                    values.selectedRoles.add(Values.Role.CHAOTIC_SPY);
                else
                    values.selectedRoles.remove(Values.Role.CHAOTIC_SPY);
                break;
            default:
                break;
        }
    }
}
