package com.thepunctuatedhorizon.bosonfermionstate;

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
            Distributor distributor = new Distributor();

            try {
                System.out.println(distributor.nCr(70,1));
            } catch (BadCombinationsException e) {
                e.printStackTrace();
                return;
            } catch (TooLargeException e ) {
                e.printStackTrace();
                return;
            }

            int[][][] iPaths = distributor.distribute(totalEnergy, totalParticlesForThisSystem, MaxNumberOfEnergyLevels, particleType);

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
}
