package com.thepunctuatedhorizon.bosonfermionstate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.txtBox);

        String str = "";

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
                str = "" + distributor.nCr(69,35);
            } catch (BadCombinationsException e) {
                e.printStackTrace();
                return;
            }

            int[][][] iPaths = distributor.distribute(totalEnergy, totalParticlesForThisSystem, MaxNumberOfEnergyLevels, particleType);

            int[][] iPath = iPaths[0];
            SystemPath path = new SystemPath(iPath);

            SystemOfParticles systemOfParticles = new SystemOfParticles(true, MaxNumberOfEnergyLevels, particleType, path);

            str += "\n" + systemOfParticles.getStateAsString();

            str += "\n" + path.getPathTaken().toString();

        }
        catch (NotValidSetOfParticlesException e) {e.printStackTrace(); }
        catch (NotAValidEnergyLevel s) {s.printStackTrace(); }
        catch (NotValidParticleException e) { e.printStackTrace(); }
        catch (BadPathException e) { e.printStackTrace(); }
        catch (TooLargeException e) { e.printStackTrace();}



        textView.setText(str);
    }
}
