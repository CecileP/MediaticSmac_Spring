package fr.iocean.application.emprunt.exception;

public class EmpruntNonTrouve extends EmpruntException {

	public EmpruntNonTrouve(String champ, Object valeur) {
		super(champ, valeur);
	}

	public String toString() {
		return super.descriptionEmprunt() + " non trouve";
	}
}
