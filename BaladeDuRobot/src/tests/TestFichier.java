package tests;

import static org.junit.Assert.*;
import metier.Entrepot;
import metier.Instance;

import org.junit.Before;
import org.junit.Test;

import fichiers.Fichier;

public class TestFichier {
	Fichier fich;
	Instance i;
	
	
	
	@Before
	public void initialization(){
		fich = new Fichier("Samples/uc001-test.txt");
		i = fich.interpretationFichier();
	}
	@Test
	public void testLectureFichier() {
		assertTrue(fich.getFichier().exists());
		
		//fich.afficherStream();
	}
	
	@Test
	public void testCreationInstance(){
		assertTrue(i.getEntrepot()!=null);
		assertTrue(i.getRobot()!=null);
			
	}
	
	@Test
	public void ecritureFichier(){
		Fichier.enregistre(i,"Samples/testEcriturefichier");
		
		
	}

}
