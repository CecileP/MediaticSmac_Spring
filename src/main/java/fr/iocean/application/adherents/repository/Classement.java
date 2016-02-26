package fr.iocean.application.adherents.repository;

public enum Classement {
	CROISSANT, DECROISSANT;
	
	public String toSQLString() {
		switch (this) {
		case CROISSANT:
			return "ASC";
		case DECROISSANT:
			return "DESC";
		default:
			return null;
		}
	}
}
