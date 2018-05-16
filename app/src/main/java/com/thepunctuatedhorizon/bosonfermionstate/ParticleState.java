/**
 * 
 */
package com.thepunctuatedhorizon.bosonfermionstate;

import java.util.logging.*;

/**
 * @author 209722
 *
 */
public class ParticleState {

	/**
	 * Variables
	 */
	private int maxParticleLevels;
	private int energyLevel;
	private int whichParticle;
	private int typeOfParticle;
	private boolean validState= false;
	
	private final int MAX_LEVELS = 10;
	
	private final String TAG = "Particle State";
	
	/**
	 * 
	 */
	public ParticleState() {
		this.maxParticleLevels = 1;
		this.energyLevel = 0;
		this.whichParticle = 0;
		this.typeOfParticle = 0;
		validState = false;
	}
	
	public ParticleState(int maxL, int energyL, int whichP, int typeOfP) throws NotValidParticleException {
		
		if (maxL > MAX_LEVELS) { throw new NotValidParticleException("Maximum levels exceeded."); }
		if (maxL < 1) { throw new NotValidParticleException("Minimum levels breached");}
		this.maxParticleLevels = maxL;
		
		if (energyL > MAX_LEVELS) { throw new NotValidParticleException("Maximum energy levels exceeded."); }
		if (energyL > this.maxParticleLevels) { throw new NotValidParticleException("Maximum situation energy levels exceeded."); }
		if (energyL < 0) { throw new NotValidParticleException("Minimum levels breached");}
		this.energyLevel = energyL;
		
		if (whichP > 1) { throw new NotValidParticleException("Wrong particle Number."); }
		if (whichP < 0) { throw new NotValidParticleException("Wrong particle Number."); }
		this.whichParticle = whichP;
		
		if (typeOfP > 1) { throw new NotValidParticleException("Wrong Particle Type."); }
		if (typeOfP < 0) { throw new NotValidParticleException("Wrong Particle Type."); }
		this.typeOfParticle = typeOfP;
		validState = true;		
	}
	
public ParticleState(boolean inGroundState, int i, int typeOfP) throws NotValidParticleException {
		
		this.maxParticleLevels = MAX_LEVELS;
		
		this.energyLevel = 0;
		
		this.whichParticle = i;
		
		if (typeOfP > 1) { throw new NotValidParticleException("Wrong Particle Type."); }
		if (typeOfP < 0) { throw new NotValidParticleException("Wrong Particle Type."); }
		this.typeOfParticle = typeOfP;
		validState = true;		
	}
	
	public void setValidState(boolean bool) {
		validState = bool;
	}

	public boolean getValidState() {
		return this.validState;
	}
	
	public int getMaxLevels() {
		return this.maxParticleLevels;
	}
	
	public int getEnergyLevel() {
		return this.energyLevel;
	}
	
	public int getWhichParticle() {
		return this.whichParticle;
	}
	
	public int getIntTypeOfParticle() {
		return this.typeOfParticle;
	}
	
	public String getShortNameTypeOfParticle() throws NotValidParticleException {
		if (this.typeOfParticle == 0)
		{
			return "B";
		}
		if (this.typeOfParticle == 1) {
			return "F";
		}
		else
		{
			throw new NotValidParticleException("Type of Particle number not standard.");
		}
		
	}
	
	public String getFullNameTypeOfParticle() throws NotValidParticleException {
		if (this.typeOfParticle == 0)
		{
			return "Boson";
		}
		if (this.typeOfParticle == 1) {
			return "Fermion";
		}
		else
		{
			throw new NotValidParticleException("Type of Particle not standard.");
		}
	}
	
	@Override
	public String toString() {
		String validOrNot = (this.validState) ? " valid" : " invalid";
		String retStr = "";
		try {
			retStr = "This " + getFullNameTypeOfParticle() + " is the " + 
								getWhichParticle() + "th particle in the simulation. This particle has energy " +
								((double)getEnergyLevel()/2) + "eV above the ground state." + "This particle is in a(n)" +
								validOrNot + " particle.";
		} catch (NotValidParticleException e) {
			e.printStackTrace();
			
		}
		return retStr;
	}
}
