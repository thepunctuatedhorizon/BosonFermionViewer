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

	private List<ParticleState> particles = new ArrayList<ParticleState>();
	
	private boolean isValidSetOfParticles = false;
	
	SetOfParticles() throws NotValidSetOfParticlesException {
		super();
		try {
			this.isValidSetOfParticles = setUpSetOfParticles(10, true, 0);
			System.out.println(""+ this.isValidSetOfParticles);
		} catch (NotValidParticleException e) {e.printStackTrace();}
		
		if(!isValidSetOfParticles) {throw new NotValidSetOfParticlesException("Tried to use default constructor, failure");}
	}
	
	SetOfParticles(int N, boolean inGroundState, int typeOfParticle) throws NotValidSetOfParticlesException {
		super();
		try {
			this.isValidSetOfParticles = setUpSetOfParticles(N,inGroundState, typeOfParticle);
		} catch (NotValidParticleException e) {e.printStackTrace();}
		if(!isValidSetOfParticles) {throw new NotValidSetOfParticlesException("Tried to use primary constructor, failure");}
	}
	
	private boolean setUpSetOfParticles(int N, boolean inGroundState, int typeOfParticle) throws NotValidParticleException {
		if (!inGroundState) { throw new NotValidParticleException("default constructor");}
		for(int i = 0; i < N; i++) {
			try {
				ParticleState element = new ParticleState(true, i, typeOfParticle);
				System.out.println(element.toString());
				this.particles.add(i, element);
			} catch (NotValidParticleException e) { e.printStackTrace();}
		}
		return isValidSetOfParticles;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		String retStr = "";
		for(ParticleState el : this.particles) {
			retStr += el.toString() + "\n";
		}
		return retStr;
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
