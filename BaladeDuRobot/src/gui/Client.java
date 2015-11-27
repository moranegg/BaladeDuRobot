package gui;


public class Client {
	
	public static void main(String[] atgs){
		IHM2 ihm= new IHM2();
		GestionGui ggui= new GestionGui(ihm);
		ihm.setGestion(ggui);
		ihm.setVisible(true);
	}
}
