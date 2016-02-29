package fr.iocean.application.emprunt.exception;

public class EmpruntExistantException extends EmpruntException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmpruntExistantException(String champ, Object valeur) {
		super(champ, valeur);
	}

	public String toString() {
		return super.descriptionEmprunt() + " existe";
	}
}
