package com.thepunctuatedhorizon.bosonfermionstate;

public class Runner {

	public static void main(String[] args)  {
		try {
		ParticleState state1 = new ParticleState(10, 2, 0, 0);
		
		SetOfParticles particles = new SetOfParticles();
		
		System.out.println(state1.getEnergyLevel());
		System.out.println(state1.getFullNameTypeOfParticle());
		System.out.println(state1.getIntTypeOfParticle());
		System.out.println(state1.getMaxLevels());
		System.out.println(state1.getShortNameTypeOfParticle());
		System.out.println(state1.getValidState());
		System.out.println(state1.getWhichParticle());
		
		
		System.out.println(state1);
		
		System.out.println(particles);
		} catch (NotValidParticleException e) { e.printStackTrace();}
		catch (NotValidSetOfParticlesException e) {e.printStackTrace();}
	}

}
