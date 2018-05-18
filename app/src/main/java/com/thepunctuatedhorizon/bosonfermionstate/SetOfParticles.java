/**
 * 
 */
package com.thepunctuatedhorizon.bosonfermionstate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 209722
 *
 */
public class SetOfParticles {

	
	//Private Vars
	protected List<ParticleState> particles = new ArrayList<ParticleState>();
	protected int maxEnergyLevels;
	protected boolean isValidSetOfParticles = false;
	
	protected int N;
	
	//Default constructor
	SetOfParticles() throws NotValidSetOfParticlesException {
		super();
		try {
			this.N = 10;
			this.maxEnergyLevels = 10;
			this.isValidSetOfParticles = setUpSetOfParticles(10, 10, true, 0);
		} catch (NotValidParticleException e) {e.printStackTrace();}
		
		if(!isValidSetOfParticles) {throw new NotValidSetOfParticlesException("Tried to use default constructor, failure");}
	}
	
	//Proper constructor
	SetOfParticles(int N, boolean inGroundState, int typeOfParticle, int maxLevels) throws NotValidSetOfParticlesException {
		super();
		
		this.N = N;
		this.maxEnergyLevels = maxLevels;
		try {
			this.isValidSetOfParticles = setUpSetOfParticles(this.N, this.maxEnergyLevels, inGroundState, typeOfParticle);
		} 
		catch (NotValidParticleException e) {e.printStackTrace();}
		
		if(!isValidSetOfParticles) {throw new NotValidSetOfParticlesException("Tried to use primary constructor, failure");}
	}
	
	//Sets up the object
	private boolean setUpSetOfParticles(int N, int max, boolean inGroundState, int typeOfParticle) throws NotValidParticleException {
		if (!inGroundState) { throw new NotValidParticleException("default constructor");}
		for(int i = 0; i < N; i++) {
			try {
				ParticleState element = new ParticleState(true, i, typeOfParticle, max);
				this.particles.add(i, element);
			} 
			catch (NotValidParticleException e) { e.printStackTrace(); }
		}
		
		this.isValidSetOfParticles = true;
		for (ParticleState s : this.particles) {
			if(!s.getValidState()) {
				isValidSetOfParticles = false;
				break;
			}
		}
		return this.isValidSetOfParticles;
	}
	
	//Accessors and setters
	@Override
	public String toString() {
		String retStr = "";
		for(ParticleState el : this.particles) {
			retStr += el.toString() + "\n";
		}
		return retStr;
	}
	
	public boolean getParticlesValidState() {
		
		boolean isSetValid = true;
		for (ParticleState s : particles) {
			if (!s.getValidState()) {
				isSetValid = false;
				break;
			}
		}
		
		this.isValidSetOfParticles  = isSetValid;
		
		return this.isValidSetOfParticles;
	}
	
	public ParticleState getParticle(int i) {
		return particles.get(i);
	}

	public void setParticleState(int i, ParticleState particle) {
		this.particles.set(i, particle);
	}
	
	public List<ParticleState> getParticles() {
		return particles;
	}

	public void setParticles(List<ParticleState> particles) {
		this.particles = particles;
	}
	
	
}
