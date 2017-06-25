package semgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;

import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultValueDataset;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.Font;


public class GraphWindow extends JPanel {

    private static  JFrame f;
    private ProjectLawsManagmentFrame lawManagment;
    private JLabel lblErrorMessage;
    private JTextField textField;
    private JComboBox comboBox;    
    private String userComment;
    private String userDecision;
    private int lawId;
    private String lawName;
    
    public static GraphWindow createNewGraphWindowWithCharts(JFreeChart chart){
    	return new GraphWindow(chart);
    }
    
    public void setLawName(String lawName){
    	this.lawName = lawName;
    }
    
    private GraphWindow(JFreeChart chart) {
        this.setLayout(new GridLayout());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(460, 300));  
        JPanel panel = new JPanel();
        add(panel);
        panel.add(chartPanel);
        lawId = 0;
    }
    
    public void setProjectLawsManagmentFrame(ProjectLawsManagmentFrame lawManagmentFrame){
        this.lawManagment = lawManagmentFrame;
    }
    
    public void setEvaluatedLawId(int lawId){
    	this.lawId = lawId;
    }
    
    public static GraphWindow createNewGraphWindowWithLawEvaluationDecisions(){
    	return new GraphWindow();
    }

    private GraphWindow() {
        lawId = 0;
        this.setLayout(new GridLayout());   
        final JPanel panel = new JPanel();
        add(panel);
        JLabel chooseVaildLabel = new JLabel("Choose if this law is valid ");
        JButton btnNewButton = new JButton("ok");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		userDecision =(String) comboBox.getSelectedItem();
        		
        		if(userDecision.equals("-")){
        	        lblErrorMessage.setVisible(true);
        			return;
        		}
        		userComment = textField.getText();
        		lawManagment.hideLehmanLawWindow();
        		lawManagment.saveLehmanLawEvaluation(lawId, userDecision,userComment,lawName);
        	}
        });
        
        textField = new JTextField();
        textField.setColumns(10);
        String[] comboBoxArray = {"-","Valid","Not Valid","Not clear"};
        comboBox = new JComboBox(comboBoxArray);
        JLabel lblWriteAComment = new JLabel("Write a comment about your decision");
        lblErrorMessage = new JLabel("(*) Please select an option.");
        lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblErrorMessage.setVisible(false);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
        				.addComponent(chooseVaildLabel)
        				.addComponent(lblErrorMessage)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(186)
        					.addComponent(btnNewButton)))
        			.addGap(232))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(textField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(232, Short.MAX_VALUE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblWriteAComment)
        			.addContainerGap(134, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(9)
        			.addComponent(chooseVaildLabel)
        			.addGap(14)
        			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lblErrorMessage)
        			.addGap(30)
        			.addComponent(lblWriteAComment)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textField, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
        			.addGap(36)
        			.addComponent(btnNewButton)
        			.addContainerGap(60, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
    }
    
    
    public static GraphWindow createNewGraphWindowWithLawSevenEvaluation(final String lawDecision){
    	return new GraphWindow(lawDecision);
    }
    
    private GraphWindow(final String lawDecision) {
        lawId = 0;
        this.setLayout(new GridLayout());
        
        final JPanel panel = new JPanel();
     
        add(panel);
        
        JLabel chooseValidLabel = new JLabel("Choose if this law is valid ");
        chooseValidLabel.setForeground(Color.BLUE);        
        if( lawDecision.equals("Valid") ){
        	chooseValidLabel.setText("Law is: " + lawDecision + 
        							" because Lehman Law 2 and 6 are valid.");
        	userDecision = "Valid";
        }
        else{
        	chooseValidLabel.setText("Law is: " + lawDecision + 
					" because Lehman Law 2 and 6 are not both valid.");  
        	userDecision = "Not Valid";
        }
        
        JButton btnNewButton = new JButton("ok");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		userComment = textField.getText();
        		lawManagment.hideLehmanLawWindow();
        		lawManagment.saveLehmanLawEvaluation(7, userDecision, userComment, lawName);
        	}
        });
        
        textField = new JTextField();
        textField.setColumns(10);
        String[] comboBoxArray = {"-","Valid","Not Valid","Not clear"};
        comboBox = new JComboBox<String>(comboBoxArray);
        comboBox.setVisible(false);
        JLabel lblWriteAComment = new JLabel("Write a comment :");
        lblErrorMessage = new JLabel("(*) Please select an option.");
        lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblErrorMessage.setVisible(false);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
        				.addComponent(chooseValidLabel)
        				.addComponent(lblErrorMessage)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(186)
        					.addComponent(btnNewButton)))
        			.addGap(232))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(textField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(232, Short.MAX_VALUE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblWriteAComment)
        			.addContainerGap(134, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(9)
        			.addComponent(chooseValidLabel)
        			.addGap(14)
        			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lblErrorMessage)
        			.addGap(30)
        			.addComponent(lblWriteAComment)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textField, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
        			.addGap(36)
        			.addComponent(btnNewButton)
        			.addContainerGap(60, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
    }
}