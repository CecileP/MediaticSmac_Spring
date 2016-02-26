package fr.iocean.application.emprunt.exception;

public abstract class EmpruntException extends Exception {

	protected String champ;
	protected Object valeur;

	public EmpruntException(String champ, Object valeur) {
		this.champ = champ;
		this.valeur = valeur;
	}

	public String descriptionEmprunt() {
		return "Emprunt avec " + this.champ + "=" + this.valeur;
	}
}
