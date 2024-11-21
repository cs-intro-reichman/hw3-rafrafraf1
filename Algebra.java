// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// returns |a| for a
	public static int abs(int a) {
		int result = a;
		if (a < 0) {
			result = 0;
			int counter = 0;
			while (counter > a) {
				result++;
				counter--;
			}
		}
		return result;
	}

	// returns -|a| for a
	public static int negative_abs(int a) {
		int result = a;
		if (a > 0) {
			result = 0;
			int counter = 0;
			while (counter < a) {
				result--;
				counter++;
			}
		}
		return result;
	}


	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		int result = x1;
		for (int i = 0; i < abs(x2); i++) {
			if (x2 > 0) {
				result++;
			}
			else {
				result--;
			}
		}
		return result;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		int result = x1;
		for (int i = 0; i < abs(x2); i++) {
			if (x2 > 0) {
				result--;
			}
			else {
				result++;
			}
		}
		return result;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		int result = 0;
		for (int i = 0; i < abs(x2); i++) {
			if (x2 > 0) {
				result = plus(result, x1);
			}
			else {
				result = minus(result, x1);
			}
		}
		return result;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		int result = 1;
		for (int i = 0; i < n; i++) {
			result = times(result, x);
		}
		return result;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		int result = 1;
		while (true) {
			if (abs(times(result, x2)) > abs(x1)) {
				result--;
				break;
			}
			result++;
		}
		if (times(x1,x2) < 0 ) {
			result = negative_abs(result);
		}
		return result;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		int result = x1;
		if (x1 > 0) {
			while (result >= x2) {
				result = minus(result, x2);
			}
		}
		else {
			while (result < 0) {
				result = plus(result, x2);
			}
		}
		
		return result;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		int result = 0;
		while (true) {
			if (times(result, result) > x) {
				result--;
				break;
			}
			result++;
		}
		return result;
	}	  	  
}