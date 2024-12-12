package foldersContentOrdering;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import foldersContentOrdering.mapper.CustomFileMapper;

/**
 * @author Sceok
 *
 */
public class FolderOrderingEngine {
	static CustomFileMapper mapper;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		mapper = new CustomFileMapper();
		final String dossierDeTravail = demanderDossierDeTravail();
		traiterFichiers(dossierDeTravail);
	}

	private static String demanderDossierDeTravail() {
		return null;
		// TODO Auto-generated method stub

	}

	private static void traiterFichiers(String dossierDeTravailParam) {
		final File dossierDeTravail = new File(dossierDeTravailParam);
		if(FileUtils.isDirectory(dossierDeTravail)) {
			final Collection<File> listOfFiles = FileUtils.listFiles(dossierDeTravail, null, false);
			final int nombreDeFichiers = listOfFiles.size();
//			final Comparator<CustomFile> comparatorOlderOnesFirst = getFilesComparator();
			listOfFiles.stream().map(f -> mapper.mapFromFileToCustomFile(f, recupererAttributsFichier(f))).sorted();
		}
	}

	private static BasicFileAttributes recupererAttributsFichier(File fichierParam) {
		BasicFileAttributes resultat = null;
		try {
			resultat = Files.readAttributes(Paths.get(fichierParam.getPath()), BasicFileAttributes.class);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultat;
	}
}
