package object;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;

public class Bookshelf {

	public static void main(String[] args) {
		Person author1 = new Person("Noah", "Webster", true);
		Person author2 = new Person("Anthony", "Burgess", true);
		Person author3 = new Person("Philip", "K.", "Dick", true);
		Person random1 = new Person("John", "Peacock", true);
		Person random2 = new Person("Jane", "Doe", false);
		Person random3 = new Person("Jack", "Robinson", true);
		Book book1 = new Book("Dictionary", 1001, author1);
		Book book2 = new Book("Clockwork Orange", 749, author2);
		Book book3 = new Book("Do Androids Dream of Electric Sheep?", 500, author3);
		
		ArrayList<Book> shelf = new ArrayList<Book>();
		ArrayList<Person> libraryCardHolders = new ArrayList<Person>();
		ArrayList<Book> creepyBooks = new ArrayList<Book>();
		libraryCardHolders.add(random1);
		libraryCardHolders.add(random2);
		libraryCardHolders.add(random3);
		shelf.add(book1);
		shelf.add(book2);
		shelf.add(book3);
		shelf.add(new Book("The Man in the High Castle", 600, author3));
		Book book5 = new Book("The Minority Report", 589, author3);
		shelf.add(0, book5);
		
		book1.setJacketColor(Color.cyan);
		book2.setJacketColor(Color.orange);
		book3.setJacketColor(Color.pink);
		book5.setJacketColor(Color.red);
		
		Library lib = new Library(shelf, libraryCardHolders);//use "books" or "shelf" whatever your ArrayList is 

		lib.setSize(new Dimension(500,500));

		lib.setVisible(true);

		lib.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		Collections.sort(shelf, new Comparator<Book>() {
//	    public int compare(Book a, Book b) {
//
//	        String aLast = a.getAuthor().getLastName();
//			String bLast = b.getAuthor().getLastName();
//			return aLast.compareTo(bLast);
//	    }
//
//	});
		
//		Collections.sort(shelf, new Comparator<Book>() {
//		    public int compare(Book a, Book b) {
//
//		        return a.getNumberOfPages() - b.getNumberOfPages();
//
//		    }
//
//		});
		
		//By height
//		Collections.sort(shelf, new Comparator<Book>() {
//		    public int compare(Book a, Book b) {
//
//		        return a.getHeight() - b.getHeight();
//
//		    }
//
//		});
		sortByAuthor(true, shelf);
		printAll(shelf);
	}
	
	public static void sortByPageNumber(final boolean ascending, ArrayList<Book> shelf){
		Collections.sort(shelf, new Comparator<Book>() {
	    public int compare(Book a, Book b) {

	        return a.getNumberOfPages() - b.getNumberOfPages();

	    }

	});
	}
	
	public static void sortByHeight(final boolean ascending, ArrayList<Book> shelf){
		Collections.sort(shelf, new Comparator<Book>() {
	    public int compare(Book a, Book b) {

	        return a.getHeight() - b.getHeight();

	    }

	});
	}
	
	public static void sortByTitle(final boolean ascending, ArrayList<Book> shelf){
		Collections.sort(shelf, new Comparator<Book>() {
		    public int compare(Book a, Book b) {

		    	String aTitle = a.getTitle();
				String bTitle = b.getTitle();
				if(ascending)return aTitle.compareTo(bTitle);
				return bTitle.compareTo(aTitle);

		    }

		});
	}
	
	public static void sortByAuthor(final boolean ascending, ArrayList<Book> shelf){
		Collections.sort(shelf, new Comparator<Book>() {
		    public int compare(Book a, Book b) {

		        String aLast = a.getAuthor().getLastName();
				String bLast = b.getAuthor().getLastName();
				if(ascending)return aLast.compareTo(bLast);
				return bLast.compareTo(aLast);
		    }

		});
	}
	
	private static void arrayListStuff(ArrayList<Book> shelf){
		/**
		 * "<Book> or <?>" is a generic type. It tells the constructor WHAT is
		 * 			in the ArrayList. We use generics to save ourselves the
		 * 			trouble of type casting
		 * "new ArrayList<?>()" constructor does not need to specify
		 * 			length. (Default is 10, but it doesn't matter,
		 * 			it adjusts as you add objects to it)
		 * note that ArrayList IS indexed (item 0 is always at index 0,
		 * 			item 1 is always at index 1, and so on) You can iterate
		 * 			through it
		 * you cannot make an ArrayList of primitives 
		 * 			(no int, boolean, etc.) IF you wish to make an
		 * 			ArrayList of ints, you use the wrapper class
		 * 			int is integer, double is Double, etc.
		 */
		//add things to an ArrayList (remember, for arrays, we did
		//array[0] = book1;//specify index
		//does not specify index. book1 is automatically index 0
		//you can construct at the same time;
		
		//iterate through an ArrayList;
//		for(Book b:shelf)System.out.println(b);
		
		//adding Objects at specified indices
		//task: remove all books with the word "The" in the title
//		for(int i = 0; i < shelf.size(); i++){
//			while(i < shelf.size() && shelf.get(i).toString().toLowerCase().indexOf("the") > -1){
//				shelf.remove(i);
//			}
//		}
		
		//to get the length of ArrayList
		//recall using arrays:
		//array.lengthis " + 
		//iterate through an ArrayList using standard for loop
//		for(int i = 0; i < shelf.size(); i++){
//			System.out.println(shelf.get(i));
//		}
		
		
		//to get something from an ArrayList (recall to get something
		//from array:
		//System.out.println(array[0]);
		//LOSE POINTS IF YOU DO THIS WRONG!!
		
		//getting the index of an Object
//		if(shelf.contains(book2)){
//			System.out.println(book2.getTitle() +
//					" is book #" + shelf.indexOf(book2) + " on the shelf.");
//		}
//		
//		for(Book b:shelf){
//			if(b.getAuthor().toString().equals("Philip K. Dick")){
//				creepyBooks.add(b);
//				b.setOnFire();
//			}
//		}
//		printAll(creepyBooks);
	}
	
	private static void printAll(ArrayList list){
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
	}

}

