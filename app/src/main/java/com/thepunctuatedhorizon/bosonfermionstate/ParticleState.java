/**
 * 
 */
package com.thepunctuatedhorizon.bosonfermionstate;

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
	private double energyIneV;
	
	private int whichParticle;
	private int typeOfParticle;  // 0=boson, 1 = fermion
	private boolean validState= false;
	
	protected static final int MAX_LEVELS = 10;
	
	@SuppressWarnings("unused")
	private final String TAG = "Particle State";
	
	/**
	 * 
	 */
	public ParticleState() {
		this.maxParticleLevels = 1;
		this.energyLevel = 0;
		this.whichParticle = 0;
		this.typeOfParticle = 0;
		this.validState = false;
	}
	
	public ParticleState(int maxL, int energyL, int whichP, int typeOfP) throws NotValidParticleException {
		
		if (maxL > MAX_LEVELS) { throw new NotValidParticleException("Maximum levels exceeded."); }
		if (maxL < 1) { throw new NotValidParticleException("Minimum levels breached");}
		this.maxParticleLevels = maxL;
		
		if (energyL > MAX_LEVELS) { throw new NotValidParticleException("Maximum energy levels exceeded."); }
		if (energyL > this.maxParticleLevels) { throw new NotValidParticleException("Maximum situation energy levels exceeded."); }
		if (energyL < 0) { throw new NotValidParticleException("Minimum levels breached");}
		this.energyLevel = energyL;
		this.energyIneV = ((double) this.energyLevel)/2;
		
		this.whichParticle = whichP;
		
		if (typeOfP > 1) { throw new NotValidParticleException("Wrong Particle Type."); }
		if (typeOfP < 0) { throw new NotValidParticleException("Wrong Particle Type."); }
		this.typeOfParticle = typeOfP;
		validState = true;		
	}
	
	public ParticleState(boolean inGroundState, int i, int typeOfP, int max) throws NotValidParticleException {
		if (!inGroundState) {
			throw new NotValidParticleException("Wasn't created at the ground state!"); 
		}
		if (max < 1) { this.maxParticleLevels = MAX_LEVELS; } else { this.maxParticleLevels = max; }
		
		this.energyLevel = 0;
		this.energyIneV = 0;
		
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
		
		try {
			checkIfValid();
		} 
		catch (NotValidParticleException ex) {
			ex.printStackTrace();
		}
		
		String groundStateOrNot = (this.energyLevel == 0) ? "is in the ground state, 0eV." : "has energy " + this.energyIneV +"eV";
		String aboveGroundOrNot = (this.energyLevel == 0) ? "" : " above the ground state.";
		String validOrNot = (this.validState) ? " valid" : "n invalid";
		String retStr = "";
		try {
			retStr = "This " + getFullNameTypeOfParticle() + " is the " + 
								getWhichParticle() + "th particle in the simulation. This particle " + groundStateOrNot +
								aboveGroundOrNot + " This particle is in a" +
								validOrNot + " particle.";
		} catch (NotValidParticleException e) {
			e.printStackTrace();
		}
		
		return retStr;
	}
	
	private void checkIfValid() throws NotValidParticleException {
		if (this.maxParticleLevels > MAX_LEVELS) { 
			validState = false;	
			throw new NotValidParticleException("Maximum levels exceeded."); 
		}
		if (this.maxParticleLevels < 1) { 
			validState = false;	
			throw new NotValidParticleException("Minimum levels breached");
		}
		 
		
		if (this.energyLevel > MAX_LEVELS) { 
			validState = false;	
			throw new NotValidParticleException("Maximum energy levels exceeded."); 
		}
		if (this.energyLevel > this.maxParticleLevels) { 
			
			validState = false;	
			throw new NotValidParticleException("Maximum situation energy levels exceeded."); 
		}
		if (this.energyLevel < 0) { 
			validState = false;	
			throw new NotValidParticleException("Minimum levels breached");
		}
		
		
		if (this.typeOfParticle > 1) { 
			validState = false;	
			throw new NotValidParticleException("Wrong Particle Type."); 
		}
		if (this.typeOfParticle < 0) { 
			validState = false;	
			throw new NotValidParticleException("Wrong Particle Type."); 
		}
		
		validState = true;		
	}
	
	public void perturb(int energyAddedToMe) throws NotAValidEnergyLevel, NotValidParticleException {
		
		checkIfValid();
		
		if (!validState) { 
			throw new NotValidParticleException("This particle was not valid, cannot perturb"); 
		}
		
		int newEnergyLvl = energyAddedToMe + energyLevel;
		if (maxParticleLevels < (newEnergyLvl)) {
			this.validState = false;
			throw new NotAValidEnergyLevel("This particle was given more energy (" + ((double)(energyAddedToMe + energyLevel))/2 + "eV) than possible (" + ((double)maxParticleLevels)/2 + "eV).");
		}
		
		if (newEnergyLvl < 0) {
			this.validState = false;
			throw new NotAValidEnergyLevel("This particle was given less energy (" + ((double)(energyAddedToMe + energyLevel))/2 + "eV) than possible (" + 0 + "eV - the ground state).");
		}
		
		if (newEnergyLvl == energyLevel) {
			//TODO: What to do when unchanged? What else is going on?
		}
		
		this.energyLevel = newEnergyLvl;
		this.energyIneV = ((double) this.energyLevel)/2;
		
	}
}
