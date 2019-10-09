package com.tony.theresistance;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Values extends Application {

    // Singleton instance
    private static Values sValues;

    public Values(){
        playerList = new ArrayList<>();
        selectedRoles = new ArrayList<>();
        playableRoles = new ArrayList<>();
        gameState = new GameState();
    }

    public static Values getInstance() {
        if(sValues == null)
            sValues = new Values();
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

    class GameState{
        public int numPlayers;
        public int numResistance;
        public int numSpies;
        public int currentMission;
        public int numSpyWins;
        public int numResistanceWins;
        public int currentPlayer;
        public int numNeededVotes;
        public int numNeededPlayers;
        public int numFailedVotes;
        public int numVotes;
        public int numPassVotes;
        public int numSabotageVotes;
        public int[] wins; // 0 - not played, 1 - resistance win, 2 - spy win
        public List<Player> selectedPlayers;
        public Player currentVoter;
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
    public List<Role> playableRoles;
    public GameState gameState;

    public void restGameState() {
        gameState.numVotes = 0;
        gameState.numPassVotes = 0;
        gameState.numSabotageVotes = 0;
        gameState.numFailedVotes = 0;
        gameState.numNeededVotes = 1;
        gameState.numNeededPlayers = 3;
        gameState.currentMission = 0;
        gameState.numSpyWins = 0;
        gameState.numResistanceWins = 0;
        gameState.numPlayers = playerList.size();
        gameState.wins = new int[] {0, 0, 0, 0, 0};
        gameState.selectedPlayers = new ArrayList<>();
        gameState.currentPlayer = new Random().nextInt(gameState.numPlayers);
        switch (playerList.size()){
            case 5:
                gameState.numResistance = 3;
                gameState.numSpies = 2;
                break;
            case 6:
                gameState.numResistance = 4;
                gameState.numSpies = 2;
                break;
            case 7:
                gameState.numResistance = 4;
                gameState.numSpies = 3;
                break;
            case 8:
                gameState.numResistance = 5;
                gameState.numSpies = 3;
                break;
            case 9:
                gameState.numResistance = 6;
                gameState.numSpies = 3;
                break;
            case 10:
                gameState.numResistance = 6;
                gameState.numSpies = 4;
                break;
        }
    }

    public void resetVote(){
        gameState.numVotes = 0;
        gameState.numPassVotes = 0;
        gameState.numSabotageVotes = 0;
        gameState.numFailedVotes = 0;
        gameState.selectedPlayers.clear();
    }

    public void generatePlayers(int playerNumber){
        playerList.clear();

        for(int i=0; i<playerNumber; i++){
            Player player = new Player();
            playerList.add(player);
        }

        generateRoles();
    }

    public void generateRoles(){

        playableRoles.clear();

        int playerNumber = playerList.size();
        int spyNumber = 0;
        int resistanceNumber = 0;

        switch (playerNumber){
            case 5:
                resistanceNumber = 3;
                spyNumber = 2;
                break;
            case 6:
                resistanceNumber = 4;
                spyNumber = 2;
                break;
            case 7:
                resistanceNumber = 4;
                spyNumber = 3;
                break;
            case 8:
                resistanceNumber = 5;
                spyNumber = 3;
                break;
            case 9:
                resistanceNumber = 6;
                spyNumber = 3;
                break;
            case 10:
                resistanceNumber = 6;
                spyNumber = 4;
                break;
        }

        for(Role role : selectedRoles){
            if(role == Role.COMMANDER || role == Role.BODYGUARD)
                resistanceNumber--;
            else
                spyNumber--;
            if(role != Role.BLIND_SPY && role != Role.CHAOTIC_SPY && role != Role.DEEP_SPY)
                playableRoles.add(role);
        }


        List<Role> spies = new ArrayList<>();

        if(selectedRoles.contains(Role.BLIND_SPY))
            spies.add(Role.BLIND_SPY);
        if(selectedRoles.contains(Role.DEEP_SPY))
            spies.add(Role.DEEP_SPY);
        if(selectedRoles.contains(Role.CHAOTIC_SPY))
            spies.add(Role.CHAOTIC_SPY);

        if(spies.size() != 0){
            spyNumber += spies.size()-1;
            int rnd = new Random().nextInt(spies.size());
            playableRoles.add(spies.get(rnd));
        }

        while (resistanceNumber > 0){
            playableRoles.add(Role.RESISTANCE);
            resistanceNumber--;
        }

        while (spyNumber > 0){
            playableRoles.add(Role.SPY);
            spyNumber--;
        }


        Integer[] arr = new Integer[playerNumber];
        for (int i = 0; i < playerNumber; i++) {
            arr[i] = i;
        }
        Collections.shuffle(Arrays.asList(arr));

        for(int i=0; i<playerNumber; i++){
            playerList.get(arr[i]).setRole(playableRoles.get(i));
        }
        for(Player player : playerList){
            if(player.role == Role.RESISTANCE || player.role == Role.COMMANDER)
                player.setIsEvil(false);
            else
                player.setIsEvil(true);
        }
    }

}


