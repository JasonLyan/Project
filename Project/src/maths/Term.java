package maths;

public class Term{

	double coefficient;
	String variable;
	int exponent;
	boolean constant;//this is true if a Term has no variable expression
	//TODO: Write getters for all of the above fields

	public Term(double coefficient, String var, int exp){
		this.coefficient = coefficient;
		this.variable = var;
		this.exponent = exp;
		if(var.equals(""))this.constant = true;
		else this.constant = false;
		//if variable is non-empty, sets constant to 'false'
	}

	/**
	 *a constructor for constant: 
	 */
	Term(double constant){
		this.coefficient = constant;
		this.variable = "";
		this.exponent = 1;
		this.constant = true;
		//sets coefficient to constant, variable to "", exponent to "0" and constant to 'true')
	}
	public double getCoefficient() {
		return coefficient;
	}

	public String getVariable() {
		return variable;
	}

	public int getExponent() {
		return exponent;
	}

	public boolean isConstant() {
		return constant;
	}

	/**
	 *Write getters for each field
	 *Note that the getter for the boolean should be called 'isConstant'
	 */ 

	public Term getAddInverse(){
		return new Term(coefficient * -1, variable, exponent);
		//returns the additive inverse of this Term
	}

	public int getDegree(){
		//returns the exponent
		return this.exponent;
	}

	public boolean isPositive(){
		if(this.coefficient >= 0)return true;
		else return false;
		//returns true if the coefficient is positive (or zero), false otherwise
	}	

	public String toString(){
		String desiredString = "";
		String convertDouble = String.valueOf(this.coefficient);
		if(convertDouble.indexOf(".0") > 0 && convertDouble.indexOf(".0") == convertDouble.length() - 2)convertDouble = convertDouble.substring(0, convertDouble.indexOf(".0"));
		if((this.variable != "" && String.valueOf(convertDouble).equals("1") || this.variable != "" && String.valueOf(convertDouble).equals("-1"))){
			convertDouble = convertDouble.substring(0, convertDouble.length() - 1);
		}
		desiredString+=convertDouble;
		if(this.exponent > 1 && !this.variable.equals("")){ 
			desiredString += (this.variable + "^" + this.exponent);
		}
		else{
			desiredString += this.variable;
		}

		return desiredString;
		/**
		 *Some tips to consider:
		 *doubles always print with trailing zeros (i.e. 2.0) This is not desireable
		 *If a term has a coefficient of 1 or -1, the 1 should not be shown. 
		 *If a term has an exponent of 1, the 1 should not be shown
		 *For exponents, use '^'. The GUI will change it into superscript.
		 */
	}

	/**

	 * 

	 * 

	 * @param s

	 * @param t

	 * @return 'true' if s and t are like terms (same variable and exponent)

	 */

	public static boolean areLikeTerms(Term s, Term t){
		if((s.getVariable().equals(t.getVariable())) && (s.getExponent() == t.getExponent()))return true;
		return false;
	}

	/**

	 * returns a new Term with same variable and exponent as s and t but combined coefficient

	 * @param s

	 * @param t

	 * @return

	 */

	public static Term combine(Term s, Term t){
		return new Term(s.getCoefficient() + t.getCoefficient(), s.getVariable(), s.getExponent());
	}


	public void setCoefficient(double d) {
		this.coefficient = d;
	}
}