package foldersContentOrdering;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import foldersContentOrdering.BO.CustomFile;
import foldersContentOrdering.mapper.CustomFileMapper;

/**
 * @author Sceok
 *
 */
public class FolderOrderingEngine {
	private static final String ZERO_NUMBER = "0";
	private static final String UNDERSCORE_SIGN = "_";
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

	//JTA/JAE : Prévoir de traiter cas où plus de 2 underscores dans le nom : se débarasser des premiers qui ne contiendraient que des chiffres
	private static void traiterFichiers(String dossierDeTravailParam) {
		final File dossierDeTravail = new File(dossierDeTravailParam);
		if(FileUtils.isDirectory(dossierDeTravail)) {
			final Collection<File> listOfFiles = FileUtils.listFiles(dossierDeTravail, null, false);
			final int nombreDeFichiers = listOfFiles.size();
			final String numberLength = String.valueOf(nombreDeFichiers);
			 final List<CustomFile> orderedListOfFiles = listOfFiles.stream().map(f -> mapper.mapFromFileToCustomFile(f, recupererAttributsFichier(f))).sorted().collect(Collectors.toList());
			 for(int i=0; i<orderedListOfFiles.size();++i) {
				 final CustomFile loopFile = orderedListOfFiles.get(i);
				 final String fileName = loopFile.getFichier().getName();
				 final String fileAbsolutePath = loopFile.getFichier().getAbsolutePath();
				 if(fileAbsolutePath.endsWith(fileName)) {
					 final String fileAbsolutePathToWorkOn = StringUtils.removeEnd(fileAbsolutePath, fileName);
					 final String newFileNumber = String.valueOf(i+1);
					 if(hasFileAWrongNumer(loopFile, newFileNumber)) {
						 renameFile(numberLength, loopFile, fileAbsolutePathToWorkOn, newFileNumber);
					 }
				 }
			 }
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

	private static boolean hasFileAWrongNumer(CustomFile loopFileParam, String newFileNumberParam) {
		boolean resultat = false;
		if(loopFileParam != null && StringUtils.isNotBlank(newFileNumberParam)) {
			final String fileNameLocal = loopFileParam.getFichier().getName();
			final String[] splitLocal = StringUtils.split(fileNameLocal, UNDERSCORE_SIGN);
			if(!StringUtils.equals(splitLocal[0], newFileNumberParam) || splitLocal.length < 1) {
				resultat = true;
			}
		}
		return resultat;
	}

	private static void renameFile(final String numberLength, final CustomFile loopFile,
			String fileAbsolutePathToWorkOn, final String newFileNumber) {
		final String newFileName = StringUtils.leftPad(newFileNumber, numberLength.length(), ZERO_NUMBER) + UNDERSCORE_SIGN + loopFile.getFichier().getName() ;
		fileAbsolutePathToWorkOn  = fileAbsolutePathToWorkOn + newFileName;
		loopFile.getFichier().renameTo(new File(fileAbsolutePathToWorkOn));
	}
}
