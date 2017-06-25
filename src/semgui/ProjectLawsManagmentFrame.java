package semgui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.List;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JEditorPane;
import javax.swing.JTextArea;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import org.jfree.chart.JFreeChart;

import semmainengine.MainEngine;
import semoutputmanager.ReportFactory;

import java.awt.Dimension;


public class ProjectLawsManagmentFrame extends JFrame {

	private JPanel contentPane;
	private String title = "Software Evolution Monitor";
	private JFrame mainWindowFrame;
	private JFrame lawEvaluationFrame;
	private MainEngine engine;
	private String lawName;
	private JComboBox txtHtmlComboBox ;
	private JButton lhemanLaw1Button, lhemanLaw2Button, lhemanLaw3Button ;
	private JButton lhemanLaw4Button, lhemanLaw5Button, lhemanLaw6Button ;
	private JButton lhemanLaw7Button, lhemanLaw8Button;
	
	private void createLehmanLawWindow(int lawId){
		List <JFreeChart> charts;
		charts = engine.getGraphsForSpecificLaw(lawId);

		lawEvaluationFrame = new JFrame("Lehman Law: " + lawId +" " + lawName);
		lawEvaluationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lawEvaluationFrame.getContentPane().setLayout(new GridLayout(1, 0));
		for(int i = 0; i < charts.size(); i++)
        	lawEvaluationFrame.getContentPane().add(GraphWindow.createNewGraphWindowWithCharts(charts.get(i)));
        GraphWindow userDesicionWindow = GraphWindow.createNewGraphWindowWithLawEvaluationDecisions();
        userDesicionWindow.setProjectLawsManagmentFrame(this);
        userDesicionWindow.setEvaluatedLawId(lawId);        
        userDesicionWindow.setLawName(lawName);
        lawEvaluationFrame.getContentPane().add(userDesicionWindow);
  
        lawEvaluationFrame.pack();
        lawEvaluationFrame.setSize((charts.size()+1)*480, 450);
        setVisible(false);
        lawEvaluationFrame.setVisible(true);	
	}
	
	private void createLehmanLawWindowSeven(){
		lawEvaluationFrame = new JFrame("Evaluation of Lehman Law: 7");		
		lawEvaluationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lawEvaluationFrame.getContentPane().setLayout(new GridLayout(1, 0));
		String lawTwoDecision = engine.getLawDecisionForCurrentProject(2);
		String lawSixDecision = engine.getLawDecisionForCurrentProject(6);
		String lawSevenDecision;
		
		if(lawTwoDecision.equals("Valid") && lawSixDecision.equals("Valid"))
			lawSevenDecision = "Valid";
		else
			lawSevenDecision = "Not Valid";
		
        GraphWindow userDesicionWindow = GraphWindow.createNewGraphWindowWithLawSevenEvaluation(lawSevenDecision);
        userDesicionWindow.setProjectLawsManagmentFrame(this);
        userDesicionWindow.setEvaluatedLawId(7);
        userDesicionWindow.setLawName(lawName);
        userDesicionWindow.setSize(new Dimension(400,300));
        
        lawEvaluationFrame.getContentPane().add(userDesicionWindow);
        lawEvaluationFrame.pack();
        lawEvaluationFrame.setSize(400, 300);
        setVisible(false);
        lawEvaluationFrame.setVisible(true);	
	}	
	
	public void hideLehmanLawWindow(){
		lawEvaluationFrame.setVisible(false);
		setVisible(true);
	}
	
	public void saveLehmanLawEvaluation(int lawId,String userDecision,String userComment, String lawName){
		engine.saveLehmanLawEvaluation(lawId, userDecision, userComment, lawName);
	}
	
	public ProjectLawsManagmentFrame(JFrame frame,MainEngine mainEngine) {
		this.engine = mainEngine;
		this.mainWindowFrame = frame;
		setBackground(new Color(255, 192, 203));
		setForeground(new Color(0, 191, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 536);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(300, 300));
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setForeground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("In this windows you can choose specific Lheman Laws and see if they respond to project.");
		
		lhemanLaw1Button = new JButton("Lheman Law 1");
		lhemanLaw1Button.setToolTipText("Continuing Change");

		lhemanLaw1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //LehMan Law 1
				lawName = lhemanLaw1Button.getToolTipText();	
				createLehmanLawWindow(1);
			}
		});
		
		lhemanLaw2Button = new JButton("Lheman Law 2");
		lhemanLaw2Button.setToolTipText("Increasing Complexity");	
		lhemanLaw2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lawName = lhemanLaw2Button.getToolTipText();	
				createLehmanLawWindow(2);
			}
		});
		
		lhemanLaw3Button = new JButton("Lheman Law 3");
		lhemanLaw3Button.setToolTipText("Self Regulation");
	
		lhemanLaw3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lawName = lhemanLaw3Button.getToolTipText();		
				createLehmanLawWindow(3);
			}
		});
		
		lhemanLaw4Button = new JButton("Lheman Law 4");
		lhemanLaw4Button.setToolTipText("Conservation of Organisational Stability ");
		
		lhemanLaw4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lawName = lhemanLaw4Button.getToolTipText();	
				createLehmanLawWindow(4);
			}
		});		
		lhemanLaw5Button = new JButton("Lheman Law 5");
		lhemanLaw5Button.setToolTipText("Conservation of Familiarity");
		lawName = lhemanLaw5Button.getToolTipText();		
		lhemanLaw5Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lawName = lhemanLaw5Button.getToolTipText();
				createLehmanLawWindow(5);
			}
		});		
		lhemanLaw6Button = new JButton("Lheman Law 6");
		lhemanLaw6Button.setToolTipText("Continuing Growth");

		lhemanLaw6Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lawName = lhemanLaw6Button.getToolTipText();		
				createLehmanLawWindow(6);
			}
		});		
		lhemanLaw7Button = new JButton("Lheman Law 7");
		lhemanLaw7Button.setToolTipText("Declining Quality");

		lhemanLaw7Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(! engine.isLawSevenEvaluatable() ){
					WarningDialog warning = new WarningDialog(
					"(*) Please evaluate LehmanLaw 2 and LehManLaw 6");
					warning.setVisible(true);
					warning.setTitle("SEM: " + engine.getCurrentProjectName());
					return;
				}
				lawName = lhemanLaw7Button.getToolTipText();		
				createLehmanLawWindowSeven();
			}
		});		
		lhemanLaw8Button = new JButton("Lheman Law 8");
		lhemanLaw8Button.setToolTipText("Feedback System");
		lhemanLaw8Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lawName = lhemanLaw8Button.getToolTipText();
				createLehmanLawWindow(8);
			}
		});		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mainWindowFrame.setVisible(true);
			}
		});
		
		JButton ReportButton = new JButton("Produce Report");
		ReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				engine.initOutputWriter((String)txtHtmlComboBox.getSelectedItem());
				engine.writeReportForCurrentProject();
			}
		});
		
		JLabel lblForMoreInformation = new JLabel("For more information about Lheman Laws press here(Wikipedia)");
		lblForMoreInformation.setForeground(Color.BLUE);
		lblForMoreInformation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		
		lblForMoreInformation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
				final URI uri = new URI("http://en.wikipedia.org/wiki/Lehman's_laws_of_software_evolution");
				if (Desktop.isDesktopSupported()) 
				     Desktop.getDesktop().browse(uri);
				}catch(Exception exception  ){
					System.out.println(exception.getMessage());
				}
			}
		});
		JLabel lhemanDescriptionLabel = new JLabel("");
		
		JTextArea txtrInSoftwareEngineering = new JTextArea();
		txtrInSoftwareEngineering.setBackground(new Color(240, 255, 255));
		txtrInSoftwareEngineering.setText("In software engineering, the laws of"
				+ " software\r\nevolution refer to a series of laws that\r\n"
				+ "Lehman and Belady formulated starting in 1974 \r\n"
				+ "with respect to software evolution.\r\n"
				+ "The laws describe a balance between \r\n"
				+ "forces driving new developments on one \r\n"
				+ "hand, and forces that slow down \r\n"
				+ "progress on the other hand.");
		String txtOrHtmlChoises[]= {"Txt","Html"};
		 txtHtmlComboBox = new JComboBox(txtOrHtmlChoises);
		
		JLabel lblNewLabel_1 = new JLabel("Txt or Html Report?");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(250)
					.addComponent(lhemanDescriptionLabel)
					.addContainerGap(337, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(okButton))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(19)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lhemanLaw5Button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lhemanLaw4Button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addComponent(lhemanLaw3Button, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
									.addComponent(lhemanLaw2Button, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
									.addComponent(lhemanLaw1Button, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
									.addComponent(lhemanLaw6Button, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
									.addComponent(lhemanLaw7Button, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
									.addComponent(lhemanLaw8Button, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblForMoreInformation)
											.addComponent(txtrInSoftwareEngineering, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(6))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(131)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNewLabel_1)
											.addComponent(txtHtmlComboBox, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
											.addComponent(ReportButton))
										.addGap(100))))))
					.addGap(166))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(lhemanDescriptionLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lhemanLaw1Button)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lhemanLaw2Button)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lhemanLaw3Button)
							.addGap(18)
							.addComponent(lhemanLaw4Button)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lhemanLaw5Button))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(txtrInSoftwareEngineering, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lhemanLaw6Button)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lhemanLaw7Button))
						.addComponent(lblForMoreInformation))
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lhemanLaw8Button)
							.addGap(33))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtHtmlComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ReportButton)
							.addGap(6)))
					.addGap(12)
					.addComponent(okButton)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(329, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addGap(117))
		);
		contentPane.setLayout(gl_contentPane);
		setTitle(title + " : " + engine.getCurrentProjectName());
	}
}
