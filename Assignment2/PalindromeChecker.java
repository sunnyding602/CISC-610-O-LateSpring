import java.io.FileNotFoundException;
import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class PalindromeChecker {
    public static boolean isPalindrome(String str) {
        if (str.length() == 0) {
            return true;
        }

        MyStack<Character> stack = new MyStack<>();
        str = str.toLowerCase().trim();
        char[] charArr = str.toCharArray();
        for (int index = 0; index < str.length(); index++) {
            if (str.length() % 2 == 1) {
                if ( index == str.length()/2) {
                    continue;
                }
            }
            char c = charArr[index];
            if (!stack.empty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        // read the words from txt file
        String str = "";
        try {
            File myFile = new File("/Users/sunny/IdeaProjects/TrackEdit/out/production/Assignment2/palindrome.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                str += myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }
        String[] arr = str.split(",");
        System.out.println("\t isPalindrome");
        for (String s : arr) {
            System.out.println( s.trim() + "\t" + PalindromeChecker.isPalindrome(s));
        }

    }
}
