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
public class SystemPath {
	
	private boolean isValidPath;
	private boolean isRedundant;
	private List<Integer> pathTaken; 

	/**
	 * 
	 */
	public SystemPath() {
		this.pathTaken = new ArrayList<Integer>();
		this.pathTaken.add(0);
	}
	
	public SystemPath(int[][] pathTakenNow) throws BadPathException {
		this.pathTaken = new ArrayList<Integer>();
		if (pathTakenNow.length < 1) { 
			isValidPath = false;
			throw new BadPathException("Path initialized with zero length path.");
		}
		
		for (int i = 0; i < pathTakenNow.length; i++) {
			if (pathTakenNow[i].length < 1) {
				isValidPath = false;
				throw new BadPathException("Path given null energy!");
			}
			if(pathTakenNow[i][0] < 0) {
				isValidPath = false;
				throw new BadPathException("Path given negative energy!"); 
			}
			this.pathTaken.add(i, pathTakenNow[i][0]);
			isValidPath = true;
		}
	}

	public boolean isValidPath() {
		return isValidPath;
	}

	public void setValidPath(boolean isValidPath) {
		this.isValidPath = isValidPath;
	}

	public boolean isRedundant() {
		return isRedundant;
	}

	public void setRedundant(boolean isRedundant) {
		this.isRedundant = isRedundant;
	}

	public List<Integer> getPathTaken() {
		return pathTaken;
	}

	public int[] getIntPathTaken() {
		int[] ints = new int[pathTaken.size()];
		for(int i = 0; i < pathTaken.size(); i++) {
			ints[i] = pathTaken.get(i);
		}
		return ints;
	}

	public void setPathTaken(List<Integer> pathTaken) {
		this.pathTaken = pathTaken;
	}

	public String toStateString() {
		String state = "";
		int particle = 0;
		for(Integer i : pathTaken) {
			state += "Particle " + particle +" = " + ((double)i)/2 + "eV \n";
			particle++;
		}
		return state;
	}

}
