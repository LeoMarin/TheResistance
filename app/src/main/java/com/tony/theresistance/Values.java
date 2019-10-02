package com.tony.theresistance;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Values extends Application {

    // Singleton instance
    private static Values sValues = null;

    public Values(){
        playerList = new ArrayList<>();
        selectedRoles = new ArrayList<>();
    }

    public static Values getInstance() {
        if(sValues == null)
            return new Values();
        return sValues;
    }

    public static final String PLAYER_NUMBER_EXTRA = "LAYER_NUMBER_EXTRA";

    public enum Role{
        SPY,
        RESISTANCE,
        COMMANDER,
        BODYGUARD,
        FALSE_COMMANDER,
        BLIND_SPY,
        DEEP_SPY,
        INQUISITOR,
        CHAOTIC_SPY
    }

    class Player{
        private String name;
        private Role role;
        private int score;
        private boolean isEvil;

        public void setName(String name){
            this.name = name;
        }

        public void setRole(Role role){
            this.role = role;
        }

        public void setScore(int score){
            this.score = score;
        }

        public void setIsEvil(boolean isEvil){
            this.isEvil = isEvil;
        }

        public String getName(){
            return this.name;
        }

        public Role getRole(){
            return this.role;
        }

        public int getScore(){
            return this.score;
        }

        public boolean getIsEvil(){
            return this.isEvil;
        }
    }

    public List<Player> playerList;

    public List<Role> selectedRoles;

    public void generatePlayers(int playerNumber){
        Integer[] arr = new Integer[playerNumber];
        for (int i = 0; i < playerNumber; i++) {
            arr[i] = i;
        }
        Collections.shuffle(Arrays.asList(arr));


    }
}
