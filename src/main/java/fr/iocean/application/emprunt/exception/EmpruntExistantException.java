package fr.iocean.application.emprunt.exception;

public class EmpruntExistantException extends EmpruntException {

	public EmpruntExistantException(String champ, Object valeur) {
		super(champ, valeur);
	}

	public String toString() {
		return super.descriptionEmprunt() + " existe";
	}
}
