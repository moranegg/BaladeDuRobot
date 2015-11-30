package uitest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.Box;

import java.awt.Component;

import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Test {

	private JFrame frame;
	private int maxObs;
	private int maxX;
	private int maxY;
	private JSpinner lng;
	private JSpinner lag;
	private JPanel grille;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
		DrawGrille();
	}
	
	public void DrawGrille(){
		int ln = (int) lng.getValue(); 
		int la = (int) lag.getValue(); 
		grille.removeAll();
		grille.setLayout(null);
		
		int height = grille.getHeight();
		int width = grille.getWidth();
		
		int sx = (width-10)/ln;
		int sy = (height-10)/la;
		
		if(sx < sy)
			sy = sx;
		else
			sx=sy;
		
		for(int i = 0;i<ln;i++){
			for(int j = 0;j<la;j++){
				Freegfx btn = new Freegfx(i,j);
				btn.setBounds(i*sx+5, j*sy+5,  sx, sy);
				btn.setImgSize(sx, sy);
				grille.add(btn);
			}
		}
		grille.repaint();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				
				DrawGrille();
			}
		});
		frame.setBounds(100, 100, 530, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFichiers = new JMenu("File");
		menuBar.add(mnFichiers);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFichiers.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFichiers.add(mntmSave);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFichiers.add(mntmQuit);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();

		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnVal = new JButton("Valider");
		btnVal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnVal);
		
		JButton btnCalc = new JButton("Calculer");
		panel_1.add(btnCalc);
		
		JLabel lblSolution = new JLabel("Solution :");
		panel_1.add(lblSolution);
		
		JLabel lblNull = new JLabel("NULL");
		panel_1.add(lblNull);
		
		this.grille = new JPanel();
		panel.add(grille, BorderLayout.CENTER);
		grille.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JLabel lblEntrepot = new JLabel("Entrepot :");
		lblEntrepot.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lblEntrepot);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblLongueur = new JLabel("Longueur :");
		panel_4.add(lblLongueur);
		
		lng = new JSpinner();

		lng.setModel(new SpinnerNumberModel(10, 1, 50, 1));
		panel_4.add(lng);
		
		JLabel lblLargeur = new JLabel("Largeur :");
		panel_4.add(lblLargeur);
		
		lag = new JSpinner();
		lag.setModel(new SpinnerNumberModel(10, 1, 50, 1));
		panel_4.add(lag);
		
		JLabel lblObstacles = new JLabel("Obstacles :");
		panel_4.add(lblObstacles);
		
		JSpinner obs = new JSpinner();
		maxObs = (Integer)lag.getValue()*(Integer)lng.getValue()/4;

		obs.setModel(new SpinnerNumberModel(0, 0, maxObs, 1));
		panel_4.add(obs);
		
		JLabel lblRobot = new JLabel("Robot :");
		lblRobot.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lblRobot);
		
		JLabel lblPosition = new JLabel("Position :");
		lblPosition.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_3.add(lblPosition);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblX = new JLabel("X :");
		panel_5.add(lblX);
		
		JSpinner rdx = new JSpinner();
		maxX = (Integer)lng.getValue();
		rdx.setModel(new SpinnerNumberModel(0, 0, maxX, 1));
		panel_5.add(rdx);
		
		JLabel lblY = new JLabel("Y :");
		panel_5.add(lblY);
		
		JSpinner rdy = new JSpinner();
		maxY = (Integer)lng.getValue();
		rdy.setModel(new SpinnerNumberModel(0, 0, maxY, 1));
		panel_5.add(rdy);
		
		JLabel lblOrientation = new JLabel("orientation :");
		panel_5.add(lblOrientation);
		
		JComboBox ori = new JComboBox();
		ori.setModel(new DefaultComboBoxModel(new String[] {"nord", "sud", "est", "ouest"}));
		panel_5.add(ori);
		
		JLabel lblObjectif = new JLabel("Objectif :");
		lblObjectif.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_3.add(lblObjectif);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblX_1 = new JLabel("X :");
		panel_6.add(lblX_1);
		
		JSpinner rox = new JSpinner();
		rox.setModel(new SpinnerNumberModel(0, 0, maxX, 1));
		panel_6.add(rox);
		
		JLabel lblY_1 = new JLabel("Y :");
		panel_6.add(lblY_1);
		
		JSpinner roy = new JSpinner();
		roy.setModel(new SpinnerNumberModel(0, 0, maxY, 1));
		panel_6.add(roy);
		
		JButton btnAlea = new JButton("Aléatoire");
		btnAlea.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(btnAlea);
		
		//Desactivation saisie manuelle spinners
		JFormattedTextField tf = ((JSpinner.DefaultEditor) roy.getEditor()).getTextField();
	    tf.setEditable(false);
	    tf.setBackground(Color.white);
	    tf = ((JSpinner.DefaultEditor) rox.getEditor()).getTextField();
	    tf.setEditable(false);
	    tf.setBackground(Color.white);
	    tf = ((JSpinner.DefaultEditor) rdx.getEditor()).getTextField();
	    tf.setEditable(false);
	    tf.setBackground(Color.white);
	    tf = ((JSpinner.DefaultEditor) rdy.getEditor()).getTextField();
	    tf.setEditable(false);
	    tf.setBackground(Color.white);
	    tf = ((JSpinner.DefaultEditor) lng.getEditor()).getTextField();
	    tf.setEditable(false);
	    tf.setBackground(Color.white);
	    tf = ((JSpinner.DefaultEditor) lag.getEditor()).getTextField();
	    tf.setEditable(false);
	    tf.setBackground(Color.white);
	    tf = ((JSpinner.DefaultEditor) obs.getEditor()).getTextField();
	    tf.setEditable(false);
	    tf.setBackground(Color.white);
	    
	    
		//Listeners
		lng.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				maxObs = (Integer)lag.getValue()*(Integer)lng.getValue()/4;
				maxX = (Integer)lng.getValue();
				int curdx = (int) rdx.getValue(); 
				int curox = (int) rox.getValue(); 
				int curobs = (int) obs.getValue(); 

				if(maxX < curdx)
					curdx = maxX;
				if(maxX < curox)
					curox = maxX;
				if(maxObs < curobs)
					curobs = maxObs;
				rdx.setModel(new SpinnerNumberModel(curdx, 0, maxX, 1));
				rox.setModel(new SpinnerNumberModel(curox, 0, maxX, 1));
				obs.setModel(new SpinnerNumberModel(curobs, 0, maxObs, 1));
				JFormattedTextField tf = ((JSpinner.DefaultEditor) rdx.getEditor()).getTextField();
			    tf.setEditable(false);
			    tf.setBackground(Color.white);
				tf = ((JSpinner.DefaultEditor) rox.getEditor()).getTextField();
			    tf.setEditable(false);
			    tf.setBackground(Color.white);
				tf = ((JSpinner.DefaultEditor) obs.getEditor()).getTextField();
			    tf.setEditable(false);
			    tf.setBackground(Color.white);
			    
			    DrawGrille();
			}
		});
		lag.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				maxObs = (Integer)lag.getValue()*(Integer)lng.getValue()/4;
				maxY = (Integer)lag.getValue();
				int curdy = (int) rdy.getValue(); 
				int curoy = (int) roy.getValue(); 
				int curobs = (int) obs.getValue(); 
				if(maxY < curdy)
					curdy = maxY;
				if(maxY < curoy)
					curoy = maxY;
				if(maxObs < curobs)
					curobs = maxObs;
				rdy.setModel(new SpinnerNumberModel(curdy, 0, maxY, 1));
				roy.setModel(new SpinnerNumberModel(curoy, 0, maxY, 1));
				obs.setModel(new SpinnerNumberModel(curobs, 0, maxObs, 1));
				JFormattedTextField tf = ((JSpinner.DefaultEditor) rdy.getEditor()).getTextField();
			    tf.setEditable(false);
			    tf.setBackground(Color.white);
				tf = ((JSpinner.DefaultEditor) roy.getEditor()).getTextField();
			    tf.setEditable(false);
			    tf.setBackground(Color.white);
				tf = ((JSpinner.DefaultEditor) obs.getEditor()).getTextField();
			    tf.setEditable(false);
			    tf.setBackground(Color.white);
			    
			    DrawGrille();
			}
		});
		grille.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				/*int ln = (int) lng.getValue(); 
				int la = (int) lag.getValue(); 
				System.out.println("x : "+e.getX()+" y : "+e.getY()+"maxX :"+e.getComponent().getHeight()+"maxX :"+e.getComponent().getWidth());
				((JPanel)e.getComponent()).removeAll();
				((JPanel)e.getComponent()).setLayout(null);
				
				int height = e.getComponent().getHeight();
				int width = e.getComponent().getWidth();
				
				int sx = (width-10)/ln;
				int sy = (height-10)/la;
				
				if(sx < sy)
					sy = sx;
				else
					sx=sy;
				
				for(int i = 0;i<ln;i++){
					for(int j = 0;j<la;j++){
						Freegfx btn = new Freegfx(i,j);
						//btn.setAlignmentX(Component.CENTER_ALIGNMENT);
						btn.setBounds(i*sx+5, j*sy+5,  sx, sy);
						btn.setImgSize(sx, sy);
						((JPanel)e.getComponent()).add(btn);
					}
				}
				e.getComponent().repaint();
				
				DrawGrille();*/
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			

 
        });
	}
}
