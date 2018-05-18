package com.thepunctuatedhorizon.bosonfermionstate;

/**
 * @author 209722
 *
 */
public class SystemOfParticles extends SetOfParticles {

	/**
	 * Variables
	 */
	SystemPath path;
	
	/**
	 * @throws NotValidSetOfParticlesException
	 */
	public SystemOfParticles() throws NotValidSetOfParticlesException {
		throw new NotValidSetOfParticlesException("Don't use the default constructor");
		//this.path = new SystemPath();
	}

	/**
	 * @param N
	 * @param inGroundState
	 * @param typeOfParticle
	 * @throws NotValidSetOfParticlesException
	 * @throws NotValidParticleException 
	 * @throws NotAValidEnergyLevel 
	 */
	public SystemOfParticles(boolean inGroundState, int max, int typeOfParticle, SystemPath path) throws NotValidSetOfParticlesException, NotAValidEnergyLevel, NotValidParticleException {
		super(path.getPathTaken().size(), inGroundState, typeOfParticle, (max < ParticleState.MAX_LEVELS) ? max : ParticleState.MAX_LEVELS);
		
		this.N = path.getPathTaken().size();
		
		this.path = path;
		
		perturbTheParticlesToMatchPath();
	}

	private void perturbTheParticlesToMatchPath() throws NotAValidEnergyLevel, NotValidParticleException {
		int index = 0;
		for(ParticleState s : this.getParticles()) {
			s.perturb(path.getPathTaken().get(index));
			index++;
		}
	}

	public String getStateAsString() throws BadPathException {
			testIfPathMatchesParticlesState();
			return this.path.toStateString();
	}

	private void testIfPathMatchesParticlesState() throws BadPathException {
		int[] ints = this.path.getIntPathTaken();
		for(ParticleState s : this.getParticles()) {
			if(!((ints[s.getWhichParticle()]) == s.getWhichParticle() )) {
				throw new BadPathException("The path didn't match the particles");
			}
		}
	}
}
