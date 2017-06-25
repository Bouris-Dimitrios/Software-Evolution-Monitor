package semgui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

import semmainengine.MainEngine;

import java.awt.SystemColor;
import java.io.File;
import java.awt.Dimension;


public class MainWindow {

	private JFrame frame;
	private String title = "Software Evolution Monitor";
	static private MainEngine engine;
	private JLabel browseStatusLbl;
	private JComboBox<String> comboBox;
	private ProjectLawsManagmentFrame lawEvaluationFrame;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					engine = new MainEngine();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		initialize();
	}
	
	private void updateComboBoxOptions(){
		String[] projects = engine.getProjectNames();
		comboBox.removeAllItems();
		for(int i = 0; i < projects.length; i++)
			comboBox.addItem(projects[i]);		
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setMinimumSize(new Dimension(300, 300));
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setBounds(100, 100, 436, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblWelcomeToSotware = new JLabel("Welcome to Sotware Evolution Monitor.Please load a Project to begin.");
		
		final JButton browseProjectButton = new JButton("Browse Project");
		browseProjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFileChooser fc = new JFileChooser();
				    if (e.getSource() == browseProjectButton) {
				        int returnVal = fc.showOpenDialog(null);

				        if (returnVal == JFileChooser.APPROVE_OPTION) {
				            File file = fc.getSelectedFile();
				            browseStatusLbl.setVisible(true);	
				    		browseStatusLbl.setForeground(Color.RED);
				    		
				            if( ! engine.initializeInputParser(file) ){
				            	browseStatusLbl.setText("(*) wrong file type.");			            	
				            	return;
				            }
				            
				            int fileReadStatus = engine.readFile();

				            if( fileReadStatus == 1 ){
				            	browseStatusLbl.setText("(*) project already loaded.");			            	
				            	return;
				            }
				            
				            if ( fileReadStatus == 2 ){
				            	browseStatusLbl.setText("(*) wrong file format.");			            	
				            	return;
				            }
				    		browseStatusLbl.setForeground(Color.BLACK);
				            browseStatusLbl.setText("File: " + file.getName() + " loaded.");
				       }
				   }	
				    updateComboBoxOptions(); 
			}
		});
		
		browseProjectButton.setToolTipText("Press this button to load a Project File");
		
		browseStatusLbl = new JLabel("Status");
		browseStatusLbl.setVisible(false);

		
		comboBox = new JComboBox<String>();
		
		JButton okButton = new JButton("ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userChoice = (String) comboBox.getSelectedItem();
				if(userChoice == null){
					browseStatusLbl.setForeground(Color.RED);
					browseStatusLbl.setText("(*) Please load a project.");
					browseStatusLbl.setVisible(true);
					return;
				}
				
				engine.chooseCurrentProjectAndComputeMetrics(userChoice);
				lawEvaluationFrame = new ProjectLawsManagmentFrame(frame,engine);
				frame.setVisible(false);
	            browseStatusLbl.setVisible(false);	
				lawEvaluationFrame.setVisible(true);
			}
		});
		
		JLabel lblChooseAProject = new JLabel("Choose a Project and Press Ok");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(lblWelcomeToSotware))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(browseProjectButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblChooseAProject, Alignment.LEADING))
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addComponent(browseStatusLbl))))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWelcomeToSotware)
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(browseProjectButton)
						.addComponent(browseStatusLbl))
					.addGap(34)
					.addComponent(lblChooseAProject)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(okButton))
					.addContainerGap(312, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setTitle(title);
	}
}
