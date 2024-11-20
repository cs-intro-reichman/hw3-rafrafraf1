
/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		str1 = preProcess(str1).replaceAll(" ", "");
		str2 = preProcess(str2).replaceAll(" ", "");
		if (str1.length() != str2.length()) {
			return false; // ignoring spaces, anagrams must have the same length... 
		}

		int indexOfChar;
		char currChar;
		for (int i = 0; i < str2.length(); i++) { // i am not doing a nested for loop >:( very unnecessary.
			currChar = str2.charAt(i);
			indexOfChar = str1.indexOf(currChar);
			if (indexOfChar == -1) {
				return false; // char doesnt match so we can just return false now
			}
			else { // removes char from str1 to make sure all chars match one-to-one
				str1 = str1.substring(0, indexOfChar) + str1.substring(indexOfChar+1);
			}
		}
		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		// Replace the following statement with your code
		str = str.toLowerCase();
		String preserve = "abcdefghijklmnopqrstuvwxyz ";
		for (int i = 0; i < str.length(); i++) {
			if (preserve.indexOf(str.charAt(i)) == -1) { // removes all chars that arent in preserve
				str = str.substring(0, i) + str.substring(i+1); 
			}
		}
		return str;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) { // java atleast has pass-by-value so i can modify str freely :)
		// Replace the following statement with your code
		String newStr = "";
		int rando;
		while (str.length() > 0) {
			rando = (int) (Math.random() * str.length()); // generates a random index of str
			newStr = newStr + str.charAt(rando); // put that char into newstr
			str = str.substring(0, rando) + str.substring(rando+1); // remove char from str
		}
		return newStr;
	}
}