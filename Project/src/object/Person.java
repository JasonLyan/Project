package object;

import java.util.ArrayList;

public class Person {
	private String firstName;
	private String middleName;
	private String lastName;
	public static int MAX_ALLOWED_BOOKS = 3;
	private ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
	private boolean male;
	private Balance balance;

	public Balance getBalance() {
		return balance;
	}

	//no return type: it "returns" a Person
	public Person(String firstName, String lastName, boolean male){
		this.firstName = firstName;
		middleName = "";
		this.lastName = lastName; //"this" is used to specify the field variable.
		ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
		this.male = male;
		balance = new Balance();
	}
	
	//constructor for middle-named people
	public Person(String firstName, String middleName, String lastName, boolean male){
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
		this.male = male;
		balance = new Balance();
	}
	
	public void checkOutBook(Book b){
		b.setCheckedOut(true);
		b.setCheckOutDate(System.currentTimeMillis());
		b.setDueDate(System.currentTimeMillis()+30000);
		this.checkedOutBooks.add(b);
	}
	
	public void returnBook(Book b){
		b.setCheckedOut(false);
		b.updateCondition(System.currentTimeMillis());
		balance.subtractLateFees((int)(System.currentTimeMillis() - b.getDueDate()));
		b.setCheckOutDate(0);
		b.setDueDate(0);
		this.checkedOutBooks.remove(b);
	}
	
	public void renewBook(Book Book){
		Book.setDueDate(System.currentTimeMillis()+30000);
	}
	
	public String getGenderPossessivePronoun(Person Person){
		if(Person.isMale() == true){
			return "his";
		}
		return "her";
	}
	
	public String getLibraryDescription(){
		 return getFirstName() + " is viewing the library collection. Their balance is $" + balance.getAmount();
	}
	
	public boolean isMale() {
		return male;
	}
	
	public ArrayList<Book> getCheckedOutBooks() {
		return checkedOutBooks;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}


	public String getLastName() {
		return lastName;
	}

	public String toString(){
		return firstName + " " + middleName + " " + lastName;
	}
}
