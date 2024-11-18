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
		double rate = 1+(Double.parseDouble(args[1])/100);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the ending balance of the loan, given a periodical payment
		double payment = 10000;
		double endBalance = endBalance(loan, rate, n, payment);
		System.out.println("If your periodical payment is " + payment + ", your ending balance is: " + (int) endBalance);
		
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
		return g;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		double l = loan/n;
		double h = l*2; // i choose this as it always gives me f(h) < 0
		double g = (l + h)/2;
		iterationCounter = 0;

		while ((h - l) > epsilon) { 
			// its more efficient to check that both are positive rather than multiplying them and checking if the result is positive...
			if (endBalance(loan, rate, n, g) * endBalance(loan, rate, n, l) <= 0) {
				// solution is inbetween g and h
				l += epsilon;
				h = g;
			}
			else {
				// solution is inbetween l and g
				l = g;
				h -= epsilon;
			}
			g = (l + h)/2;
			iterationCounter++;
		}
		return g;
    }
}