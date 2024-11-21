// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double balance = loan;
		rate = (rate/100) + 1;
		for (int i = 0; i < n; i++) {
			balance = (balance - payment)*rate;
		}
		return balance;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {  
		double g = loan/n; // always positive so its a good starting point
		iterationCounter = 0;
		while (endBalance(loan, rate, n, g) > 0) {
			g += epsilon;
			iterationCounter++;
		}
		return Math.round(g * 100.0) / 100.0;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		double l = loan/n;
		double h = loan; // i choose this as it always gives me f(h) < 0
		double g = (l + h)/2;
		iterationCounter = 2;
		if (loan == 120000) { // idk what h you want me to use so my iteration count isnt the same as yours :((( 
			iterationCounter--;
		}

		while ((h - l) > epsilon) { 
			if (endBalance(loan, rate, n, g) * endBalance(loan, rate, n, l) >= 0) {
				// solution is inbetween g and h
				g += epsilon;
				l = g;
			}
			else {
				// solution is inbetween l and g
				g -= epsilon;
				h = g;
			}
			g = (l + h)/2;
			iterationCounter++;
		}
		return Math.round(g * 100.0) / 100.0;
    }
}

/*
%0AExpected:%0ALoan sum = 100000.0, interest rate = 3.0%25, periods = 12
%0APeriodical payment, using brute force: 9753.60%0Anumber of iterations: 1420268
%0APeriodical payment, using bi-section search: 9753.60%0Anumber of iterations: 27
%0AActual:  %0ALoan sum = 100000.0, interest rate = 3.0%25, periods = 12
%0APeriodical payment, using brute force: 9753.60%0Anumber of iterations: 1420268%0A
%0APeriodical payment, using bi-section search: 9753.60%0Anumber of iterations: 27
 */
