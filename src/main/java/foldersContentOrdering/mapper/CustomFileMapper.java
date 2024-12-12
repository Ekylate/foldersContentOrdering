package foldersContentOrdering.mapper;

import java.io.File;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;

import foldersContentOrdering.BO.CustomFile;

/**
 * Mapper de la classe {@linkplain CustomFile}.
 * @author Sceok
 *
 */
public class CustomFileMapper {
	public CustomFile mapFromFileToCustomFile(File fileParam, BasicFileAttributes attributsSuppParam) {
		final CustomFile result = new CustomFile();
		if(fileParam != null) {
			this.mapFromFileToCustomFile(fileParam);
			if(attributsSuppParam != null) {
				result.setDateDeCreation(LocalDateTime.parse(attributsSuppParam.creationTime().toString()));
				result.setCleUniqueFichier(attributsSuppParam.fileKey().toString());
			}
		}
		return result;
	}

	public CustomFile mapFromFileToCustomFile(File fileParam) {
		final CustomFile result = new CustomFile();
		if(fileParam != null) {
			result.setFichier(fileParam);
		}
		return result;
	}
}
