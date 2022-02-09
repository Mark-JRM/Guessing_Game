import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class NumberGuess {

  public static int randomInRange(
    int min,
    int max,
    HashMap<Integer, Double> percentage
  ) {
    Optional<Map.Entry<Integer, Double>> bestOdds = percentage
      .entrySet()
      .stream()
      .filter(k -> k.getKey() > min && k.getKey() < max)
      .max(Comparator.comparing(Map.Entry::getValue));

    if (!bestOdds.isEmpty()) {
      return bestOdds.get().getKey();
    }

    Random r = new Random();
    return r.nextInt(max - min) + min;
  }

  public static void main(String[] args)
    throws IOException, ClassNotFoundException {
    Scanner console = new Scanner(System.in);
    int timesAnswered = 0;
    int guess = 0;

    HashMap<Integer, Integer> numbers = new HashMap<>();

    File toRead = new File("output.txt");
    FileInputStream fis = new FileInputStream(toRead);
    ObjectInputStream ois = new ObjectInputStream(fis);

    HashMap<Integer, Double> percentage = (HashMap<Integer, Double>) ois.readObject();

    ois.close();
    fis.close();

    while (true) {
      System.out.println("Enter any number");
      int usersInputNumber = console.nextInt();
      guess = 0;
      boolean answer = false;
      int num = (usersInputNumber * 2) + 100;
      //int num = 21;
      int hOrL = 0;
      int computersGuess = 0;

      int upperBound = num;
      int lowerBound = 0;

      for (int i = 0; i <= num; i = i + 1) {
        numbers.put(i, numbers.getOrDefault(i, 0));
      }

      numbers.put(
        usersInputNumber,
        numbers.getOrDefault(usersInputNumber, 0) + 1
      );
      //System.out.println("Debug1: " + timesAnswered);
      //System.out.println("Debug2: " + numbers);

      int x = numbers.get(usersInputNumber);

      //System.out.println("Debug3: " + x);

      //System.out.println("Debug4: " + percentage);
      while (answer == false) {
        if (hOrL == 0) {
          computersGuess = randomInRange(lowerBound, upperBound, percentage);
          System.out.println(computersGuess);
          guess = guess + 1;
        }
        if (hOrL == 1) {
          if (computersGuess < upperBound) {
            upperBound = computersGuess;
          }
          computersGuess = randomInRange(lowerBound, upperBound, percentage);
          System.out.println(computersGuess);
          guess = guess + 1;
        }
        if (hOrL == 2) {
          if (computersGuess > lowerBound) {
            lowerBound = computersGuess;
          }
          computersGuess = randomInRange(lowerBound, upperBound, percentage);
          System.out.println(computersGuess);
          guess = guess + 1;
        }

        if (computersGuess == usersInputNumber) {
          answer = true;
        } else if (computersGuess != usersInputNumber) {
          answer = false;
        }

        if (answer == true) {
          System.out.println("Right");
          System.out.println("It took " + guess + " guesses");
        }
        if (answer == false) {
          if (computersGuess < usersInputNumber) {
            System.out.println("Higher");
            hOrL = 2;
          }
          if (computersGuess > usersInputNumber) {
            System.out.println("Lower");
            hOrL = 1;
          }
        }
      }
      timesAnswered = timesAnswered += 1;
      for (int i = 0; i <= num; i = i + 1) {
        if (numbers.get(i) != 0) {
          percentage.put(
            i,
            (
              ((double) numbers.get(i)) /
              (timesAnswered != 0 ? timesAnswered : 1)
            )
          );
        }
      }
      ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("output.txt")
      );
      out.writeObject(percentage);
      out.close();
    }
  }
}
