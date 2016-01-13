package object;

import java.awt.Color;

public class Book {
	private String title;
	private int numberOfPages;
	private Person author;//custom class in this package
	private Color jacketColor;//class from java.awt
	private boolean wasLitOnFire;
	private int height;
	private int thickness;
	private boolean checkedOut;
	private long checkOutDate;
	private long dueDate;
	private String description;
	private int accumulatedUse;
	
	public Book (String title, int numOfPages, Person author){
		this.title = title;
		this.numberOfPages = numOfPages;
		this.author = author;
		jacketColor = Color.gray;
		wasLitOnFire = false;
		height = (int) (Math.random() * 100 + 150);
		thickness = numOfPages / 10;
		checkedOut = false;
		checkOutDate = 0;
		dueDate = 0;
		description = conditions[0];
		accumulatedUse = 0;
	}
	
	static public String[] conditions = {"This book is brand new!", "This book is pretty new!", 
		"This book is in OK condition.", "This book is old!"};
	
	public void updateCondition(long timeOfReturn){
		accumulatedUse += (int)((timeOfReturn - checkOutDate)/1000);
		if(accumulatedUse > 30){
			description = conditions[1];
		}
		if(accumulatedUse > 60){
			description = conditions[2];
		}
		if(accumulatedUse > 90){
			description = conditions[3];
		}
	}
	
	public String getDescription() {
		return description;
	}

	public int getAccumulatedUse() {
		return accumulatedUse;
	}

	public static String[] getConditions() {
		return conditions;
	}

	public long getSecondsRemaining(){
		return (dueDate - System.currentTimeMillis()) / 1000;
	}
	
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	public void setCheckOutDate(long checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public void setDueDate(long dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isWasLitOnFire() {
		return wasLitOnFire;
	}

	public boolean isCheckedOut() {
		return checkedOut;
	}

	public long getCheckOutDate() {
		return checkOutDate;
	}

	public long getDueDate() {
		return dueDate;
	}

	public int getHeight() {
		return height;
	}

	public int getThickness() {
		return thickness;
	}

	public String toString(){
		if(wasLitOnFire)return "\"" + title + "\", by an author whose name you cannot make out. " + numberOfPages + " pages";
		else return "\"" + title + "\", by " + author + ", " + numberOfPages + " pages";
	}
	
	public Color getJacketColor() {
		return jacketColor;
	}

	public void setJacketColor(Color jacketColor) {
		this.jacketColor = jacketColor;
	}

	public String getTitle(){
		return title;
	}
	
	public int getNumberOfPages(){
		return numberOfPages;
	}

	public Person getAuthor() {
		return author;
	}
	
	public void setOnFire(){
		wasLitOnFire = true;
		jacketColor = Color.black;
		title = "The title of this book is too charred to make out";
		
	}
}
