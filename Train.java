
public class Train {
	private int trainNo = 0;
	private String trainName = new String();
	private String source = new String();
	private String destination = new String();
	private Double ticketPrice = 0.0;
	
	public Train(int trainNo, String trainName, String source, String destination, double ticketPrice) {
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.source = source;
		this.destination = destination;
		this.ticketPrice = ticketPrice;
	}
	
	public Train() {
		this.trainNo = 1001;
		this.trainName = "Shatabdi Express";
		this.source = "Bangalore";
		this.destination = "Delhi";
		this.ticketPrice = 2500.0;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public String getTrainName() {
		return trainName;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	public String toString() {
		String train = new String();
		train = "Train #:" + this.getTrainNo() + " is called " + this.getTrainName() + ". " + this.getTrainName() + " will be leaving from " + this.getSource() + " heading towards " + this.getDestination() + " for a ticket price of $" + this.getTicketPrice();
		return train;
	}

	public static void main(String[] args) {
		Train train = new Train();
		System.out.println(train);
	}

}
