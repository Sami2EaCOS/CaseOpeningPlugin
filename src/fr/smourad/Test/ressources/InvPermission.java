package fr.smourad.Test.ressources;

public enum InvPermission {
	READ(0), WRITE(1);
	
	private final int permission;
	
	private InvPermission(int permission) {
		this.permission = permission;
	}
	
	public int getPermission() {
		return permission;
	}
	
}
