package com.thepunctuatedhorizon.bosonfermionstate;

//*******************************************************************
// Simple State space calculator
//*******************************************************************

// one class needs to have a main() method
public class State
{
    //Private variables
    private int N = 10; //Number of paricles
    private int maxLevels;
    private int q; //Number of excited particles
    private int excitedPraticlesMax = 10; //Max number of excited particles
    private int energyInput = 0;
    private String[][] states;
    private int typeOfParticle = 0;  // 0 = Boson, 1 = fermion

    //The initiator.
    State(int energyIn, int typeParticle, int Num, int maxLevel, int[] whichParticles) {
        super();
        N = Num;
        maxLevels = maxLevel;
        if (typeParticle != 0) {
            return;
        }
        typeOfParticle = typeParticle;

        if (N < excitedPraticlesMax) {
            System.out.println("Maximum excited particles exceeded!");
            return;
        }
        states = new String[][]{{""}};
        int whichParticle = 0;
        energyInput = energyIn;
        groundState();
        //perturb();
    }

    


    private void perturb(int whichParticle, int byWhatEnergy){
        if (whichParticle < 0 || whichParticle > 10 || byWhatEnergy > 100)
        {
            System.out.println("BAD CALL to purturb.");
            return;
        }
        String type = (typeOfParticle ==0) ? "B" : "F";
        for (int i = 0; i < maxLevels; i++){
            if (states[i][whichParticle].equals("_")){
                continue;
            }
            if (states[i][whichParticle].equals(type)){
                //TODO: Fix this error checking and put in proper place.
                //if (byWhatEnergy > (100-i)){return;}
                //if (i-byWhatEnergy > 98){return;}

                changeAtTo(i,whichParticle,"_");
                changeAtTo(i-byWhatEnergy,whichParticle, type);
            }
        }
    }












    private void groundState(){
        String[][] state = new String[maxLevels][];
        for(int i=0; i < maxLevels; i++){
            String[] blank = new String[N];
            for(int j=0; j < N; j++){
                blank[j] =  "_";
            }
            state[i] = blank;
        }
        String[] blank2 = new String[N];
        for (int j=0; j < N; j++){
            blank2[j] = (typeOfParticle == 0) ? "B" : "F";
        }

        state[maxLevels-1] = blank2;
        states = state;
    }

    private void changeAtTo(int a, int b, String to){
        System.out.println("Run");
        states[a][b] = to;
    }

    @Override
    public String toString(){
        String returnString = "";
        for (int i = 0; i < states.length; i++){
            for (int j = 0; j < states[i].length; j++){
                returnString += states[i][j];
            }
            returnString += "\n";
        }
        return returnString;
    }
}