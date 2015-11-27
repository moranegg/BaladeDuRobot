package gui;


public class Client {
	
	public static void main(String[] atgs){
		
		GestionGui ggui= new GestionGui();
		IHM2 ihm= new IHM2(ggui);
		ihm.setVisible(true);
	}
}
