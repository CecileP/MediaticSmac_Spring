package fr.iocean.application.emprunt.exception;

public class EmpruntNonTrouveException extends EmpruntException {

	public EmpruntNonTrouveException(String champ, Object valeur) {
		super(champ, valeur);
	}

	public String toString() {
		return super.descriptionEmprunt() + " non trouve";
	}
}
