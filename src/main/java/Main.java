import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    double result = 0;
    /*

        Ask the user do you want to multiply or add.
        Enter 0 for add, 1 for multiply

         */
    System.out.println(
      "Enter + to add, * to multiply, - to subtract or / to divide"
    );

    String sign = console.next();

    System.out.println("Enter number one");

    double numberOne = console.nextDouble();

    System.out.println("Enter number two");
    double numberTwo = console.nextDouble();

    // System.out.println("Result = " + List.of(numberOne, sign, numberTwo));
    if (sign.equals("+")) {
      result = (numberOne + numberTwo);
    }
    if (sign.equals("*")) {
      result = (numberOne * numberTwo);
    }
    if (sign.equals("-")) {
      result = (numberOne - numberTwo);
    }
    if (sign.equals("/")) {
      result = (numberOne / numberTwo);
    }
    if (result % 1 != 0) {
      System.out.println("Result = " + result);
    } else {
      System.out.println("Result = " + ((int) result));
    }
  }
}
