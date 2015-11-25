package gui;



import javax.swing.JFrame;


public class IHM {
	JFrame fenetre;
	
	
	public IHM(){
		fenetre = new JFrame();
		

	    fenetre.setTitle("La balade du robot");
	    fenetre.setSize(400, 100);

	    fenetre.setLocationRelativeTo(null);
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    fenetre.setVisible(true);

	}
	
	
	

    

}
