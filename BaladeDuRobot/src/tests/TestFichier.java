package tests;

import static org.junit.Assert.*;
import metier.Entrepot;
import metier.Instance;

import org.junit.Before;
import org.junit.Test;

import fichiers.Fichier;

public class TestFichier {

	@Test
	public void testLectureFichier() {
		
		Fichier fich = new Fichier("Samples/uc001-test.txt");
		
		assertTrue(fich.getFichier().exists());
		
		fich.afficherStream();
	}
	
	@Test
	public void testCreationInstance(){
		Fichier fich = new Fichier("Samples/uc001-test.txt");
		Instance i = fich.interpretationFichier();
		System.out.print(i.toString());
		
		
	}

}
