package com.thepunctuatedhorizon.bosonfermionstate;

import java.util.ArrayList;
import java.util.List;

public class Runner {

	public static void main(String[] args)  {
		try {

//			SetOfParticles particles = new SetOfParticles();
//
//			System.out.println(particles);
//		
//			System.out.println("" + particles.getParticlesValidState());
//			
//			particles.getParticle(0).perturb(1);
//			particles.getParticle(1).perturb(2);
//			particles.getParticle(2).perturb(10);
//			//particles.getParticle(3).perturb(11);
//			
//			particles.getParticle(4).perturb(1);
//			particles.getParticle(4).perturb(-1);
//			System.out.println(particles);
			
			int totalParticlesForThisSystem = 3;
			int totalEnergy = 3;
			int MaxNumberOfEnergyLevels = 10; // We MIGHT need to not use this!
			int particleType = 0; 
			
			try {
				System.out.println(combinations(70,1));
			} catch (BadCombinationsException e) {
				e.printStackTrace();
				return;
			}
			
			int[][][] iPaths = distribute(totalEnergy, totalParticlesForThisSystem, MaxNumberOfEnergyLevels, particleType);
			
			int[][] iPath = iPaths[0];
			SystemPath path = new SystemPath(iPath);
			
			SystemOfParticles systemOfParticles = new SystemOfParticles(true, MaxNumberOfEnergyLevels, particleType, path);
			
			System.out.println(systemOfParticles.getStateAsString());
			
			System.out.println(path.getPathTaken().toString());
			
		} 
		catch (NotValidSetOfParticlesException e) {e.printStackTrace(); }
		catch (NotAValidEnergyLevel s) {s.printStackTrace(); }
		catch (NotValidParticleException e) { e.printStackTrace(); } 
		catch (BadPathException e) { e.printStackTrace(); }
	}

	
	
	
	private static int[][][] distribute(int totalEnergy, int totalParticlesForThisSystem, int maxNumberOfEnergyLevels, int typeOfParticle) throws NotValidParticleException {
		List<int[][]> pathsOverall = new ArrayList<int[][]>();
		
		double combos;
		try {
			combos = numberOfCombinationsPossible(totalEnergy, totalParticlesForThisSystem, maxNumberOfEnergyLevels, typeOfParticle);
		} catch (BadStateForCombinationsException e) {
			e.printStackTrace();
			return new int[][][] {{{0}}};
		}
		
		
		recursive(pathsOverall, combos, totalEnergy, totalParticlesForThisSystem);
		
		int[][][] bad = new int[][][] {{{0}}}; //(int[][][]) pathsOverall.toArray();
		
		return bad;
	}

	private static void recursive(List<int[][]> pathsOverall, double combos, int totalEnergy,
			int totalParticlesForThisSystem) {
		// TODO Auto-generated method stub
		
	}

	private static double numberOfCombinationsPossible(int totalEnergy, int totalParticlesForThisSystem, int maxNumberOfEnergyLevels, int typeOfParticle) throws NotValidParticleException, BadStateForCombinationsException {
		double combs = 0;
		
		switch (typeOfParticle){
			case 0:{
				try {
					combs = combinations(totalParticlesForThisSystem, totalEnergy);
				} catch (BadCombinationsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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

	private static double combinations(int N, int r) throws BadCombinationsException {
		if (r < 0 || N < 0) {throw new BadCombinationsException("Bad Input");}
		if (N < r) {throw new BadCombinationsException("N<r exception");}
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
}
