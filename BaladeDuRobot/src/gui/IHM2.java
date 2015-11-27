package gui;

import javax.swing.JApplet;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import fichiers.Fichier;


import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class IHM2 extends JFrame {
	
	private JTextField longueur;
	private JTextField largeur;
	private JTextField nbObstacles;
	private JTextField departColonne;
	private JTextField departLigne;
	private JTextField arriveeColonne;
	private JTextField direction;
	private JTextField arriveeLigne;
	private GestionGui gestion;
	private JLabel lblSol;
	//private JTextField filename = new JTextField(), dir = new JTextField();

	/**
	 * Create the applet.
	 */
	public IHM2() {
		
		 
		
		
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(46dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblMatrice = new JLabel("Dépôt:");
		getContentPane().add(lblMatrice, "2, 2");
		
		Button chargeFichier = new Button("Charger fichier");
		chargeFichier.addActionListener(new ChargerFichier());
		
		getContentPane().add(chargeFichier, "10, 2");
		
		JLabel lblLongeur = new JLabel("longeur");
		getContentPane().add(lblLongeur, "4, 4, left, default");
		
		longueur = new JTextField();
		getContentPane().add(longueur, "6, 4, fill, default");
		longueur.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("largeur");
		getContentPane().add(lblNewLabel, "4, 6, left, default");
		
		largeur = new JTextField();
		getContentPane().add(largeur, "6, 6, fill, default");
		largeur.setColumns(10);
		
		JLabel lblNombreDobstacle = new JLabel("nombre d'obstacle");
		getContentPane().add(lblNombreDobstacle, "4, 8");
		
		nbObstacles = new JTextField();
		getContentPane().add(nbObstacles, "6, 8, fill, default");
		nbObstacles.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Robot:");
		getContentPane().add(lblNewLabel_1, "2, 10");
		
		JLabel lblDpart = new JLabel("départ-colonne");
		getContentPane().add(lblDpart, "4, 12");
		
		departColonne = new JTextField();
		getContentPane().add(departColonne, "6, 12, fill, default");
		departColonne.setColumns(10);
		
		JLabel lblColonnel = new JLabel("départ-ligne");
		getContentPane().add(lblColonnel, "4, 14, left, default");
		
		departLigne = new JTextField();
		getContentPane().add(departLigne, "6, 14, fill, default");
		departLigne.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("arrivée-colonne");
		getContentPane().add(lblNewLabel_2, "4, 16, left, default");
		
		arriveeColonne = new JTextField();
		getContentPane().add(arriveeColonne, "6, 16, fill, default");
		arriveeColonne.setColumns(10);
		
		JLabel lblLigne_1 = new JLabel("arrivée-ligne");
		getContentPane().add(lblLigne_1, "4, 18, left, default");
		
		arriveeLigne = new JTextField();
		getContentPane().add(arriveeLigne, "6, 18, fill, default");
		arriveeLigne.setColumns(10);
		
		JLabel lblDirection = new JLabel("direction");
		getContentPane().add(lblDirection, "4, 20");
		
		direction = new JTextField();
		getContentPane().add(direction, "6, 20, fill, default");
		direction.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new validerEntree());
		
		
		getContentPane().add(btnValider, "10, 22");
		
		JLabel lblSolution = new JLabel("Solution:");
		getContentPane().add(lblSolution, "2, 24");
		
		lblSol = new JLabel("");
		getContentPane().add(lblSol, "4, 24");
		
		JButton btnGenrerFichier = new JButton("Générer fichier");
		btnGenrerFichier.setEnabled(true);//TODO mettre à false avant de charger fichier ou valider
		btnGenrerFichier.addActionListener(new genererFichier());
		getContentPane().add(btnGenrerFichier, "10, 24");
		this.pack();
	}
	public void setGestion(GestionGui gestion){
		this.gestion = gestion;
	}

	class ChargerFichier implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	JFileChooser c = new JFileChooser();
		      // Demonstrate "Open" dialog:
		      int rVal = c.showOpenDialog(IHM2.this);
		      if (rVal == JFileChooser.APPROVE_OPTION) {
		    	 String file=c.getSelectedFile().getName();
		    	 String dir= c.getCurrentDirectory().toString();
		    	 //btnGenrerFichier.setEnabled(true);
		        gestion.chargerFichier(dir+"\\"+ file);
		        
		        
		      }
		      if (rVal == JFileChooser.CANCEL_OPTION) {
		        
		      }
	    }
 }
	class validerEntree implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try{
				
				System.out.print(longueur.getText()+" "+ largeur.getText()+" "+
						nbObstacles.getText()+" "+departColonne.getText()+" "+
						departLigne.getText()+" "+arriveeColonne.getText()+" "+
						arriveeLigne.getText()+" "+ direction.getText());
				gestion.valider(longueur.getText(),largeur.getText(),
						nbObstacles.getText(),departColonne.getText(),
						departLigne.getText(),arriveeColonne.getText(),
						arriveeLigne.getText(), direction.getText());
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
 }
	class genererFichier implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try{
				gestion.genererFichier();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
 }
	public JLabel getLbsol() {
		return this.lblSol;
	}
}
				
				
			
	 


