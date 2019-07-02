
public class Passenger implements Comparable<Passenger>{
	
	private int age;
	private String name;
	private char gender;
	
	
	public Passenger() {
		super();
	}
	
	public Passenger(String n,int a,char g) {
		this.age = a;
		this.gender = g;
		this.name =n;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public int compareTo(Passenger other) {
		if(this.getAge() > other.getAge()) return 1; 
	    if(this.getAge() < other.getAge()) return -1;
	    else                   return 0;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
}