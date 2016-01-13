package maths;

import java.util.ArrayList;

public class Equation{

	private ArrayList<Term> leftSide;
	private ArrayList<Term> rightSide;
	private boolean cancelRight;
	private ArrayList<Double> solution;
	//TODO: Make getters for all of the above

	public Equation(ArrayList<Term> leftSide, ArrayList<Term> rightSide){
		this.leftSide = leftSide;
		this.rightSide = rightSide;
		//sets values of leftSide and rightSide only
	}

	public ArrayList<Term> getLeftSide() {
		return leftSide;
	}

	public ArrayList<Term> getRightSide() {
		return rightSide;
	}

	public boolean isCancelRight() {
		return cancelRight();
	}

	public ArrayList<Double> getSolution() {
		return solution;
	}

	public boolean isLinear(){
		for(Term checkLinear: leftSide){
			if((!checkLinear.getVariable().equals("") && (checkLinear.getExponent() > 1) || (!checkLinear.getVariable().equals("") && checkLinear.getExponent() < 1)))return false;
		}
		for(Term checkLinear: rightSide){
			if((!checkLinear.getVariable().equals("") && (checkLinear.getExponent() > 1) || (!checkLinear.getVariable().equals("") && checkLinear.getExponent() < 1)))return false;
		}
		return true;
		//returns 'true' if this equation is linear
		//and every term is either constant or has the same variable
	}

	private boolean isOfDegree(int degree){
		int maxDegree = 0;
		int minDegree = 0;
		String variable = "";
		for(Term t: leftSide){
			if(t.getDegree() > maxDegree)maxDegree = t.getDegree();
			if(t.getDegree() < minDegree)return false;
			if(t.isConstant())variable = t.getVariable();
		}
		for(Term t:rightSide){
			if(t.getDegree() > maxDegree)maxDegree = t.getDegree();
			if(t.getDegree() < minDegree)return false;
			if(t.isConstant())variable = t.getVariable();
		}
		if(maxDegree == degree && variablesMatch(variable))return true;
		else return false;
	}

	private boolean variablesMatch(String s){
		for(Term t: leftSide){
			if(!t.isConstant() && !t.getVariable().equals(s))return false;
		}
		for(Term t: rightSide){
			if(!t.isConstant() && !t.getVariable().equals(s))return false;
		}
		return true;
	}

	public boolean isQuadratic(){
		boolean quad = false;
		for(Term checkQuad: leftSide){
			if(checkQuad.getExponent() > 2 && checkQuad.getExponent() < 0)return false;
			if(checkQuad.getExponent() == 2){
				quad = true;
			}
		}
		for(Term checkQuad: rightSide){
			if(checkQuad.getExponent() > 2 && checkQuad.getExponent() < 0)return false;
			if(checkQuad.getExponent() == 2){
				quad = true;
			}
		}
		return quad;
		//returns 'true' if this equation is quadratic
		//and every term is either constant or has the same variable
	}

	public boolean isSolveable(){
		if(isQuadratic() == true || isLinear() == true)return true;
		return false;
		//returns 'true' if this equation is linear or quadratic, 'false' otherwise 
		//(because in this project we are not programming a means of solving anything other than linear and quadratic equations)
	}

	public boolean cancelRight(){
		Term highleft = getHighestDegreeTerm(leftSide);
		Term highright = getHighestDegreeTerm(rightSide);
		if(highleft.getExponent() > highright.getExponent()){
			if(highleft.getCoefficient() < 0){
				return true;
			}
			return false;
		}
		if(highleft.getExponent() < highright.getExponent()){
			if(highright.getCoefficient() < 0){
				return true;
			}
			return false;
		}
		if(!highleft.isConstant()){
			return true;
		}
		return false;
//		if(getHighestDegreeTerm(rightSide).getExponent() < getHighestDegreeTerm(leftSide).getExponent())return true;
//		if(getHighestDegreeTerm(rightSide).getExponent() == getHighestDegreeTerm(leftSide).getExponent()){
//			if(getHighestDegreeTerm(rightSide).getCoefficient() < getHighestDegreeTerm(leftSide).getCoefficient())return true;
//		}
//		return false;
		//sets the value of cancelRight and
		//returns 'true' if the equation should be solved by subtracting terms from the right side, false if it is better to subtract terms on the left side
	}

	public String toString(){
		String leftString = "";
		String rightString = "";
		for(Term t: leftSide){
			if(t.getCoefficient() > 0 && leftSide.indexOf(t) != 0)leftString += " + ";
			if(t.getCoefficient() == 0 && leftSide.size() != 1){leftString = leftString;}//nothing
			else{leftString += " " + t.toString();}
			if(leftString.equals(""))leftString += "0";
		}

		for(Term t: rightSide){
			if(t.getCoefficient() > 0 && rightSide.indexOf(t) != 0)rightString += " + ";
			if(t.getCoefficient() == 0 && rightSide.size() != 1){rightString = rightString;}//nothing
			else{rightString += " " + t.toString();}
			if(rightString.equals(""))rightString += "0";
		}
//		for(int i = 0; i < leftString.length(); i++){
//			if(leftString.indexOf("  +  ") > 0)
//		}
//		for(int i = 0; i < rightString.length(); i++){
//			
//		}
		if(leftString.length() > 1){
			for(int i = 0; i < leftString.length(); i++){
				while (i < leftString.length() && leftString.indexOf("0 + ") >= 0){
					leftString = leftString.substring(leftString.indexOf("0 + ") + 3, leftString.length());
				}
				while (i < leftString.length() && leftString.indexOf("0 ") >= 0){
					leftString = leftString.substring(leftString.indexOf("0 ") + 1, leftString.length());
				}
			}
		}
		if(rightString.length() > 1){
			for(int i = 0; i < rightString.length(); i++){
				while (i < rightString.length() && rightString.indexOf("0 + ") >= 0){
					rightString = rightString.substring(rightString.indexOf("0 + ") + 3, rightString.length());
				}
				while (i < rightString.length() && rightString.indexOf("0 ") >= 0){
					rightString = rightString.substring(rightString.indexOf("0 ") + 1, rightString.length());
				}
			}
		}
		return leftString + " = " + rightString;
		/**
		 *Take your time on this method!
		 *There are many things to consider:
		 *Every terms should be separated by a '+' UNLESS it has a negative coefficient. 
		 *When a term is negative, the negative sign will appear as a minus.
		 */
	}


	public static Term getHighestDegreeTerm(ArrayList<Term> side){
		Term highest = new Term(0);
		int highestDeg = 0;
		for (Term t: side){
			if(t.getExponent() > highestDeg || t.getCoefficient() > highest.getCoefficient()){
				highest = t;
				highestDeg = t.getExponent();
			}
		
		}
		return highest;
		//		int highestExp = -2147483648;
		//		int indexOfExp = 0;
		//		for(Term d: side){
		//			if(d.getExponent() > highestExp)highestExp = d.getExponent();
		//			indexOfExp = side.indexOf(d);
		//		}
		//		return side.get(indexOfExp);
		//returns the term in the ArrayList that is the highest degree.
		//example
		//3x^2 + 4x^3 - 12x + x^2
		//will return 4x^3 
	}

	/**

	 * adds the additiveInverse of everything on the sideBeingCancelled

	 * to both sides of the Equation

	 * @param sideBeingCanceled

	 */

	public void toZeroOnOneSide(ArrayList<Term> sideBeingCanceled){
		ArrayList<Term> holder = new ArrayList<Term>();
		for(Term t: sideBeingCanceled){
			Term inverse = t.getAddInverse();
			holder.add(inverse);
		}
		for(Term h: holder){
			leftSide.add(h);
			rightSide.add(h);
		}

	}

	/**

	 * 

	 * @param side - simplifies the specified side of the equation

	 * Notes: This method should check every Term on the specified side of the equation 

	 * with every other Term to check if they are like terms (use Term.areLikeTerms(Term s, Term t)

	 * If they are, it should reassign the current Term to the combined result (use Term.combine(Term s, Term t)

	 * and remove the one with which it combined.

	 * Finally, if the resulting term has a coefficient of zero should be removed.

	 * 

	 * For example, Suppose side contains 5x + 3 -5x. Call the three terms a, b and c, respectively

	 * 1. It checks to see if 5x and 3 (a and b) are like terms, they are not

	 * 2. It checks to see if 5x and -5x (a and c) are like terms, they are

	 * 3. Since 5x and -5x are like terms, a = Term.cobine(a, c) then leftSide.remove(c)

	 * 4. Now side contains 0x + 3. Since term a has a coefficient of zero, remove it.

	 * 5. Now side contains 3. 

	 * 

	 * ONE MORE NOTE: Since you will be removing items from side, use a standard for loop

	 * and remember that when something is moved, everything "slides over"

	 */

	public void simplify(ArrayList<Term> side){
		for(int i = 0; i < side.size(); i++){
			for(int j = 0; j < side.size(); j++){
				if(i < side.size()){
					if(j < side.size()){
						if(i != j && Term.areLikeTerms(side.get(i), side.get(j))){
							side.set(i, Term.combine(side.get(i), side.get(j)));
							side.remove(j);
						}
					}
				}
			}
		}
		//		for(int i = 0; i < side.size(); i++){
		//			if(i < side.size()){
		//				if((side.get(i).getCoefficient() == 0 && !side.get(i).getVariable().equals("")) || (side.get(i).getVariable().equals("") && side.size() != 1))
		//					side.remove(i);}
		//		}
	}

	/**

	 * 

	 * @param sideWithVariable - we can assume the side with a variable is of the form ax + b

	 * @return the solution

	 * 

	 * Example: 3x + 21 = 0

	 * 1. Identify the constant term in the sideWithVariable (21)

	 * 2. Identify the variable term in the sideWithVariable (3x)

	 * 3. Add the additive inverse of the constant Term to both sides of the equation (3x = -21)

	 * 4. Multiple both sides by the additive inverse of the coefficient of the variable term (.33333333)

	 */

	public void solveLinear(ArrayList<Term> sideWithVariable){
		ArrayList<Term> holder = new ArrayList<Term>();
		for(Term t: sideWithVariable){
			if(t.getVariable().equals("")){
				Term inverse = t.getAddInverse();
				holder.add(inverse);
			}
		}
		for(Term h: holder){
			leftSide.add(h);
			rightSide.add(h);
		}

		simplify(leftSide);
		simplify(rightSide);
		Solver.s.addStep(this.toString());
		for(Term t: sideWithVariable){
			if(!t.getVariable().equals("")){
				Term inverseCoeff = t.getAddInverse();
				multiplyScalar(leftSide, -1/inverseCoeff.getCoefficient());
				multiplyScalar(rightSide, -1/inverseCoeff.getCoefficient());
			}
		}
	}

	/**

	 * 

	 * @param side

	 * @param scalar

	 * multiplies all Terms on the given side by the given scalar

	 * (Hint: use setCoefficient(double))

	 */

	public void multiplyScalar(ArrayList<Term> side, double scalar){
		for(Term t: side){
			t.setCoefficient(scalar * t.getCoefficient());
		}
	}
}

