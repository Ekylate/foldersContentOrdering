package foldersContentOrdering.BO;

import java.io.File;
import java.time.LocalDateTime;

import javax.naming.directory.BasicAttributes;

/**
 * Classe contenant un fichier et quelques champs que l'on peut trouver dans la classe
 * {@link BasicAttributes} tel que la date de création et la clé unique.
 * @author Sceok
 *
 */
public class CustomFile {
	File fichier;

	LocalDateTime dateDeCreation;

	String cleUniqueFichier;
	//TODO

	/**
	 *
	 */
	public CustomFile() { }

	/**
	 * @return the fichier
	 */
	public File getFichier() {
		return this.fichier;
	}

	/**
	 * @param fichier the fichier to set
	 */
	public void setFichier(File fichier) {
		this.fichier = fichier;
	}

	/**
	 * @return the dateDeCreation
	 */
	public LocalDateTime getDateDeCreation() {
		return this.dateDeCreation;
	}

	/**
	 * @param dateDeCreation the dateDeCreation to set
	 */
	public void setDateDeCreation(LocalDateTime dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}

	/**
	 * @return the cleUniqueFichier
	 */
	public String getCleUniqueFichier() {
		return this.cleUniqueFichier;
	}

	/**
	 * @param cleUniqueFichier the cleUniqueFichier to set
	 */
	public void setCleUniqueFichier(String cleUniqueFichier) {
		this.cleUniqueFichier = cleUniqueFichier;
	}

	@Override
	public String toString() {
		return "CustomFile [fichier=" + this.fichier + ", dateDeCreation=" + this.dateDeCreation + ", cleUniqueFichier="
				+ this.cleUniqueFichier + "]";
	}
}
