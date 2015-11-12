package fichiers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestFichier {

	@Test
	public void testLectureFichier() {
		
		Fichier fich = new Fichier("Samples/uc001-test.txt");
		
		assertTrue(fich.getFichier().exists());
		
		fich.afficherStream();
	}
	
	@Test
	public void testCreationEntrepot(){
		Fichier fich = new Fichier("Samples/uc001-test.txt");
		assertTrue(fich.getEntrepot() instanceof Entrepot);
		
		
	}

}
