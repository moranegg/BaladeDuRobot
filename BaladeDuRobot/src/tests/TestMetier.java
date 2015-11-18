package tests;

import static org.junit.Assert.assertTrue;
import metier.Entrepot;
import metier.Instance;
import metier.Robot;

import org.junit.Test;

import fichiers.Fichier;

public class TestMetier {

	
	@Test
	public void testCreationInstance() {
		Entrepot entrepot=null;
		Robot robot=null;
		//Instance en dur :
		Instance instance = new Instance(entrepot, robot);
		assertTrue(instance.getEntrepot().equals(robot));

	}
	
}
