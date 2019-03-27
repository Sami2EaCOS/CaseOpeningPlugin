package fr.smourad.Test.ressources;

public enum Rarity {

	LEGENDARY(5), EPIC(25), RARE(50), COMMON(100);
	
	private final int chancePoint;
	
	private Rarity(int chancePoint) {
		this.chancePoint = chancePoint;
	}
	
	public int getChancePoint() {
		return chancePoint;
	}
}
