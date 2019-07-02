import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class Ticket {

	private int counter = 100;
	private String pnr = new String();
	private Date travelDate;
	private Train train;
	private TreeMap<Passenger,Integer> passengers = new TreeMap<Passenger,Integer>();
	
	
	public Ticket(Date d, Train t) {
		this.setTrain(t);
		this.setTravelDate(d);
	}
	
	public String generatePNR() {
		SimpleDateFormat d1 = new SimpleDateFormat("ddMMyyyy");
		String newdate = d1.format(travelDate);
		String s = (this.getTrain().getSource()).substring(0,1);
		String d = (this.getTrain().getDestination()).substring(0,1);

		String result = s + "_" + d + "_" + newdate + "_" + counter;
		return result;
	}
	
	
	public double calculatePassengerfare(Passenger p) {
		char f = 'f';
		double regularfare=this.getTrain().getTicketPrice();

		if(p.getAge()<=12) {
			regularfare= (regularfare *.5);
		}

		else if(p.getAge()>=60) {
			regularfare = (regularfare *.6);
		}

		else if(p.getGender()==f) {
			regularfare= (regularfare *.75);
		}

			return regularfare;
				
				
	}
	
	

	public void addPassengerfare(String s,int i,char c) {
		Passenger p1 = new Passenger(s,i,c);
		passengers.put(p1,(int)(this.calculatePassengerfare(p1)));
		this.counter++;
	}
	
	public double calculateTotalTicketPrice() {
		double sum =0;

		for (Map.Entry <Passenger,Integer>m:passengers.entrySet()) {
		sum += Double.parseDouble(""+m.getValue());
		}
		return sum;
	}
	
	public StringBuilder generateTicket() {
		StringBuilder strB = new StringBuilder();
		SimpleDateFormat d1 = new SimpleDateFormat("dd/MM/yyyy");
		String date = d1.format(travelDate);
		
		strB.append("PNR        :  " + this.pnr);
		strB.append("\n");
		strB.append("Train no   :  " + this.getTrain().getTrainNo());
		strB.append("\n");
		strB.append("Train Name :  " + this.getTrain().getTrainName());
		strB.append("\n");
		strB.append("From       :  " + this.getTrain().getSource());
		strB.append("\n");
		strB.append("To         :  " + this.getTrain().getDestination());
		strB.append("\n");
		strB.append("Travel Date:  " + date);
		strB.append("\n");
		strB.append("\n");
		strB.append("Passengers : ");
		strB.append("\n");
		strB.append("Name \t Age \t Gender \t Fare");
		strB.append("\n");
		
		for (Map.Entry <Passenger,Integer>m:passengers.entrySet()) {
			strB.append(m.getKey().getName() + "\t");
			strB.append(m.getKey().getAge() + "\t");
			strB.append(m.getKey().getGender() + "\t $");
			strB.append(m.getValue());
			strB.append("\n");
		}
		
		strB.append("Total Price: $" + this.calculateTotalTicketPrice());
		
		return strB;
	}
	
	public void WriteTicket() {
		try{    
				
	           FileWriter fw=new FileWriter("ticket.txt");    
	           fw.write(this.generateTicket().toString());
	           fw.close();    
	          }catch(Exception e){System.out.println(e);}    
	           
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}
	
	public static void main(String[] args) {
		Date travelDate = new Date();
		Train train = new Train();
		Ticket ticket = new Ticket(travelDate, train);

		ticket.addPassengerfare("Dana", 30,(char)'f');
		ticket.addPassengerfare("Dan", 25,(char)'m');
		ticket.setPnr(ticket.generatePNR());
		
		System.out.println(ticket.generateTicket());
		ticket.WriteTicket();
	}
}