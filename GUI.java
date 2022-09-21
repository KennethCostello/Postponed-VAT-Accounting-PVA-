import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.io.PrintWriter; 


public class GUI implements ActionListener {
	
	// declare Jobjects
	private JFrame frame;
	private JPanel panel;
	private JButton cancelButton;
	private JButton saveButton;
	private JLabel yearLabel;
	private JLabel monthLabel;
	private JLabel vatLabel;
	private JLabel amountLabel;
	private JLabel enableLabel;
	private JComboBox<String> yearText;
	private JComboBox<String>  monthText;
	private JTextField vatText;
	private JTextField amountText;
	private JCheckBox enable;
	
	//declare variables
	private int Year;  
	private int Month;  
	private double GoodsAmount;
	private String VATno;
	
	//string variables to convert
	private String sYear;
	private String sMonth;
	private String sAmount;

	//constructor
	static pvaInfo Info = new pvaInfo(0, 0, " ", 0);
	
	
	public GUI() {
		
		//arrays for month list and year list 
		String[] selectMonth = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		String[] selectYear = {"2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030",
				"2031","2032","2033","2034","2035","2036","2037","2038","2039","2040",
				"2041","2042","2043","2044","2045","2046","2047","2048","2049","2050"};
		
		//create frame and panel
		frame = new JFrame();
		panel = new JPanel();
		
		//frame and panel settings 
		frame.setSize(350, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setTitle("Postponed VAT Accounting");
		panel.setLayout(null);
		
		//dropdown box for year
		yearText = new JComboBox<>(selectYear);
		yearText.setBounds(120,20,165,25);
		panel.add(yearText);
		//year label
		yearLabel = new JLabel("Year:");
		yearLabel.setBounds(10, 20, 100, 25);
		panel.add(yearLabel);
		
		//dropdown box for month
		monthText = new JComboBox<>(selectMonth);
		monthText.setBounds(120,60,165,25);
		panel.add(monthText);
		//month label
		monthLabel = new JLabel("Month:");
		monthLabel.setBounds(10, 60, 100, 25);
		panel.add(monthLabel);
		
		//text field for VAT
		vatText = new JTextField();
		vatText.setBounds(120,100,165,25);
		vatText.setText("Please enter VAT Reference"); //default text
		panel.add(vatText);
		// VAT label
		vatLabel = new JLabel("VAT Reference:");
		vatLabel.setBounds(10,100, 100, 25);
		panel.add(vatLabel);
		
		//text field for Goods Amount 
		amountText = new JTextField();
		amountText.setBounds(120,140,165,25);
		amountText.setText("0"); //default 0
		panel.add(amountText);
		//goods amount label
		amountLabel = new JLabel("Goods Amount:");
		amountLabel.setBounds(10,140,100, 25);
		panel.add(amountLabel);
		
		//check box enable
		enable = new JCheckBox("Enable");
		enable.setBounds(210, 170, 20, 20);
		enable.setSelected(true); //default ticked
		panel.add(enable);
		// check box label
		enableLabel = new JLabel("Record VAT for selected month");
		enableLabel.setBounds(10,170, 200, 25);
		panel.add(enableLabel);
		
		//cancel button
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(10, 200, 80, 20);
		panel.add(cancelButton);
		cancelButton.addActionListener(this);
		cancelButton.setActionCommand("cancel");
		
		//save button
		saveButton = new JButton("Save");
		saveButton.setBounds(240, 200, 80, 20);
		panel.add(saveButton);
		saveButton.addActionListener(this);
		if (enable.isSelected()) {
			//if enabled ticked save with VAT
			saveButton.setActionCommand("save with VAT");
		} else {
			//if enabled not ticked save without VAT
			saveButton.setActionCommand("save without VAT");
		}		
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//get action from button press
		String actionCommand = ((JButton) e.getSource()).getActionCommand(); 
	    switch (actionCommand) {
	        case "save with VAT": 
	        	saveInfo(); //method to save with VAT
	        	printInfo(); //method to print to file
		        JOptionPane.showMessageDialog(null, 
		        		"File has been saved", "File Saved", JOptionPane.INFORMATION_MESSAGE);
	        break;
	        case "save without VAT": 
	        	saveInfoExVAT(); //method to save without VAT
	        	printInfo(); //method to print to file
		        JOptionPane.showMessageDialog(null, 
		        		"File has been saved", "File Saved", JOptionPane.INFORMATION_MESSAGE);
	        break;
	        case "cancel": 
	         int confirmed = JOptionPane.showConfirmDialog(null, 
	        	 "Are you sure you want to exit the program?", "Exit Program Message Box",
	        	 JOptionPane.YES_NO_OPTION); //popup confirm box 
	        	 if (confirmed == JOptionPane.YES_OPTION) {
	        		 System.exit(0); //if yes close program 
	        	    }
	        	 else {
	                 frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // if not do nothing 
	             }
	        break;
		}  
	}
	
	public void saveInfo() {
		//saving info with VAT input 
		sYear = yearText.getSelectedItem().toString(); //get string from list
		Year = Integer.parseInt(sYear); //string to int 
		Info.setYear(Year); //set year in pvainfo
		
		sMonth = monthText.getSelectedItem().toString(); //get string from list
		Month = Integer.parseInt(sMonth); //string to int 
		Info.setMonth(Month); //set month in pvainfo
		
		VATno = vatText.getText(); //get string from text field 
		Info.setVAT(VATno); //set VAT in pvainfo
		
		sAmount = amountText.getText(); //get string from text field 
		GoodsAmount = Double.parseDouble(sAmount); //string to double
		Info.setAmount(GoodsAmount); //set goods in pvainfo
	}
	
	public void saveInfoExVAT() {
		sYear = yearText.getSelectedItem().toString();//get string from list
		Year = Integer.parseInt(sYear); //string to int 
		Info.setYear(Year); //set year in pvainfo
		
		sMonth = monthText.getSelectedItem().toString(); //get string from list
		Month = Integer.parseInt(sMonth); //string to int 
		Info.setMonth(Month); //set month in pvainfo
		
		VATno = "N/A"; //set VAT N/A
		Info.setVAT(VATno); //set in pvainfo
		
		GoodsAmount = 0; //set goods to 0
		Info.setAmount(GoodsAmount); //set in pvainfo
	}
	public void printInfo() {
		try {
			//try print to file VAT in temp folder 
			  PrintWriter out = new PrintWriter("C:\\temp\\VAT.txt");
			  //print values to file
			  out.printf("Year: %d\n", Info.getYear());
			  out.printf("Month: %d\n", Info.getMonth());
			  out.printf("VAT Reference: %s\n", Info.getVAT());
			  out.printf("Goods Amount: %.2f\n", Info.getAmount());
			  //close file
		      out.close();
		      //print success 
		      System.out.println("Successfully wrote to the file.");	
		}catch(Exception e) { // catch exception if unable to write to file
			System.out.println("An error occurred."); //print error message
		      e.printStackTrace();
		}
	}
		
	public static void main(String[] args) {
		 new GUI(); // call GUI 
		
	}
}
