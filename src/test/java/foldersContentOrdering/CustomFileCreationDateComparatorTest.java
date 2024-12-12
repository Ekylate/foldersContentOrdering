package foldersContentOrdering;



import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import foldersContentOrdering.BO.CustomFile;

/**
 * Classe de test de la classe {@linkplain CustomFileCreationDateComparator}.
 * @author Sceok
 *
 */
public class CustomFileCreationDateComparatorTest {

	@Test
	public void test1() {
		final CustomFile fichier1 = new CustomFile();
		final CustomFile fichier2 = new CustomFile();
		fichier1.setDateDeCreation(LocalDateTime.now());
		fichier2.setDateDeCreation(LocalDateTime.now().plusDays(1L));
		final int compareStatic = CustomFileCreationDateComparator.compareStatic(fichier1, fichier2);
	}

	@Test
	public void test2() {
		final CustomFile fichier1 = new CustomFile();
		final CustomFile fichier2 = new CustomFile();
		final CustomFile fichier3 = new CustomFile();
		final CustomFile fichier4 = new CustomFile();
		final CustomFile fichier5 = new CustomFile();
		final CustomFile fichier6 = new CustomFile();
		fichier1.setDateDeCreation(LocalDateTime.now());
		fichier2.setDateDeCreation(LocalDateTime.now().plusDays(1L));
		fichier3.setDateDeCreation(LocalDateTime.now().plusDays(2L));
		fichier4.setDateDeCreation(LocalDateTime.now().plusDays(3L));
		fichier5.setDateDeCreation(LocalDateTime.now().plusDays(4L));
		fichier6.setDateDeCreation(LocalDateTime.now().plusDays(5L));
		final List<CustomFile> list = Arrays.asList(fichier6, fichier4, fichier5, fichier1, fichier3, fichier2);
		final List<CustomFile> orderedList = list.stream().sorted().collect(Collectors.toList());
		for (int i=0;i<6;++i) {
			assertNotEquals(list.get(i), orderedList.get(i));
			if(i<orderedList.size()-1) {
				assertTrue(orderedList.get(i).getDateDeCreation().isBefore(orderedList.get(i+1).getDateDeCreation()));
			}
		}
	}
}
