package uitest;

import fichiers.Fichier;
import graphes.Graphe;
import gui.IHM2;

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
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.Box;

import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLayeredPane;
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

import metier.Entrepot;
import metier.Instance;
import metier.Robot;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JCheckBox;

import scala.util.matching.Regex;

public class Test {

	private JFrame frame;
	private int maxObs;
	private int maxX;
	private int maxY;
	private int sx;
	private int sy;
	private JSpinner lng;
	private JSpinner lag;
	private JSpinner rdx;
	private JSpinner rdy;
	private JSpinner rox;
	private JSpinner roy;
	private JLayeredPane grille;
	private JLabel lbmax;
	private JLabel lbcur;
	private JComboBox ori;
	private Graphe graph;
	private JButton btnNewButton_1;
	JLabel lblNull;
	private static Instance instance;
	private String solution = "[-1]";
	private boolean drawed = false;
	
	private List<Freegfx> squareList = new LinkedList<Freegfx>();
	private List<PathComponent> pathList = new LinkedList<PathComponent>();
	private RobotPanel robot;
	private ObjectifPanel objectif;
	private JTextField nbobs;
	

	/**
	 * Launch the application.
	 */
	public static int randInt(int min, int max) {

	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static void main(String[] args) {
		
		int[][] mat=new int[10][];
		for (int i=0 ; i<mat.length; i++){
			mat[i]=new int[10];
		}
		Entrepot entrepot = new Entrepot(10,10,mat);
		Robot robotInst = new Robot(0, 0, 10, 10, "sud");
		instance = new Instance(entrepot,robotInst);
		System.out.println(instance);
		
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
	public void updateMat(){
		int[][] mat=new int[(Integer)lng.getValue()][];
		for (int i=0 ; i<mat.length; i++){
			mat[i]=new int[(Integer)lag.getValue()];
		}
		instance.getEntrepot().setMatrice(mat);
	}
	public void updateInstance(){

			instance.getEntrepot().setLargeur((Integer)lag.getValue());
			instance.getEntrepot().setLongueur((Integer)lng.getValue());
		    
		    instance.getRobot().setDepart(new Point((Integer)rdx.getValue(),(Integer)rdy.getValue()));
		    instance.getRobot().setObjectif(new Point((Integer)rox.getValue(),(Integer)roy.getValue()));
		    instance.getRobot().setDirection((String)ori.getSelectedItem());
		    System.out.println(instance);
	}
	public Test() {
		initialize();
		DrawGrille();
	}
	public void calcRatio(){
		int ln = (int) lng.getValue(); 
		int la = (int) lag.getValue(); 
		
		int height = grille.getHeight();
		int width = grille.getWidth();
		
		sx = (height-10)/(ln+1);
		sy = (width-10)/(la+1);
		/*sx = (width+sx)/ln;
		sy = (height+sy)/la;*/
		//System.out.println("Pre Sx : "+sx+" Sy : "+sy);

		if(sx < sy)
			sy = sx;
		else
			sx=sy;
		
		//System.out.println("Sx : "+sx+" Sy : "+sy);
	}
	public void cleanPath(){
		
		for(PathComponent p :pathList)
		{
			grille.remove(p);

		}
		grille.repaint();
		grille.revalidate();
		drawed = false;
		pathList.clear();
	}
	public void drawPath(){
		calcRatio();
		cleanPath();
		//System.out.println();
		if(!solution.equals("[-1]") && !drawed){
			drawed = true;
			String tmpori = (String)ori.getSelectedItem();
			int dx = (int)rdx.getValue();
			int dy = (int)rdy.getValue();
			
			for(String s : solution.substring(3, solution.length()-1).split(", ")){
				PathComponent path = new PathComponent(dx,dy);
				pathList.add(path);
				//System.out.println("For debug : tmpori : "+tmpori);
				if(s.contains("a1")){

					if(tmpori.equals("sud")){
						//System.out.println(s+"sud");
						path.setBounds(dy*sy+sy/2-sy/8+5,dx*sx+sx/2-sy/8+5,  sy/4, sx/4+sx);
						path.setImgSize(sx/4, sy/4+sy);
						dx+=1;
					}
					if(tmpori.equals("nord")){
						//System.out.println(s+"nord");
						//System.out.println(s+"ouest");
						path.setBounds(dy*sy+sy/2-sy/8+5,dx*sx+sx/2-sy/8+5-sx,  sy/4, sx/4+sx);
						path.setImgSize(sx/4, sy/4+sy);
						dx-=1;
					}
					if(tmpori.equals("est")){
						//System.out.println(s+"est");
						path.setBounds(dy*sy+sy/2-sy/8+5,dx*sx+sx/2-sy/8+5,  sy/4+sy, sx/4);
						path.setImgSize(sx/4+sx, sy/4);
						dy+=1;
					}
					if(tmpori.equals("ouest")){
						//System.out.println(s+"ouest");
						path.setBounds(dy*sy+sy/2-sy/8+5-sy,dx*sx+sx/2-sy/8+5,  sy/4+sy, sx/4);
						path.setImgSize(sx/4+sx, sy/4);
						dy-=1;
					}
					grille.add(path,5);
					path.repaint();
				}
				else if(s.contains("a2")){

					if(tmpori.equals("sud")){
						//System.out.println(s+"sud");
						path.setBounds(dy*sy+sy/2-sy/8+5,dx*sx+sx/2-sy/8+5,  sy/4, sx/4+2*sx);
						path.setImgSize(sx/4, sy/4+2*sy);
						dx+=2;
					}
					if(tmpori.equals("nord")){
						//System.out.println(s+"nord");
						//System.out.println(s+"ouest");
						path.setBounds(dy*sy+sy/2-sy/8+5,dx*sx+sx/2-sy/8+5-2*sx,  sy/4, sx/4+2*sx);
						path.setImgSize(sx/4, sy/4+2*sy);
						dx-=2;
					}
					if(tmpori.equals("est")){
						//System.out.println(s+"est");
						path.setBounds(dy*sy+sy/2-sy/8+5,dx*sx+sx/2-sy/8+5,  sy/4+2*sy, sx/4);
						path.setImgSize(sx/4+2*sx, sy/4);
						dy+=2;
					}
					if(tmpori.equals("ouest")){
						//System.out.println(s+"ouest");
						path.setBounds(dy*sy+sy/2-sy/8+5-2*sy,dx*sx+sx/2-sy/8+5,  sy/4+2*sy, sx/4);
						path.setImgSize(sx/4+2*sx, sy/4);
						dy-=2;
					}
					grille.add(path,5);
					path.repaint();
				}
				else if(s.contains("a3")){
					//System.out.println(s);
					
					if(tmpori.equals("sud")){
						//System.out.println(s+"sud");
						path.setBounds(dy*sy+sy/2-sy/8+5,dx*sx+sx/2-sy/8+5,  sy/4, sx/4+3*sx);
						path.setImgSize(sx/4, sy/4+3*sy);
						dx+=3;
					}
					if(tmpori.equals("nord")){
						//System.out.println(s+"nord");
						//System.out.println(s+"ouest");
						path.setBounds(dy*sy+sy/2-sy/8+5,dx*sx+sx/2-sy/8+5-3*sx,  sy/4, sx/4+3*sx);
						path.setImgSize(sx/4, sy/4+3*sy);
						dx-=3;
					}
					if(tmpori.equals("est")){
						//System.out.println(s+"est");
						path.setBounds(dy*sy+sy/2-sy/8+5,dx*sx+sx/2-sy/8+5,  sy/4+3*sy, sx/4);
						path.setImgSize(sx/4+3*sx, sy/4);
						dy+=3;
					}
					if(tmpori.equals("ouest")){
						//System.out.println(s+"ouest");
						path.setBounds(dy*sy+sy/2-sy/8+5-3*sy,dx*sx+sx/2-sy/8+5,  sy/4+3*sy, sx/4);
						path.setImgSize(sx/4+3*sx, sy/4);
						dy-=3;
					}
					grille.add(path,5);
					path.repaint();
				}
				else if(s.contains("G")){
					//System.out.println("G");
					if(tmpori.equals("sud")){
						tmpori = "est";
					}
					else if(tmpori.equals("nord")){
						tmpori = "ouest";
					}
					else if(tmpori.equals("est")){
						tmpori = "nord";
					}
					else if(tmpori.equals("ouest")){
						tmpori = "sud";
					}
				}
				else if(s.contains("D")){
					//System.out.println("D");
					if(tmpori.equals("sud")){
						tmpori = "ouest";
					}
					else if(tmpori.equals("nord")){
						tmpori = "est";
					}
					else if(tmpori.equals("est")){
						tmpori = "sud";
					}
					else if(tmpori.equals("ouest")){
						tmpori = "nord";
					}
				}				
			}
		}
		robot.repaint();
		objectif.repaint();

	}
	
	public void ResizeGrille(){
		calcRatio();
		
		objectif.setBounds(sy*objectif.getposY()+5, sx*objectif.getposX()+5,  sy, sx);
		objectif.setImgSize(sx, sy);
		
		robot.setBounds( sy*robot.getposY()+5,sx*robot.getposX()+5,  sy, sx);
		robot.setImgSize(sx, sy);
		
		/*objectif.setBounds( sy*objectif.getposY()+5,sx*objectif.getposX()+5,  sy, sx);
		objectif.setImgSize(sx, sy);*/
		
		for(Freegfx gfx :  squareList){
				gfx.setBounds(gfx.getposY()*sy+5+sy/2,gfx.getposX()*sx+5+sx/2,  sy, sx);
				gfx.setImgSize(sx, sy);
		}
		cleanPath();
		
		grille.repaint();
		grille.revalidate();
		drawPath();
		robot.repaint();
		objectif.repaint();
	}
	
	public void DrawGrille(){
		int ln = (int) lng.getValue(); 
		int la = (int) lag.getValue(); 
		calcRatio();
		//System.out.println(sx+" "+sy);
		robot = new RobotPanel((int)rdx.getValue(),(int)rdy.getValue(),(String)ori.getSelectedItem());
		robot.setBounds(sy*robot.getposY()+5, sx*robot.getposX()+5,  sy, sx);
		robot.setImgSize(sx, sy);
		robot.setBackground(new Color(0,0,0,0));
		
		objectif = new ObjectifPanel((int)rox.getValue(),(int)roy.getValue());
		objectif.setBounds(sy*objectif.getposY()+5, sx*objectif.getposX()+5,  sy, sx);
		objectif.setImgSize(sx, sy);
		objectif.setBackground(new Color(0,0,0,0));
				
		squareList.clear();
		grille.removeAll();
		lbcur.setText(String.valueOf("0"));
		//grille.add
		grille.add(robot,0);
		grille.add(objectif,0);
		for(int i = 0;i<ln;i++){
			for(int j = 0;j<la;j++){
				Freegfx btn = new Freegfx(i,j);
				squareList.add(btn);
				btn.setBounds(j*sy+5+sy/2,i*sx+5+sx/2,  sy, sx);
				btn.setImgSize(sx, sy);
				
				grille.add(btn,9);
				btn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						
						if(Integer.parseInt(lbcur.getText()) < Integer.parseInt(lbmax.getText()) || ((Freegfx)arg0.getComponent()).isState()){
							int sol = ((Freegfx)arg0.getComponent()).change();
							lbcur.setText(String.valueOf(Integer.parseInt(lbcur.getText())+sol));
							((Freegfx)arg0.getComponent()).repaint();
							//System.out.println("btn : "+((Freegfx)(arg0.getComponent())).getposX()+"_"+((Freegfx)(arg0.getComponent())).getposY());
							if(((Freegfx)arg0.getComponent()).isState())
								instance.getEntrepot().getMatrice()[btn.getposX()][btn.getposY()] = 1;
							else
								instance.getEntrepot().getMatrice()[btn.getposX()][btn.getposY()] = 0;
							System.out.println(instance);
							
						}
						
						robot.repaint();
						objectif.repaint();
					}
					
				});
			}
		}		
		
		cleanPath();
		grille.repaint();
		objectif.repaint();
		robot.repaint();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				
				ResizeGrille();
			}
		});
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFichiers = new JMenu("File");
		menuBar.add(mnFichiers);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanPath();
				JFileChooser c = new JFileChooser();
			      // Demonstrate "Open" dialog:
			      int rVal = c.showOpenDialog(c);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			    	 String file=c.getSelectedFile().getName();
			    	 String dir= c.getCurrentDirectory().toString();
			    	 //btnGenrerFichier.setEnabled(true);

					Fichier fichierInstance = new Fichier(dir+"/"+ file);
					Instance i = fichierInstance.interpretationFichier();
					lng.setValue((int)i.getEntrepot().getLongueur());
					lag.setValue((int)i.getEntrepot().getLargeur());
					rdx.setValue((int)i.getRobot().getDepart().getX());
					rdy.setValue((int)i.getRobot().getDepart().getY());
					rox.setValue((int)i.getRobot().getObjectif().getX());
					roy.setValue((int)i.getRobot().getObjectif().getY());
					ori.setSelectedItem(i.getRobot().getDirection());
					
					for(Freegfx gfx :  squareList){
						if(i.getEntrepot().getMatrice()[gfx.getposX()][gfx.getposY()] == 1 && Integer.parseInt(lbcur.getText())+1 < maxObs){
							instance.getEntrepot().getMatrice()[gfx.getposX()][gfx.getposY()] = 1;
							gfx.change();
							lbcur.setText(String.valueOf(Integer.parseInt(lbcur.getText())+1));
							gfx.repaint();
							
						}
					 }
					System.out.println(instance);
					System.out.println(i);

			        
			      }
			      if (rVal == JFileChooser.CANCEL_OPTION) {
			        
			      }
			}
		});
		mnFichiers.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFichiers.add(mntmSave);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFichiers.add(mntmQuit);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("Nb Obstacles :");
		panel_8.add(lblNewLabel_2);
		
		nbobs = new JTextField();
		panel_8.add(nbobs);
		nbobs.setColumns(10);
		
		JButton btnNewButton = new JButton("Générer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanPath();
				try{
					
					int nb = Integer.parseInt(nbobs.getText());
					if(nb <= maxObs){
						updateInstance();
						updateMat();
						DrawGrille();
					for (int cpt = nb;cpt > 0 ; cpt--){
						
						int randx = randInt(0,(int)lng.getValue()-1);
						int randy = randInt(0,(int)lng.getValue()-1);
						while(instance.getEntrepot().getMatrice()[randx][randy] == 1){
							randx = randInt(0,(int)lng.getValue()-1);
							randy = randInt(0,(int)lag.getValue()-1);
						}
						instance.getEntrepot().getMatrice()[randx][randy] = 1;
					}
					for(Freegfx gfx :  squareList){
						if(instance.getEntrepot().getMatrice()[gfx.getposX()][gfx.getposY()] == 1 && Integer.parseInt(lbcur.getText())+1 < maxObs){
							
							gfx.change();
							lbcur.setText(String.valueOf(Integer.parseInt(lbcur.getText())+1));
							gfx.repaint();
							
						}
					 }
				}
					
				}catch(Exception efds){
					System.out.println("Le paramètre saisi n'est pas un nombre");
				}
				
				
			}
		});
		panel_8.add(btnNewButton);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JCheckBox chckbxGok = new JCheckBox("afficher graphe");
		panel_9.add(chckbxGok);
				
		JButton btnCalc = new JButton("Calculer");
		panel_9.add(btnCalc);
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				graph = new Graphe(instance);
				graph.graphGenerate();
				if(chckbxGok.isSelected())
					graph.graphDisplay();
				solution = graph.pathCompute();
				lblNull.setText(solution);
				drawPath();
			}
		});
		
		JLabel lblSolution = new JLabel("Solution :");
		panel_9.add(lblSolution);
		
		lblNull = new JLabel("[-1]");
		panel_9.add(lblNull);
		
		this.grille = new JLayeredPane();
		panel.add(grille, BorderLayout.CENTER);
		grille.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JLabel lblEntrepot = new JLabel("Entrepot :");
		lblEntrepot.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lblEntrepot);
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Dimensions");
		lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_2.add(lblNewLabel);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(2, 2, 0, 0));
		
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
		maxObs = (Integer)lag.getValue()*(Integer)lng.getValue()/4;
		
		JLabel lblNewLabel_1 = new JLabel("Obstacles :");
		lblNewLabel_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_2.add(lblNewLabel_1);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblObstacles = new JLabel("Maximum :");
		panel_7.add(lblObstacles);
				
				lbmax = new JLabel(""+maxObs);
				panel_7.add(lbmax);
				
				JLabel lbcurzefzef = new JLabel("Courant :");
				panel_7.add(lbcurzefzef);
				
				lbcur = new JLabel("0");
				panel_7.add(lbcur);

		
				
		
		
		JLabel lblRobot = new JLabel("Robot :");
		lblRobot.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lblRobot);
		
		JLabel lblPosition = new JLabel("Position :");
		lblPosition.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_3.add(lblPosition);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new GridLayout(3, 2, 0, 0));
		
		maxX = (Integer)lng.getValue();
		maxY = (Integer)lag.getValue();
		maxObs = (Integer)lag.getValue()*(Integer)lng.getValue()/4;
		
		JLabel lblX = new JLabel("X :");
		panel_5.add(lblX);
		
		rdx = new JSpinner();
		rdx.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				robot.setposX((int)rdx.getValue());
				calcRatio();				
				robot.setBounds(sy*robot.getposY()+5,sx*robot.getposX()+5,  sy, sx);
				robot.repaint();
				updateInstance();
				cleanPath();
			}
		});
		rdx.setModel(new SpinnerNumberModel(0, 0, maxX, 1));
		panel_5.add(rdx);
		
		JLabel lblY = new JLabel("Y :");
		panel_5.add(lblY);
		
		rdy = new JSpinner();
		rdy.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				robot.setposY((int)rdy.getValue());
				calcRatio();	
				
				robot.setBounds(sy*robot.getposY()+5,sx*robot.getposX()+5,  sy, sx);
				robot.repaint();
				updateInstance();
				cleanPath();
			}
		});
		rdy.setModel(new SpinnerNumberModel(0, 0, maxY, 1));
		panel_5.add(rdy);
		
		JLabel lblOrientation = new JLabel("orientation :");
		panel_5.add(lblOrientation);
		
		ori = new JComboBox();
		ori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println((String)ori.getSelectedItem());
				robot.rotate((String)ori.getSelectedItem());
				robot.repaint();
				updateInstance();
				cleanPath();
			}
		});
		ori.setModel(new DefaultComboBoxModel(new String[] {"sud", "nord", "est", "ouest"}));
		panel_5.add(ori);
		
		JLabel lblObjectif = new JLabel("Objectif :");
		lblObjectif.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_3.add(lblObjectif);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblX_1 = new JLabel("X :");
		panel_6.add(lblX_1);
		
		rox = new JSpinner();
		rox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				objectif.setposX((int)rox.getValue());
				calcRatio();	
				//System.out.println("objx : "+objectif.getposX());
				objectif.setBounds(sy*objectif.getposY()+5,sx*objectif.getposX()+5,  sy, sx);
				objectif.repaint();
				updateInstance();
				cleanPath();
			}
		});
		rox.setModel(new SpinnerNumberModel(10, 0, maxX, 1));
		panel_6.add(rox);
		
		JLabel lblY_1 = new JLabel("Y :");
		panel_6.add(lblY_1);
		
		roy = new JSpinner();
		roy.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				cleanPath();
				objectif.setposY((int)roy.getValue());
				calcRatio();	
				//System.out.println("objx : "+objectif.getposY());
				objectif.setBounds(sy*objectif.getposY()+5,sx*objectif.getposX()+5,  sy, sx);
				objectif.repaint();
				updateInstance();
			}
		});
		roy.setModel(new SpinnerNumberModel(10, 0, maxY, 1));
		panel_6.add(roy);
		
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
	    tf.setEditable(false);
	    tf.setBackground(Color.white);
	    tf = ((JSpinner.DefaultEditor) lng.getEditor()).getTextField();
	    tf.setEditable(false);
	    tf.setBackground(Color.white);
	    tf = ((JSpinner.DefaultEditor) lag.getEditor()).getTextField();
	    tf.setEditable(false);
	    tf.setBackground(Color.white);
	    
	  //Listeners
	  		lng.addChangeListener(new ChangeListener() {
	  			public void stateChanged(ChangeEvent arg0) {
	  				maxObs = (Integer)lag.getValue()*(Integer)lng.getValue()/4;
	  				maxX = (Integer)lng.getValue();
	  				int curdx = (int) rdx.getValue(); 
	  				int curox = (int) rox.getValue(); 
	  				lblNull.setText("[-1]");

	  				if(maxX < curdx)
	  					curdx = maxX;
	  				if(maxX < curox)
	  					curox = maxX;
	  				
	  				lbmax.setText(""+maxObs);
	  				rdx.setModel(new SpinnerNumberModel(curdx, 0, maxX, 1));
	  				rox.setModel(new SpinnerNumberModel(curox, 0, maxX, 1));
	  				JFormattedTextField tf = ((JSpinner.DefaultEditor) rdx.getEditor()).getTextField();
	  			    tf.setEditable(false);
	  			    tf.setBackground(Color.white);
	  				tf = ((JSpinner.DefaultEditor) rox.getEditor()).getTextField();
	  			    tf.setEditable(false);
	  			    tf.setBackground(Color.white);
	  			    tf.setEditable(false);
	  			    tf.setBackground(Color.white);
	  			    
	  			  updateMat();
	  			  updateInstance();
	  			    DrawGrille();
	  			}
	  		});
	  		lag.addChangeListener(new ChangeListener() {
	  			public void stateChanged(ChangeEvent e) {
	  				maxObs = (Integer)lag.getValue()*(Integer)lng.getValue()/4;
	  				maxY = (Integer)lag.getValue();
	  				int curdy = (int) rdy.getValue(); 
	  				int curoy = (int) roy.getValue(); 
	  				lblNull.setText("[-1]");
	  				if(maxY < curdy)
	  					curdy = maxY;
	  				if(maxY < curoy)
	  					curoy = maxY;

	  				lbmax.setText(""+maxObs);
	  				rdy.setModel(new SpinnerNumberModel(curdy, 0, maxY, 1));
	  				roy.setModel(new SpinnerNumberModel(curoy, 0, maxY, 1));
	  				JFormattedTextField tf = ((JSpinner.DefaultEditor) rdy.getEditor()).getTextField();
	  			    tf.setEditable(false);
	  			    tf.setBackground(Color.white);
	  				tf = ((JSpinner.DefaultEditor) roy.getEditor()).getTextField();
	  			    tf.setEditable(false);
	  			    tf.setBackground(Color.white);

	  			    tf.setEditable(false);
	  			    tf.setBackground(Color.white);
	  			    updateMat();
	  			    updateInstance();
	  			    DrawGrille();
	  			}
	  		});
	}
}
