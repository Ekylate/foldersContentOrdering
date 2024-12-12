package foldersContentOrdering;

import java.util.Comparator;

import foldersContentOrdering.BO.CustomFile;

/**
 * Comparateur de {@linkplain CustomFile} sur base de leur date de création.
 * @author Sceok
 *
 */
public class CustomFileCreationDateComparator implements Comparator<CustomFile> {

	public static int compareStatic(CustomFile cf1, CustomFile cf2) {
		int result;
		if(cf1 == null || cf1.getDateDeCreation() == null) {
			result = -1;
		} else if (cf2 == null || cf2.getDateDeCreation() == null) {
			result = 1;
		} else {
			result = cf1.getDateDeCreation().compareTo(cf2.getDateDeCreation());
		}
		return result;
	}

	public int compare(CustomFile cf1, CustomFile cf2) {
		return compareStatic(cf1, cf2);
	}
}
