import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class TicketApplication{
    static int step = 1;
    static int passenger = 0;
    static int passengerAmt = 1;
    static Date travelDate;
    static Train train;
    static Ticket ticket;
    public static void main(String args[]){
    	
    	//Instantiation of outer window and ui elements
       JFrame main = new JFrame("Train Ticket System");
       JPanel layoutPanel = new JPanel();
       JPanel trainInputPanel = new JPanel();
       JPanel passengerInputPanel = new JPanel();
       JPanel instructionPanel = new JPanel();
       JLabel instructionLabel = new JLabel();
       
       //Instantiation of train input components
       JPanel trainNumberPanel = new JPanel();
       JLabel trainNumberInputLabel = new JLabel("Train #:");
       JTextField trainNumberText = new JTextField();
       
       JPanel trainDatePanel = new JPanel();
       JLabel trainDateInputLabel = new JLabel("Train Date (dd/MM/yyyy):");
       JTextField trainDateText = new JTextField();
       
       JPanel trainPassengersPanel = new JPanel();
       JLabel trainPassengersInputLabel = new JLabel("Number of Passengers:");
       JTextField trainPassengersText = new JTextField();
       
       //Instantiation of passenger input components
       JPanel passengerNamePanel = new JPanel();
       JLabel passengerNameInputLabel = new JLabel("Name:");
       JTextField passengerNameText = new JTextField();
       
       JPanel passengerAgePanel = new JPanel();
       JLabel passengerAgeInputLabel = new JLabel("Age:");
       JTextField passengerAgeText = new JTextField();
       
       JPanel passengerGenderPanel = new JPanel();
       JLabel passengerGenderInputLabel = new JLabel("Gender(M/F):");
       JTextField passengerGenderText = new JTextField();
       
       //Give the main window formatting and exit functionality
       main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       main.setSize(550,350);
       
       layoutPanel.setLayout(new BorderLayout());
       
       //Format and add user instructions
       instructionLabel.setText("Please enter your Train #, Travel Date, and the amount of Passengers boarding.");
       instructionPanel.add(instructionLabel);
       layoutPanel.add(instructionPanel, BorderLayout.NORTH);
       
       trainInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
       
       //Adding train info labels and textfields to trainInputPanel to prepare for addition to layout
       trainNumberText.setColumns(5);
       trainNumberPanel.add(trainNumberInputLabel);
       trainNumberPanel.add(trainNumberText);
       
       trainDateText.setColumns(10);
       trainDatePanel.add(trainDateInputLabel);
       trainDatePanel.add(trainDateText);
       
       trainPassengersText.setColumns(3);
       trainPassengersPanel.add(trainPassengersInputLabel);
       trainPassengersPanel.add(trainPassengersText);
       
       trainInputPanel.setLayout(new BoxLayout(trainInputPanel, BoxLayout.PAGE_AXIS));
       trainInputPanel.add(trainNumberPanel);
       trainInputPanel.add(trainDatePanel);
       trainInputPanel.add(trainPassengersPanel);
       trainInputPanel.add(Box.createHorizontalGlue());
       
       //Adding passenger info labels and textfields to passengerInputPanel to prepare for addition to layout
       passengerNameText.setColumns(20);
       passengerNamePanel.add(passengerNameInputLabel);
       passengerNamePanel.add(passengerNameText);

       passengerAgeText.setColumns(3);
       passengerAgePanel.add(passengerAgeInputLabel);
       passengerAgePanel.add(passengerAgeText);
       
       passengerGenderText.setColumns(2);
       passengerGenderPanel.add(passengerGenderInputLabel);
       passengerGenderPanel.add(passengerGenderText);
       
       passengerInputPanel.setLayout(new BoxLayout(passengerInputPanel, BoxLayout.PAGE_AXIS));
       passengerInputPanel.add(passengerNamePanel);
       passengerInputPanel.add(passengerAgePanel);
       passengerInputPanel.add(passengerGenderPanel);
       passengerInputPanel.add(Box.createHorizontalGlue());
       
       layoutPanel.add(trainInputPanel, BorderLayout.CENTER);
       
       JTextPane ticketPane = new JTextPane();
       ticketPane.setEditable(false);
       
       //Button to process inputs
       JButton button = new JButton("Find Train");
       button.addActionListener(new ActionListener(){  
    	   public void actionPerformed(ActionEvent e){
    		   //Switch case to cycle button functions using the step variable
	    		   switch(step) {
	    		   //If on step 1
	    		   case 1:
	    			   //Search for train number in database and perform data validation on input.
	    			   TrainDAO trainConnection = new TrainDAO();
	    			   train = trainConnection.findTrain(Integer.parseInt(trainNumberText.getText()));
	    			   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    			   Date date = new Date();
	    			   try {
	    				   //Turn date input into Date format
	    				   travelDate = formatter.parse(trainDateText.getText());
		    			   passengerAmt = Integer.parseInt(trainPassengersText.getText());
	    				   
	    			   } catch (ParseException e1) {
	    				   //If formatter throws exception, date was not in the correct format, and give error window
	    				   JOptionPane.showMessageDialog(main,
	    						    "Date not recognized. Please enter in dd/MM/yyyy format.",
	    						    "Error",
	    						    JOptionPane.ERROR_MESSAGE);
	    			   } catch(NumberFormatException n1) {
	    				   //If parseInt throws exception, 'Number of Passengers' field was not a number, and give error window
	    				   JOptionPane.showMessageDialog(main,
	    						    "Please enter a number in the 'Number of Passengers' field",
	    						    "Error",
	    						    JOptionPane.ERROR_MESSAGE);
	    			   }
	    			   if(train == null) {
	    				   //If Train object is null, no train with given train number was found, and give error window
	    				   JOptionPane.showMessageDialog(main,
	    						    "Train with given number does not exist.",
	    						    "Error",
	    						    JOptionPane.ERROR_MESSAGE);
	    				   break;
	    			   }
	    			   else if(travelDate.compareTo(date) < 0){
	    				   //If given date is before the current date, give error window
	    				   JOptionPane.showMessageDialog(main,
	    						    "Travel Date has already passed.",
	    						    "Error",
	    						    JOptionPane.ERROR_MESSAGE);
	    				   break;
	    			   }

	    			   //Reformat button and instructions to match passenger input as well as replace the panel for train input with one for passenger input
	    			   ticket = new Ticket(travelDate, train);
	    			   button.setText("Add Passenger");
	                   instructionLabel.setText("Enter Passenger #" + (passenger+1) + "'s Info");
	                   layoutPanel.remove(trainInputPanel);
	                   layoutPanel.add(passengerInputPanel);
	                   main.repaint();
	                   passenger++;
	                   step++;
	                   
	                   break;
	               //If on step 2
	    		   case 2:
	    			   //Increment instruction passenger #, add passenger to ticket, and clear passenger textfields
	                   instructionLabel.setText("Enter Passenger #" + (passenger+1) + "'s Info");
	                   ticket.addPassengerfare(passengerNameText.getText(), Integer.parseInt(passengerAgeText.getText()), passengerGenderText.getText().charAt(0));
	                   passengerNameText.setText(null);
	                   passengerAgeText.setText(null);
	                   passengerGenderText.setText(null);
	                   main.repaint();
	                   passenger++;
	                   break;
	               //If on step 3
	    		   case 3:
	    			   //Add last passenger to ticket, generate the ticket PNR, print the ticket to the GUI, and write the ticket to file
	    			   ticket.addPassengerfare(passengerNameText.getText(), Integer.parseInt(passengerAgeText.getText()), passengerGenderText.getText().charAt(0));
	    			   ticket.setPnr(ticket.generatePNR());
	    			   ticketPane.setText(
	    					   ticket.generateTicket().toString()
					   );
	    			   button.setText("Exit");
	    			   layoutPanel.remove(passengerInputPanel);
	    			   layoutPanel.add(ticketPane);
	    			   instructionLabel.setText("<html><body>Here's your ticket! A copy has also been saved at<br>"+ System.getProperty("user.dir") + "\\ticket.txt</body></html>");
	                   main.repaint();
	                   ticket.WriteTicket();
	                   step++;
	    			   break;
    			   //If on step 4
	    		   case 4:
	    			   //Close the program
	    			   System.exit(0);
	    		   }
	    		   if(passenger==passengerAmt) {
	    			   if(step<3) {
	    				   button.setText("Generate Ticket");
	        			   step++;
		                   main.repaint();
	    			   }
        		   }
    		   
           }  
       });  
       layoutPanel.add(button, BorderLayout.PAGE_END);
       
       layoutPanel.add(trainInputPanel, BorderLayout.CENTER);
       
       main.getContentPane().add(layoutPanel);
       main.setVisible(true);
    }
}