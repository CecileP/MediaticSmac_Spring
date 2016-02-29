package fr.iocean.application.emprunt.exception;

public class EmpruntNonTrouveException extends EmpruntException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmpruntNonTrouveException(String champ, Object valeur) {
		super(champ, valeur);
	}

	public String toString() {
		return super.descriptionEmprunt() + " non trouve";
	}
}
