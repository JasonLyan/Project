package maths;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Solver{
	static SolverGUI s = new SolverGUI();
	static String derp = "";

	public static void main(String[] args){
		//TODO: for now, you may use this method for testing
		//your other methods
		//Here is something to try:
		System.out.println("The terms in the equation \"3x^2 -12x +13 = 2x^2 -17x +7\" are:");
		Equation parsedEquation = interpretInput("3x^2 -12x +13 = 2x^2 -17x +7");
		for(Term t: parsedEquation.getLeftSide()){
			System.out.println(t.toString());
		}
		System.out.println("...on the left, and on the right:");
		for(Term t: parsedEquation.getRightSide()){
			System.out.println(t.toString());
		}
		//Once your methods are all working, you can use the following to initiate the GUI
		s.setVisible(true);
		s.setSize(900, 500);
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static Equation interpretInput(String s){
		//parse s into two ArrayList<Term>
		//expect user input to appear like this:
		//-3x^2 -12x +13 = 2x^2 -17x +7
		//A few things to note:
		//Assume the user will always include exactly one '=',
		//    (Invalid input will be handled by the GUI)
		//Terms are always separated by a '+','-' or '='
		//Do not consider subtraction. Rather, subtraction is ADDING a Term with a NEGATIVE coefficient
		//Recall that a term like "x" has a coefficient of '1' and an exponent of '1'
		//Recall that there is a specific constructor for constant terms
		String str = s.replaceAll("\\s", "");
		String[] leftAndRight = str.split("=");
		ArrayList<Term> leftSide = createAndAddTerms(leftAndRight[0]);
		ArrayList<Term> rightSide = createAndAddTerms(leftAndRight[1]);
		return new Equation(leftSide, rightSide);
	}

	//'private' since we are only using it HERE
	private static ArrayList<Term> createAndAddTerms(String termString){
		ArrayList<Term> terms = new ArrayList<Term>();
		int i = 0;//index of the first digit (in case the String starts with '-')
		boolean positiveTerm = true;
		if(termString.startsWith("-")){
			positiveTerm = false;
			i++;//start at index 1 if character at 0 is '-'
		}
		while(termString.length() > 0){//delete one term at a time as it gets
			int endOfTerm = termString.length();
			int indexOfPlus = termString.indexOf("+");
			//if there is no '+', above value is -1
			if(indexOfPlus <= 0)indexOfPlus = endOfTerm;
			int indexOfMinus = termString.indexOf("-");
			if(indexOfMinus <= 0)indexOfMinus = endOfTerm;
			if(indexOfMinus < indexOfPlus)endOfTerm = indexOfMinus;
			else endOfTerm = indexOfPlus;
			try{
				Term a = parseTerm(termString.substring(i, endOfTerm));

				if(!positiveTerm)a = a.getAddInverse();

				if(a != null)terms.add(a);

				//check if next term is + or -
				if(indexOfMinus < indexOfPlus)positiveTerm = false;
				else positiveTerm = true;
				//cut out the term that was added, including the NEXT +/- symbol
				termString = termString.substring(endOfTerm + 1, termString.length());
			}catch(Exception e){
				//if the user is trying to do something that throws an error,
				//skip this term
				termString = termString.substring(endOfTerm, termString.length());
				//cuts out the term
			}
		}
		return terms;

	}

	/**

	 * @param sample string from user: 34a^3

	 * @return new Term representing input from user

	 * Use this method for writing interpretInput

	 */

	private static Term parseTerm(String s) {

		if(s.matches("\\d*(\\.\\d*)?\\w\\^\\d+" + "|" + "\\d*(\\.\\d*)?\\w" + "|" + "\\d+(\\.\\d*)?")){//regex identifies anything like 23x^2 or y^11 or 12b or z or 13

			double coef;

			String variable;

			Term t;

			if(s.matches("\\d*(\\.\\d*)?")){

				coef = Double.parseDouble(s);

				t = new Term(coef);

			}

			else if(s.matches("\\d*(\\.\\d*)?\\w+(\\^\\d+)?")){
				String noDigits = s.replaceAll("\\d*(\\.\\d*)?", "");

				int indOfVar = s.indexOf(noDigits);

				if(s.substring(0,indOfVar).length()==0){

					coef = 1.0;

				}

				else coef = Double.parseDouble(s.substring(0, indOfVar));

				variable=s.substring(indOfVar, indOfVar+1);

				if(s.indexOf("^")>-1){

					int exponent = Integer.parseInt(s.substring(s.indexOf("^")+1));

					t =new Term(coef,variable,exponent); 

				}else{

					t =new Term(coef,variable,1); 

				}

			}else{

				t=null;

			}

			return t;

		}

		else return null;

	}


	public static String getConfirm(Equation eq) {
		return "Your input was interpreted as " + eq.toString();
		//returns a message confirming the input given by the user, such as "Your input was interpreted as "+eq.toString();

	}


	public static String getNoTricks(String usersInput) {
		return "Your input cannot be interpreted by the machine. Stop messin' around and type a real equation...";
		//returns a message for when the user types input that cannot be interpreted by your Solver

	}

	/**

	 * 

	 * @param eq

	 * This method is only called if eq is linear or quadratic

	 * Use the methods described in 4.1 to solve the equation

	 * Note that each method will need to be called twice, for the left and right side

	 * Don't forget to call gui.clearSteps() at the beginning of the method

	 * and gui.addStep after each step of the process

	 */

	public static void solveQuadratic(ArrayList<Term> side){
		String solution = "";
		ArrayList<Term> zeroSide = new ArrayList<Term>();
		ArrayList<Term> zeroSide2 = new ArrayList<Term>();
		ArrayList<Term> sidef1 = new ArrayList<Term>();
		ArrayList<Term> sidef2 = new ArrayList<Term>();
		int[] factorHolder = factor(side);
		for(int i: factorHolder)System.out.println("factor: " + i);
		if(side.get(0).getCoefficient() == 1 || (side.get(0).getCoefficient() == -1)){
			Term factorTerm1 = new Term(1,side.get(0).getVariable(),1);
			Term factorInput1 = new Term(-factorHolder[0], "", 1);
			Term factorTerm2 = new Term(1,side.get(0).getVariable(),1);
			Term factorInput2 = new Term(-factorHolder[1], "", 1);
			//			for(int i = 0; i < side.size(); i++){
			//				while(i < side.size()){
			//					side.remove(i);
			//				}
			//			}
			sidef1.add(factorTerm1);
			sidef1.add(factorInput1);
			Term zero = new Term(0,"",1);
			zeroSide.add(zero);
			Equation factor1Solution = new Equation(sidef1, zeroSide);
			System.out.println("factor1 equation: " + factor1Solution.toString());
			sidef2.add(factorTerm2);
			sidef2.add(factorInput2);
			Term zero2 = new Term(0, "",1);
			zeroSide2.add(zero2);
			Equation factor2Solution = new Equation(sidef2, zeroSide2);
			System.out.println("factor2 equation: " + factor2Solution.toString());
			s.addStep("(" + side.get(0).getVariable() + " - " + (int)-factorInput1.getCoefficient() + ")" + "(" + side.get(0).getVariable() + " - " + (int)-factorInput2.getCoefficient() + ") = 0");
			factor1Solution.solveLinear(factor1Solution.getLeftSide());
			String factor1String = factor1Solution.toString();
			factor2Solution.solveLinear(factor2Solution.getLeftSide());
			String factor2String = factor2Solution.toString();
			if(factor1String.equals(factor2String))solution = factor1String;
			else solution = factor1String + " and" + factor2String;
			s.addStep(solution);
		}
		else{
			double a = side.get(0).getCoefficient();
			double b = side.get(1).getCoefficient();
			double c = side.get(2).getCoefficient();
			double solution1 = (-b + Math.sqrt(Math.pow(b, 2) - 4*a*c)) / (2*a);
			double solution2 = (-b - Math.sqrt(Math.pow(b, 2) - 4*a*c)) / (2*a);
			if(solution1 != solution2)
				solution = side.get(0).getVariable() + " = " + solution1 + " and " + side.get(0).getVariable() + " = " + solution2; 
			else
				solution = side.get(0).getVariable() + " = " + solution1;
			s.addStep(solution);   
		}

	}

	public static int[] factor(ArrayList<Term> side){
		double test = side.get(0).getCoefficient() * side.get(2).getCoefficient();
		int[] factorHolder = new int[2];
		for(int i = 1; i*i <= test; i++){
			if(test % i == 0){
				if((test/i) + i == side.get(1).getCoefficient()){
					System.out.println("factors: " + test/i + ", " + i);
					factorHolder[0] = i;
					factorHolder[1] = (int)(test/i);
				}
			}
		}
		for(int i = -1; i*i <= -test; i--){
			if(test % i == 0){
				if((test/i) + i == side.get(1).getCoefficient()){
					System.out.println("factors: " + test/i + ", " + i);
					factorHolder[0] = i;
					factorHolder[1] = (int)(test/i);
				}
			}
		}
		for(int i = -1; i*i >= -test; i--){
			if(test % i == 0){
				if((test/i) + i == side.get(1).getCoefficient()){
					System.out.println("factors: " + test/i + ", " + i);
					factorHolder[0] = i;
					factorHolder[1] = (int)(test/i);
				}
			}
		}
		//Any quadratic equation without a bx term won't have factors
		System.out.println(side.get(0).getCoefficient());
		System.out.println(side.get(1).getCoefficient());
		System.out.println(side.get(2).getCoefficient());
		return factorHolder;
	}

	public static double getDiscriminate(ArrayList<Term> side){
		double discriminate;
		discriminate = (side.get(1).getCoefficient() * side.get(1).getCoefficient()) - (4 * side.get(0).getCoefficient() * side.get(2).getCoefficient());
		return discriminate;
	}

	public static ArrayList<Term> sortOrder(ArrayList<Term> side){
		ArrayList<Term> sortedList = new ArrayList<Term>();
		for(int i = 0; i < side.size(); i++){
			int sListSize = sortedList.size();
			if(i < side.size()){
				if(side.get(i).getExponent() == 2 && sortedList.size() == 0){
					sortedList.add(side.get(i));
					side.remove(side.get(i));
					i = -1;
				}
				if(sListSize == 1 && !side.get(i).getVariable().equals("")){
					sortedList.add(side.get(i));
					side.remove(side.get(i));
					i = -1;
				}
				if(sListSize == 2 && side.get(i).getVariable().equals("")){
					sortedList.add(side.get(i));
					side.remove(side.get(i));
				}
				if(sListSize == 1 && i == side.size() - 1){
					sortedList.add(new Term(0,"",1));
					i = -1;
				}
				if(sListSize == 2 && i == side.size() - 1){
					sortedList.add(new Term(0,"",1));
					i = -1;
				}
			}
		}
		for(Term t: sortedList)System.out.println(t.toString());
		return sortedList;
	}

	public static void solve(Equation eq) {

		s.clearSteps();
		System.out.print(eq.isCancelRight());
		if(eq.isCancelRight()){
			eq.toZeroOnOneSide(eq.getRightSide());
			if(!derp.equals(eq.toString())){
				derp = eq.toString();
				s.addStep(eq.toString());
			}
			eq.simplify(eq.getLeftSide());
			if(!derp.equals(eq.toString())){
				derp = eq.toString();
				s.addStep(eq.toString());
			}
			eq.simplify(eq.getRightSide());
			if(!derp.equals(eq.toString())){
				derp = eq.toString();
				s.addStep(eq.toString());
			}
			if(eq.isLinear()){
				eq.solveLinear(eq.getLeftSide());
				if(!derp.equals(eq.toString())){
					derp = eq.toString();
					s.addStep(eq.toString());
				}
			}
			if(eq.isQuadratic()){
				solveQuadratic(sortOrder(eq.getLeftSide()));
			}
		}
		else{
			eq.toZeroOnOneSide(eq.getLeftSide());
			if(!derp.equals(eq.toString())){
				derp = eq.toString();
				s.addStep(eq.toString());
			}
			eq.simplify(eq.getLeftSide());
			if(!derp.equals(eq.toString())){
				derp = eq.toString();
				s.addStep(eq.toString());
			}
			eq.simplify(eq.getRightSide());
			if(!derp.equals(eq.toString())){
				derp = eq.toString();
				s.addStep(eq.toString());
			}
			if(eq.isLinear()){
				eq.solveLinear(eq.getRightSide());
				if(!derp.equals(eq.toString())){
					derp = eq.toString();
					s.addStep(eq.toString());
				}
			}
			if(eq.isQuadratic()){
				solveQuadratic(sortOrder(eq.getRightSide()));
			}
		}
		//		System.out.println(eq.toString() + " HELLO");
	}

}