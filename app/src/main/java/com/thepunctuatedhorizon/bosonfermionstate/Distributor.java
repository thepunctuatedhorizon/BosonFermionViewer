package com.thepunctuatedhorizon.bosonfermionstate;

import java.util.ArrayList;
import java.util.List;

public class Distributor {

    public final static int NMAX = 69;

    Distributor(){
        super();
    }

    protected static int[][][] distribute(int totalEnergy, int totalParticlesForThisSystem,
                                          int maxNumberOfEnergyLevels, int typeOfParticle)
            throws NotValidParticleException, NotAValidEnergyLevel {

        List<int[][]> pathsOverall = new ArrayList<int[][]>();

        double combos;
        try {
            combos = numberOfCombinationsPossible(totalEnergy, totalParticlesForThisSystem, maxNumberOfEnergyLevels, typeOfParticle);
        } catch (BadStateForCombinationsException e) {
            e.printStackTrace();
            return new int[][][] {{{0}}};
        } catch (TooLargeException e) {
            e.printStackTrace();
            return new int[][][] {{{0}}};
        }


        recursive(pathsOverall, combos, totalEnergy, totalParticlesForThisSystem);

        int[][][] bad = new int[][][] {{{0}}}; //(int[][][]) pathsOverall.toArray();

        return bad;
    }

    protected static void recursive(List<int[][]> pathsOverall, double combos, int totalEnergy, int totalParticlesForThisSystem) {
        // TODO Auto-generated method stub

    }

    protected static double numberOfCombinationsPossible(int totalEnergy, int totalParticlesForThisSystem,
                                                         int maxNumberOfEnergyLevels, int typeOfParticle)
                                                        throws NotValidParticleException, TooLargeException,
                                                        BadStateForCombinationsException, NotAValidEnergyLevel {
        double combs = 0;

        if (totalParticlesForThisSystem < totalEnergy) {throw new NotAValidEnergyLevel("E > totParticles, not supported!");}
        if (maxNumberOfEnergyLevels < totalEnergy) { throw new NotAValidEnergyLevel("Creating this array would result in the inability to show all possibilities");}

        int mN = Math.min(totalEnergy, totalParticlesForThisSystem);

        switch (typeOfParticle){
            case 0:{
                try {
                    for (int affectedP = 1; affectedP < mN; affectedP++) {
                        int N = affectedP;
                        int r = 0;
                        combs += nCr(N, r);
                    }
                } catch (BadCombinationsException e) {
                    combs = -1;
                    e.printStackTrace();
                    break;
                }
                break;
            }
            case 1: {
                combs = 2;
                break;
            }
            default:{
                throw new NotValidParticleException("Cannot have other type of particle than Fermion or Boson");
            }
        }
        if (combs == 0) {
            throw new BadStateForCombinationsException();
        }
        return combs;
    }

    protected static double nCr(int N, int r) throws BadCombinationsException, TooLargeException {
        if (r < 0 || N < 0) {throw new BadCombinationsException("Bad Input");}
        if (N < r) {throw new BadCombinationsException("N<r exception");}
        if (N > NMAX && r > NMAX/3 && r < (2 * NMAX / 3) ) {throw new TooLargeException("N > NMAX");}
        if (r == 0 || N == 0 ) { return 1;}
        double comb = 0;
        try {
            double num = factorial(N);
            double denom = factorial(N-r) * factorial(r);
            comb = num / denom;
        } catch (BadFactorialInputException e) {

            e.printStackTrace();
            comb = 0;
        }

        if (comb == 0) {
            throw new BadCombinationsException();
        }

        comb = (double) Math.round(comb);
        return comb;
    }

    private static double factorial(int n) throws BadFactorialInputException {
        if (n > 1) {
            return n * factorial(n-1);
        } else if (n == 1 || n == 0) {
            return 1;
        } else {
            throw new BadFactorialInputException();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
